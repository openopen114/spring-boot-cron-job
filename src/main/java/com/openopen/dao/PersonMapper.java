package com.openopen.dao;

import com.openopen.model.Person;
import org.apache.ibatis.annotations.Mapper;


public interface PersonMapper {
    int deleteByPrimaryKey(String id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}