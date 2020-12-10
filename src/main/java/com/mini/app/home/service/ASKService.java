package com.mini.app.home.service;

import com.mini.app.common.entity.home.Ask;

import java.util.List;

/**
 * @author liyunlng
 * @ClassName: ASKService
 * @Description:
 * @date 2020/10/27
 */
public interface ASKService {
    int addAsk(Ask data);

    int updateAsk(Ask data);

    int delAsk(Integer data);

    List<Ask> queryAsks(Integer pageNo, Integer pageSize, Ask data);
}
