package com.mini.app.home.dao;

import com.mini.app.baseDao.BaseDao;
import com.mini.app.common.entity.home.Bulletin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liyunlng
 * @ClassName: BulletinDao
 * @Description:
 * @date 2020/11/6
 */
@Mapper
public interface BulletinDao extends BaseDao<Bulletin> {
}
