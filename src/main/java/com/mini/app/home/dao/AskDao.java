package com.mini.app.home.dao;

import com.mini.app.baseDao.BaseDao;
import com.mini.app.common.entity.home.Ask;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liyunlng
 * @ClassName: AskDao
 * @Description:
 * @date 2020/11/6
 */
@Mapper
public interface AskDao extends BaseDao<Ask> {
}
