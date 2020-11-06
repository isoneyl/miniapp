package com.mini.app.manage.service.impl;

import com.mini.app.common.entity.manage.ClassRoom;
import com.mini.app.manage.dao.ClassRoomDao;
import com.mini.app.manage.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liyunlng
 * @ClassName: ClassRoomServiceImpl
 * @Description:
 * @date 2020/11/5
 */
@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassRoomDao classRoomDao;

    @Override
    @Transactional
    public int addClassRoom(ClassRoom data) {
        return classRoomDao.insertSelective(data);
    }

    @Override
    @Transactional
    public int updateClassRoom(ClassRoom data) {
        return classRoomDao.updateByPrimaryKeySelective(data);
    }

    @Override
    @Transactional
    public int delClassRoom(Integer data) {
        return classRoomDao.deleteByPrimaryKey(data);
    }
}