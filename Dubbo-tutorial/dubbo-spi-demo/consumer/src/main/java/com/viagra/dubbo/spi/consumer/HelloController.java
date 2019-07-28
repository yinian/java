package com.viagra.dubbo.spi.consumer;
import com.viagra.dubbo.spi.api.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Auther: viagra
 * @Date: 2019/7/28 16:48
 * @Description:
 */
@RestController
public class HelloController {

    @Autowired
    private IHelloService helloService;

    @RequestMapping(value = "/hello")
    public String hello(){
        return helloService.hello("tom");
    }

}
