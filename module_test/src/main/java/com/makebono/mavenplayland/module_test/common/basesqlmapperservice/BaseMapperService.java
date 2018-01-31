package com.makebono.mavenplayland.module_test.common.basesqlmapperservice;

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
        final T result = mapper.selectByPrimaryKey(key);
        return result;
    }

    public int save(final T entity) {
        return mapper.insert(entity);
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
