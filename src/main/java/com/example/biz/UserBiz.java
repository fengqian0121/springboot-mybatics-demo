package com.example.biz;

import com.example.model.User;

import java.util.List;

/**
 * @author fengqian
 * date 2018/4/23 12:51
 */
public interface UserBiz{
    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);
}
