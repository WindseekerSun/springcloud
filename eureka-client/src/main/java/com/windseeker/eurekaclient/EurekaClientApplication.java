package com.windseeker.eurekaclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.logging.Logger;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class EurekaClientApplication {

    private static final Logger logger=Logger.getLogger(EurekaClientApplication.class.getName());
    @Value("${server.port}")
    String port;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }


    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name, HttpServletRequest request) {

        Enumeration en=request.getAttributeNames();
        while(en.hasMoreElements()) {
            String s = (String) en.nextElement();
            Object str = request.getAttribute(name);
            logger.info(s+":"+str);
        }
        return "hi " + name + " ,i am from port:" + port;
    }

    public String hiError(String name,HttpServletRequest request) {
        return "hi,"+name+",sorry,error!";
    }


}
