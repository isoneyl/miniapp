package com.mini.app.sys.dao;

import com.mini.app.baseDao.BaseDao;
import com.mini.app.common.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liyunlng
 * @ClassName: UserDao
 * @Description:
 * @date 2020/10/22
 */
@Mapper
public interface UserDao extends BaseDao<User> {

}
