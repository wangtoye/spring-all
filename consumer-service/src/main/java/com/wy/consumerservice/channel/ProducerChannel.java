package com.wy.consumerservice.channel;

import com.wy.consumerservice.channel.impl.ProducerChannelImpl;
import com.wy.consumerservice.common.config.FeignConfiguration;
import com.wy.consumerservice.dto.TestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangye
 * @date: 2019-04-17
 * Description:
 */
@FeignClient(name = "producer-service", fallback = ProducerChannelImpl.class, configuration = FeignConfiguration.class)
public interface ProducerChannel {

    /**
     * GET请求
     * 传输的是单个参数，必须在前面加上注解@requestParam且必须指定value
     * 不支持传输结构体，如果非要传输，只能使用map并且也要加上注解@requestParam且必须指定value
     * String getStr(@RequestParam(value="c") Map<String,Object> c);
     *
     * @param a
     * @return
     */
    @RequestMapping(value = "/api/test1", method = RequestMethod.GET)
    String getStr(@RequestParam("a") Integer a);

    /**
     * POST请求
     * 传输的是个结构体，必须在前面加上注解@RequestBody
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/api/test2", method = RequestMethod.POST)
    String getStr(@RequestBody TestDto dto);
}
