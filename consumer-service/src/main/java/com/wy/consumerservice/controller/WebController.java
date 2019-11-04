package com.wy.consumerservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangye
 * @date: 2019-04-17
 * Description:
 */
@Controller
public class WebController {

    /**
     * 默认首页
     *
     * @return
     */
    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    /**
     * web一级模块页面控制器
     *
     * @param page
     * @return
     */
    @RequestMapping("/web/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    /**
     * web 二级模块页面控制器
     *
     * @param model
     * @param page
     * @return
     */
    @RequestMapping("/web/{model}/{page}")
    public String showPages(@PathVariable String model, @PathVariable String page) {
        return model + "/" + page;
    }
}
