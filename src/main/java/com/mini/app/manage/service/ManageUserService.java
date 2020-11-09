package com.mini.app.manage.service;

import com.mini.app.common.entity.manage.ManageUser;

import java.util.List;

/**
 * @author liyunlng
 * @ClassName: ManageUserService
 * @Description:
 * @date 2020/11/6
 */
public interface ManageUserService {
    int addManageUser(ManageUser data);

    int updateManageUser(ManageUser data);

    int delCollege(Integer data);

    List<ManageUser> queryUsers(Integer pageNo, Integer pageSize, ManageUser data);
}
