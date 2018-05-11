package com.example.mapper;

import com.example.model.DbColumn;

public interface DbColumnMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(DbColumn record);

    DbColumn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DbColumn record);

    int updateByPrimaryKey(DbColumn record);
}