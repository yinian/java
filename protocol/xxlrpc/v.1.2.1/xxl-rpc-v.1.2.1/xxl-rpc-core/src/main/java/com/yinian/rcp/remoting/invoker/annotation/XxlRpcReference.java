package com.yinian.rcp.remoting.invoker.annotation;

import com.yinian.rcp.remoting.invoker.call.CallType;
import com.yinian.rcp.remoting.net.NetEnum;
import com.yinian.rcp.serialize.Serializer;

import java.lang.annotation.*;

/**
 * rpc service annotation, skeleton of stub ("@Inherited" allow service use "Transactional")
 *
 * @author yinian 2015-10-29 19:44:33
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface XxlRpcReference {

    NetEnum netType() default NetEnum.JETTY;
    Serializer.SerializeEnum serializer() default Serializer.SerializeEnum.HESSIAN;
    CallType callType() default CallType.SYNC;

    // Class<?> iface;
    String version() default "";
    long timeout() default 1000;

    String address() default "";
    String accessToken() default "";



}
