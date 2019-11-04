package com.wy.producerservice.serviceImpl;

import com.wy.producerservice.entity.User;
import com.wy.producerservice.mapper.UserMapper;
import com.wy.producerservice.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangye
 * @since 2019-09-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    public List<User> queryTest(){
        return userMapper.queryTest();
    }
}
