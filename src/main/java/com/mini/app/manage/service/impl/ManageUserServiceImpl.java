package com.mini.app.manage.service.impl;

import com.mini.app.common.entity.manage.ManageUser;
import com.mini.app.manage.service.ManageUserService;
import com.mini.app.sys.dao.ManageUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liyunlng
 * @ClassName: ManageUserServiceImpl
 * @Description:
 * @date 2020/11/6
 */
@Service
public class ManageUserServiceImpl implements ManageUserService {

    @Autowired
    private ManageUserDao manageUserDao;

    @Override
    @Transactional
    public int addManageUser(ManageUser data) {

        return manageUserDao.insertSelective(data);
    }

    @Override
    @Transactional
    public int updateManageUser(ManageUser data) {
        return manageUserDao.updateByPrimaryKeySelective(data);
    }

    @Override
    @Transactional
    public int delCollege(Integer data) {
        return manageUserDao.deleteByPrimaryKey(data);
    }
}