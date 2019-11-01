package com.yinian.rpc.sample.client.conf;

import com.yinian.rcp.registry.impl.LocalServiceRegistry;
import com.yinian.rcp.registry.impl.ZkServiceRegistry;
import com.yinian.rcp.remoting.invoker.impl.XxlRpcSpringInvokerFactory;
import com.yinian.rcp.util.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * xxl-rpc invoker config
 *
 * @author xuxueli 2018-10-19
 */
@Configuration
public class XxlRpcInvokerConfig {
    private Logger logger = LoggerFactory.getLogger(XxlRpcInvokerConfig.class);


    @Value("${xxl-rpc.registry.zk.zkaddress}")
    private String zkaddress;

    @Value("${xxl-rpc.registry.zk.zkdigest}")
    private String zkdigest;

    @Value("${xxl-rpc.env}")
    private String env;


    @Bean
    public XxlRpcSpringInvokerFactory xxlJobExecutor() {

        XxlRpcSpringInvokerFactory invokerFactory = new XxlRpcSpringInvokerFactory();
        if (zkaddress != null) {
            invokerFactory.setServiceRegistryClass(LocalServiceRegistry.class);
            invokerFactory.setServiceRegistryParam(new HashMap<String, String>(){{
                put(Environment.ZK_ADDRESS, zkaddress);
                put(Environment.ZK_DIGEST, zkdigest);
                put(Environment.ENV, env);
            }});
        }

        logger.info(">>>>>>>>>>> xxl-rpc invoker config init finish.");
        return invokerFactory;
    }

}