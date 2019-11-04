package com.wy.producerservice.controller;

import com.google.gson.Gson;
import com.wy.producerservice.dto.ResponseVo;
import com.wy.producerservice.dto.TestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangye
 * @date: 2019-04-17
 * Description:
 */
@RestController
@RequestMapping("/api/")
public class ProducerController {

    private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private Registration registration;


    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public String getStr(@RequestParam String a) {
        logger.info("/test1 start.");
        //获取当前服务的实例
        List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
        ServiceInstance instance = list.get(0);
        ResponseVo vo = new ResponseVo();
        vo.setCode(100000);
        vo.setData("host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ",a:" + a);
        vo.setSuccess(true);
        vo.setMsg("test");
        logger.info("/test1 end.");
        return new Gson().toJson(vo);
    }


    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public String getStr(@RequestBody TestDto dto) throws InterruptedException {
        System.out.println("暂停开始");
        Thread.sleep(dto.getA() * 1000);
        return "{\"a\":" + dto.getA() + ",\"b\":\"" + dto.getB() + "\"}";
    }
}
