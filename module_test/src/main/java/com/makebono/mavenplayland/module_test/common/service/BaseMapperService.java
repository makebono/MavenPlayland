package com.makebono.mavenplayland.module_test.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.common.Mapper;

/**
 * Created by liuzh on 2014/12/11.
 */
public abstract class BaseMapperService<T> {

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    public T selectByKey(final Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    public T selectOne(final T record) {
        return mapper.selectOne(record);
    }

    public List<T> select(final T record) {
        return mapper.select(record);
    }

    public int save(final T record) {
        return mapper.insert(record);
    }

    public List<T> selectAll() {
        return mapper.selectAll();
    }

    public int delete(final Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    public int updateAll(final T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    public int updateNotNull(final T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    public List<T> selectByExample(final Object example) {
        return mapper.selectByExample(example);
    }

}
