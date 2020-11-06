package com.mini.app.manage.service;

import com.mini.app.common.entity.manage.College;

/**
 * @author liyunlng
 * @ClassName: ConllegeService
 * @Description:
 * @date 2020/11/5
 */
public interface CollegeService {

    int addCollege(College data);

    int addCollegeUser(College data);

    int delCollege(Integer data);
}
