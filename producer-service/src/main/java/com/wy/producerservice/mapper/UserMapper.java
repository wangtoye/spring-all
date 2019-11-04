package com.wy.producerservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wy.producerservice.entity.User;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wangye
 * @since 2019-09-16
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> queryTest();
}