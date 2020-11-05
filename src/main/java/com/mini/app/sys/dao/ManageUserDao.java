package com.mini.app.sys.dao;

import com.mini.app.baseDao.BaseDao;
import com.mini.app.common.entity.manage.ManageUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liyunlng
 * @ClassName: ManageUserDao
 * @Description:
 * @date 2020/11/5
 */
@Mapper
public interface ManageUserDao extends BaseDao<ManageUser> {
}
