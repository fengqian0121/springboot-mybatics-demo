package com.example.service;

import com.example.model.DbColumn;
import com.example.model.User;

import java.util.List;

/**
 * @author fengqian
 * date 2018/4/17 11:00
 */
public interface UserService {
    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);

}
