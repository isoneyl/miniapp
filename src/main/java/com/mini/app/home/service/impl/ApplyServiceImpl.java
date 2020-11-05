package com.mini.app.home.service.impl;

import com.mini.app.common.entity.home.Apply;
import com.mini.app.home.dao.ApplyDao;
import com.mini.app.home.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author liyunlng
 * @ClassName: ApplyServiceImpl
 * @Description:
 * @date 2020/10/27
 */
@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyDao applyDao;
    /**
     * @param data
     * @return void
     * @Description 添加出入申请
     * @Param [data]
     */
    @Override
    @Transactional
    public int addApply(Apply data) {
        data.setCreatTime(new Date());
        return applyDao.insertSelective(data);
    }
}
