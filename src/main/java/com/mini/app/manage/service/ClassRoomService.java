package com.mini.app.manage.service;

import com.mini.app.common.entity.manage.ClassRoom;

/**
 * @author liyunlng
 * @ClassName: ClassRoomService
 * @Description:
 * @date 2020/11/5
 */
public interface ClassRoomService {
    int addClassRoom(ClassRoom data);

    int updateClassRoom(ClassRoom data);

    int delClassRoom(Integer data);
}
