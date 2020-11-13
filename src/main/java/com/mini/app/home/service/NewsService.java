package com.mini.app.home.service;

import com.mini.app.common.entity.home.News;

import java.util.List;

/**
 * @author liyunlng
 * @ClassName: NewsService
 * @Description:
 * @date 2020/10/27
 */
public interface NewsService {
    int addNews(News data);

    int updateNews(News data);

    int delNews(Integer data);
}
