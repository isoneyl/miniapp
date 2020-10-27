package com.mini.app.baseDao;

import tk.mybatis.mapper.common.Mapper;

/**
 * @Description: 通用mapper(继承此类单表的所以操作不需要写sql)
 */

public interface BaseDao<T> extends Mapper<T> {

}
