package com.mini.app.home.dao;

import com.mini.app.baseDao.BaseDao;
import com.mini.app.common.entity.home.News;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liyunlng
 * @ClassName: NewsDao
 * @Description:
 * @date 2020/11/6
 */
@Mapper
public interface NewsDao extends BaseDao<News> {
}
