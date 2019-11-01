package com.yinian.rcp.registry.impl;

import com.yinian.rcp.registry.ServiceRegistry;
import com.yinian.rcp.util.Environment;
import com.yinian.rcp.util.XxlRpcException;
import com.yinian.rcp.util.XxlZkClient;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * service registry
 *
 *  /xxl-rpc/dev/
 *              - key01(service01)
 *                  - value01 (ip:port01)
 *                  - value02 (ip:port02)
 *
 * @author yinian 2018-10-17
 * @description 这个是重点
 */
public class ZkServiceRegistry extends ServiceRegistry {
    private static Logger logger = LoggerFactory.getLogger(ZkServiceRegistry.class);


    // ------------------------------ zk conf ------------------------------

    // config
    private static final String zkBasePath = "/xxl-rpc";
    private String zkEnvPath;
    private XxlZkClient xxlZkClient = null;

    private Thread refreshThread;
    private boolean refreshThreadStop = false;

    private volatile ConcurrentMap<String, TreeSet<String>> registryData = new ConcurrentHashMap<String, TreeSet<String>>();
    private volatile ConcurrentMap<String, TreeSet<String>> discoveryData = new ConcurrentHashMap<String, TreeSet<String>>();


    /**
     * key 2 path
     * @param   nodeKey
     * @return  znodePath
     */
    public String keyToPath(String nodeKey){
        return zkEnvPath + "/" + nodeKey;
    }

    /**
     * path 2 key
     * @param   nodePath
     * @return  nodeKey
     */
    public String pathToKey(String nodePath){
        if (nodePath==null || nodePath.length() <= zkEnvPath.length() || !nodePath.startsWith(zkEnvPath)) {
            return null;
        }
        return nodePath.substring(zkEnvPath.length()+1, nodePath.length());
    }

    // ------------------------------ util ------------------------------

    /**
     * @param param
     *      Environment.ZK_ADDRESS  ：zk address
     *      Environment.ZK_DIGEST   ：zk didest
     *      Environment.ENV         ：env
     */
    @Override
    public void start(Map<String, String> param) {
        String zkaddress = param.get(Environment.ZK_ADDRESS);
        String zkdigest = param.get(Environment.ZK_DIGEST);
        String env = param.get(Environment.ENV);

        // valid
        if (zkaddress==null || zkaddress.trim().length()==0) {
            throw new XxlRpcException("xxl-rpc zkaddress can not be empty");
        }

        // init zkpath
        if (env==null || env.trim().length()==0) {
            throw new XxlRpcException("xxl-rpc env can not be empty");
        }

        zkEnvPath = zkBasePath.concat("/").concat(env);

        // init
        xxlZkClient = new XxlZkClient(zkaddress, zkEnvPath, zkdigest, new Watcher(){

            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    logger.debug(">>>>>>>>>> xxl-rpc: watcher:{}", watchedEvent);

                    // session expire, close old and create new
                    if (watchedEvent.getState() == Event.KeeperState.Expired){
                        xxlZkClient.destroy();
                        xxlZkClient.getClient();
                        // refreshDiscoveryData (all): expire retry
                        refreshDiscoveryData(null);
                        logger.info(">>>>>>>>>> xxl-rpc, zk re-connect reloadAll success.");
                    }

                    // watch + watch
                    String path = watchedEvent.getPath();
                    String key = pathToKey(path);
                    if (key != null){
                        // keep watch conf key:and One-time trigger
                        xxlZkClient.getClient().exists(path, true);

                        // refresh
                        if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                            // refreshDiscoveryData (one)：one change
                            refreshDiscoveryData(key);
                        } else if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                            logger.info("reload all 111");
                        }
                    }

                } catch (KeeperException e) {
                    logger.error(e.getMessage(), e);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }

            }
        });
        // refresh thread
        refreshThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!refreshThreadStop){
                    try {
                        TimeUnit.SECONDS.sleep(60);

                        // refreshDiscoveryData (all)：cycle check
                        refreshDiscoveryData(null);

                        // refresh RegistryData
                        refreshRegistryData();
                    } catch (Exception e) {
                        if (!refreshThreadStop) {
                            logger.error(">>>>>>>>>> xxl-rpc, refresh thread error.", e);
                        }
                    }
                }
                logger.info(">>>>>>>>>> xxl-rpc, refresh thread stoped.");

            }
        });

        refreshThread.setName("xxl-rpc, ZkServiceRegistry refresh thread.");
        refreshThread.setDaemon(true);
        refreshThread.start();

        logger.info(">>>>>>>>>> xxl-rpc, ZkServiceRegistry init success. [env={}]", env);
    }

    @Override
    public void stop() {
        if (xxlZkClient!=null) {
            xxlZkClient.destroy();
        }
        if (refreshThread != null) {
            refreshThreadStop = true;
            refreshThread.interrupt();
        }
    }

    /**
     * refresh discovery data, and cache
     *
     * @param key
     */
    private void refreshDiscoveryData(String key){

        Set<String> keys = new HashSet<String>();
        if (key!=null && key.trim().length()>0) {
            keys.add(key);
        } else {
            if (discoveryData.size() > 0) {
                keys.addAll(discoveryData.keySet());
            }
        }

        if (keys.size() > 0) {
            for (String keyItem: keys) {

                // add-values
                String path = keyToPath(keyItem);
                Map<String, String> childPathData = xxlZkClient.getChildPathData(path);

                // exist-values
                TreeSet<String> existValues = discoveryData.get(keyItem);
                if (existValues == null) {
                    existValues = new TreeSet<String>();
                    discoveryData.put(keyItem, existValues);
                }

                if (childPathData.size() > 0) {
                    existValues.addAll(childPathData.keySet());
                }
            }
            logger.info(">>>>>>>>>> xxl-rpc, refresh discovery data success, discoveryData = {}", discoveryData);
        }
    }

    /**
     * refresh registry data
     */
    private void refreshRegistryData(){
        if (registryData.size() > 0) {
            for (Map.Entry<String, TreeSet<String>> item: registryData.entrySet()) {
                String key = item.getKey();
                for (String value:item.getValue()) {
                    // make path, child path
                    String path = keyToPath(key);
                    xxlZkClient.setChildPathData(path, value, "");
                }
            }
            logger.info(">>>>>>>>>> xxl-rpc, refresh registry data success, registryData = {}", registryData);
        }
    }

    @Override
    public boolean registry(String key, String value) {

        // local cache
        TreeSet<String> values = registryData.get(key);
        if (values == null) {
            values = new TreeSet<>();
            registryData.put(key, values);
        }
        values.add(value);

        // make path, child path
        String path = keyToPath(key);
        xxlZkClient.setChildPathData(path, value, "");

        logger.info(">>>>>>>>>> xxl-rpc, registry success, key = {}, value = {}", key, value);
        return true;
    }

    @Override
    public boolean remove(String key, String value) {

        TreeSet<String> values = discoveryData.get(key);
        if (values != null) {
            values.remove(value);
        }
        String path = keyToPath(key);
        xxlZkClient.deleteChildPath(path, value);

        return true;
    }

    @Override
    public TreeSet<String> discovery(String key) {

        // local cache
        TreeSet<String> values = discoveryData.get(key);
        if (values == null) {

            // refreshDiscoveryData (one)：first use
            refreshDiscoveryData(key);

            values = discoveryData.get(key);
        }

        return values;
    }

}
