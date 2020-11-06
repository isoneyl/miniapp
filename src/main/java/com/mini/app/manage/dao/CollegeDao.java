package com.mini.app.manage.dao;

import com.mini.app.baseDao.BaseDao;
import com.mini.app.common.entity.manage.College;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liyunlng
 * @ClassName: CollegeDao
 * @Description:
 * @date 2020/11/6
 */
@Mapper
public interface CollegeDao extends BaseDao<College> {
}
