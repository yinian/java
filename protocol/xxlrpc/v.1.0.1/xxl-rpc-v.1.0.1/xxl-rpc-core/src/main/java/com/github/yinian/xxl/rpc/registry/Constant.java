package com.github.yinian.xxl.rpc.registry;

/**
 * rpc service address on zookeeper
 * @author yinian 2015-10-29 20:17:03
 */
public interface Constant {
	/**
	 *  servicePath : xxl-rpc/interfaceName/serverAddress(ip01:port9999)
	 */
    String ZK_SERVICES_REGISTRY = "/xxl-rpc";
}