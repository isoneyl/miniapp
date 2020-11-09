package com.mini.app.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.mini.app.common.entity.manage.College;
import com.mini.app.manage.dao.CollegeDao;
import com.mini.app.manage.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liyunlng
 * @ClassName: ConllegeServiceImpl
 * @Description:
 * @date 2020/11/5
 */
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeDao collegeDao;

    @Override
    @Transactional
    public int addCollege(College data) {
        int i = collegeDao.insertSelective(data);
        return i;
    }

    @Override
    @Transactional
    public int addCollegeUser(College data) {

        return collegeDao.updateByPrimaryKeySelective(data);
    }

    @Override
    @Transactional
    public int delCollege(Integer data) {
        return collegeDao.deleteByPrimaryKey(data);
    }

    @Override
    public List<College> queryUsers(Integer pageNo, Integer pageSize, College data) {
        PageHelper.startPage(pageNo, pageSize);
        List<College> colleges = collegeDao.select(data);
        return colleges;
    }
}
