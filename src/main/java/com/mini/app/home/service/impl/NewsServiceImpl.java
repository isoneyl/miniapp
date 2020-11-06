package com.mini.app.home.service.impl;

import com.mini.app.common.entity.home.News;
import com.mini.app.home.dao.NewsDao;
import com.mini.app.home.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liyunlng
 * @ClassName: NewsServiceImpl
 * @Description:
 * @date 2020/10/27
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    @Transactional
    public int addNews(News data) {
        return newsDao.insertSelective(data);
    }

    @Override
    @Transactional
    public int updateNews(News data) {
        return newsDao.updateByPrimaryKeySelective(data);
    }

    @Override
    @Transactional
    public int delNews(Integer data) {
        return newsDao.deleteByPrimaryKey(data);
    }
}
