package com.makebono.mavenplayland.module_test.mapper.mybatismapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/** 
 * @ClassName: BaseMapper 
 * @Description: BaseMapper 
 * @author makebono
 * @date 2018年1月31日 下午3:08:19 
 *  
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {}
