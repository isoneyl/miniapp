package com.mini.app.home.service.impl;

import com.mini.app.common.entity.home.Bulletin;
import com.mini.app.home.dao.BulletinDao;
import com.mini.app.home.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liyunlng
 * @ClassName: BulletinServiceImpl
 * @Description:
 * @date 2020/10/27
 */
@Service
public class BulletinServiceImpl implements BulletinService {

    @Autowired
    private BulletinDao bulletinDao;

    @Override
    @Transactional
    public int addBulletin(Bulletin data) {
        return bulletinDao.insertSelective(data);
    }

    @Override
    @Transactional
    public int updateBulletin(Bulletin data) {
        return bulletinDao.updateByPrimaryKeySelective(data);
    }

    @Override
    @Transactional
    public int delBulletin(Integer data) {
        return bulletinDao.deleteByPrimaryKey(data);
    }
}
