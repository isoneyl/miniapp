package com.mini.app.home.service.impl;

import com.mini.app.common.entity.home.Ask;
import com.mini.app.home.dao.AskDao;
import com.mini.app.home.service.ASKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liyunlng
 * @ClassName: ASKServiceImpl
 * @Description:
 * @date 2020/10/27
 */
@Service
public class ASKServiceImpl implements ASKService {

    @Autowired
    private AskDao askDao;

    @Override
    @Transactional
    public int addAsk(Ask data) {
        return askDao.insertSelective(data);
    }

    @Override
    @Transactional
    public int updateAsk(Ask data) {
        return askDao.updateByPrimaryKeySelective(data);
    }

    @Override
    @Transactional
    public int delAsk(Integer data) {
        return askDao.deleteByPrimaryKey(data);
    }
}
