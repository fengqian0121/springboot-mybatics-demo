package com.example.service.impl;

import com.example.mapper.DbColumnMapper;
import com.example.model.DbColumn;
import com.example.service.DbColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fengqian
 * date 2018/4/23 16:36
 */
@Service
public class DbColumnServiceImpl implements DbColumnService {

    @Autowired
    private DbColumnMapper dbColumnMapper;
    @Override
    public void addDbColumn(DbColumn dbColumn) {
        dbColumnMapper.insertSelective(dbColumn);
    }
}
