package com.mini.app.home.service.impl;

import com.github.pagehelper.PageHelper;
import com.mini.app.common.entity.home.Bulletin;
import com.mini.app.home.dao.BulletinDao;
import com.mini.app.home.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<Bulletin> queryBulletin(Integer pageNo, Integer pageSize, Bulletin data) {
        PageHelper.startPage(pageNo, pageSize);
        List<Bulletin> bulletins = bulletinDao.selectAll();
        return bulletins;
    }
}
