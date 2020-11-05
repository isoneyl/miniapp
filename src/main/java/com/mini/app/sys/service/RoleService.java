package com.mini.app.sys.service;

import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.user.Role;
import com.mini.app.common.entity.user.UserRole;

import java.util.List;

/**
 * @author liyunlng
 * @ClassName: RoleService
 * @Description:
 * @date 2020/10/31
 */
public interface RoleService {
    /**
     * @Author liyunlong
     * @Description 添加角色名
     * @Date 16:03 2020/10/31
     * @Param [role]
     * @return int
     **/
    int addUserRole(Role role);

    /**
     * @Author liyunlong
     * @Description 删除
     * @Date 16:03 2020/10/31
     * @Param [id]
     * @return int
     **/
    int delUserRole(Integer id);

    /**
     * @Author liyunlong
     * @Description 查询所有角色
     * @Date 16:22 2020/10/31
     * @Param [request]
     * @return java.util.List<com.mini.app.common.entity.user.Role>
     **/
    List<Role> queryRole(ApiRequest<Role> request);

    /**
     * @Author liyunlong
     * @Description 为用户添加角色
     * @Date 16:29 2020/10/31
     * @Param [userRole]
     * @return int
     **/
    int addUserByRole(UserRole userRole);

    boolean queryRoleByUserId(Integer id);

}
