package com.wy.consumerservice.controller;

import com.wy.consumerservice.channel.ProducerChannel;
import com.wy.consumerservice.dto.TestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangye
 * @date: 2019-04-17
 * Description:
 */
@RestController
@RequestMapping("/api")
@Api(tags = "消费者测试类")
public class ConsumerController {

    @Resource
    private ProducerChannel producerChannel;

//    @ApolloConfig
//    private Config config;


    @ApiOperation(value = "测试接口1", notes = "获取当前服务的实例，返回输入的参数值")
    @ApiImplicitParam(name = "a", value = "数字", required = true, paramType = "query", dataTypeClass =
            java.lang.Integer.class)
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1(@RequestParam("a") Integer a, HttpServletRequest request) {
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String head = enumeration.nextElement();
            System.out.println(head + ":" + request.getHeader(head));
        }
        return producerChannel.getStr(a);
    }

    @ApiOperation(value = "测试接口2", notes = "返回输入的参数值")
    @ApiImplicitParam(name = "a", value = "数字", required = true, paramType = "path", dataTypeClass =
            java.lang.Integer.class)
    @RequestMapping(value = "/test2/{a}", method = RequestMethod.GET)
    public String test2(@PathVariable("a") Integer a) {
        return "a:" + a.toString();
    }


    @ApiOperation(value = "测试接口3", notes = "暂停x秒，并且返回输入的参数值")
    @ApiImplicitParam(name = "dto", value = "测试dto", required = true, paramType = "body", dataType = "TestDto")
    @RequestMapping(value = "/test3", method = RequestMethod.POST)
    public String test3(@RequestBody TestDto dto) {
        TestDto dto1 = new TestDto(){{
            setA(dto.getA());
            setB(dto.getB());
        }};
        dto1.setA(1).setB("1");
        System.out.println(dto1.toString());
        return producerChannel.getStr(dto);
    }


//    @ApiOperation(value = "测试接口4", notes = "打印apollo配置中心配置信息")
//    @ApiImplicitParam(name = "configName", value = "配置名称", required = true, paramType = "query")
//    @RequestMapping(value = "/test4", method = RequestMethod.GET)
//    public String test4(@RequestParam("configName") String configName) {
//        return config.getProperty(configName, "Don't set this config option.");
//    }

}
