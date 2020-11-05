package com.mini.app.sys.dao;

import com.mini.app.baseDao.BaseDao;
import com.mini.app.common.entity.user.Role;
import com.mini.app.common.entity.user.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liyunlng
 * @ClassName: RoleDao
 * @Description:
 * @date 2020/10/31
 */
@Mapper
public interface UserRoleDao extends BaseDao<UserRole> {

}
