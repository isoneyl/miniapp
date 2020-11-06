package com.mini.app.manage.service;

import com.mini.app.common.entity.manage.ManageUser;

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
}
