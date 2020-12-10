package com.mini.app.home.service;

import com.mini.app.common.entity.home.Bulletin;

import java.util.List;

/**
 * @author liyunlng
 * @ClassName: BulletinService
 * @Description:
 * @date 2020/10/27
 */
public interface BulletinService {
    int addBulletin(Bulletin data);

    int updateBulletin(Bulletin data);

    int delBulletin(Integer data);

    List<Bulletin> queryBulletin(Integer pageNo, Integer pageSize, Bulletin data);
}
