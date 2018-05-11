package com.example.biz.impl;

import com.example.biz.BaseBiz;
import com.example.biz.UserBiz;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fengqian
 * date 2018/4/23 12:51
 */
@Service
public class UserBizImpl implements UserBiz{

    @Autowired
    private UserService userService;

    @Override
    public int addUser(User user) {
        return userService.addUser(user);
    }

    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        return userService.findAllUser(pageNum,pageSize);
    }
}
