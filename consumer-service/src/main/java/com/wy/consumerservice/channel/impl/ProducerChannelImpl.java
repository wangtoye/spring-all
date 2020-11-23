package com.wy.consumerservice.channel.impl;

import com.wy.consumerservice.channel.ProducerChannel;
import com.wy.consumerservice.dto.TestDto;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangtoye
 * @date: 2019-04-17
 * Description:
 */
@Service
public class ProducerChannelImpl implements ProducerChannel {
    @Override
    public String getStr(Integer a) {
        return "系统熔断";
    }

    @Override
    public String getStr(TestDto dto) {
        return "系统熔断";
    }
}
