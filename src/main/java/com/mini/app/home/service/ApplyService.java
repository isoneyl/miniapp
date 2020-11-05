package com.mini.app.home.service;

import com.mini.app.common.entity.home.Apply;

/**
 * @author liyunlng
 * @ClassName: ApplyService
 * @Description:
 * @date 2020/10/27
 */
public interface ApplyService {
    /**
     * @Description 添加出入申请
     * @Param [data]
     * @return void
     **/
    int addApply(Apply data);

}
