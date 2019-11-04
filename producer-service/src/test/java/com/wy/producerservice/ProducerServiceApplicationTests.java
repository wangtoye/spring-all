package com.wy.producerservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.producerservice.entity.User;
import com.wy.producerservice.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerServiceApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(ProducerServiceApplicationTests.class);

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void test1() {
        logger.debug("这是一个debug信息");
        logger.info("这是一个info信息");
        logger.warn("这是一个warn信息");
        logger.error("这是一个error信息");

//        logger.debug("这是一个{}信息","debug");
//        logger.info("这是一个{}信息","info");
//        logger.warn("这是一个{}信息","warn");
//        logger.error("这是一个{}信息","error");

        List<User> list = userService.list();
        list.forEach(user -> System.out.println(user.toString()));
    }

    @Test
    public void test2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .isNotNull("name")
                .between("age", 15, 25)
                .orderByDesc("id");
//        List<User> list = userService.list(queryWrapper);
        IPage<User> pageData = userService.page(new Page<>(2, 2), queryWrapper);
        pageData.getRecords().forEach(user -> System.out.println(user.toString()));


//        List<User> list = userService.list(Wrappers.<User>lambdaQuery()
//                .isNotNull(User::getName)
//                .between(User::getAge, 19, 20));
//
//        list.forEach(user -> System.out.println(user.toString()));
    }

    @Test
    public void test3() {
        List<User> list = userService.queryTest();
        list.forEach(user -> System.out.println(user.toString()));
    }
}
