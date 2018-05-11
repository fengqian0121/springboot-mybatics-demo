package com.example.controller;

import com.example.biz.BaseBiz;
import com.example.biz.UserBiz;
import com.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fengqian
 * date 2018/4/17 11:00
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserBiz userBiz;
    @Autowired
    private BaseBiz baseBiz;

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(User user) {
        return userBiz.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        logger.info("请求页数：" + pageNum);
        return userBiz.findAllUser(pageNum, pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/test/{planType}", produces = {"application/json;charset=UTF-8"})
    public void columnTest(@PathVariable("planType") String planType) {
        baseBiz.buildEntity(planType, "H:\\students.xlsx");
    }
}

