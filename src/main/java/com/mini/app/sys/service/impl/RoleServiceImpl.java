package com.mini.app.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.user.Role;
import com.mini.app.common.entity.user.UserRole;
import com.mini.app.sys.dao.RoleDao;
import com.mini.app.sys.dao.UserRoleDao;
import com.mini.app.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author liyunlng
 * @ClassName: RoleServiceImpl
 * @Description:
 * @date 2020/10/31
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * @param role
     * @return int
     * @Author liyunlong
     * @Description 添加角色名
     * @Date 16:03 2020/10/31
     * @Param [role]
     */
    @Override
    @Transactional
    public int addUserRole(Role role) {
        role.setCreateTime(new Date());
        return roleDao.insertSelective(role);
    }

    /**
     * @param id
     * @return int
     * @Author liyunlong
     * @Description 删除
     * @Date 16:03 2020/10/31
     * @Param [id]
     */
    @Override
    public int delUserRole(Integer id) {
        return roleDao.deleteByPrimaryKey(id);
    }

    /**
     * @param request
     * @return java.util.List<com.mini.app.common.entity.user.Role>
     * @Author liyunlong
     * @Description 查询所有角色
     * @Date 16:22 2020/10/31
     * @Param [request]
     */
    @Override
    public List<Role> queryRole(ApiRequest<Role> request) {
        return roleDao.selectAll();
    }

    /**
     * @param userRole
     * @return int
     * @Author liyunlong
     * @Description 为用户添加角色
     * @Date 16:29 2020/10/31
     * @Param [userRole]
     */
    @Override
    public int addUserByRole(UserRole userRole) {

        return userRoleDao.insertSelective(userRole);
    }

    @Override
    public boolean queryRoleByUserId(Integer id) {
        List<UserRole> select = userRoleDao.select(new UserRole().setUserId(id));
        return select.size() > 0;
    }
}
