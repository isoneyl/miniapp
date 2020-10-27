package com.mini.app.sys.service.impl;

import com.mini.app.common.entity.user.User;
import com.mini.app.common.enums.Result;
import com.mini.app.common.exception.ApiException;
import com.mini.app.sys.dao.UserDao;
import com.mini.app.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * @author liyunlng
 * @ClassName: UserServiceImpl
 * @Description:
 * @date 2020/10/23
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Value("${imgstore.path}")
    private String storePath; // 文件存储路径


    /**
     * @param data
     * @return com.mini.app.common.entity.user.User
     * @Description 保存用户信息并回显
     * @Date 10:16 2020/10/23
     * @Param [data]
     */
    @Override
    @Transactional
    public int saveUserInfo(User data) {
        data.setUpdateTime(new Date());
        int rows = userDao.updateByPrimaryKeySelective(data);
        return rows;
    }

    /**
     * @param userId
     * @return com.mini.app.common.entity.user.User
     * @Description 根据ID查询用户信息
     * @Date 10:36 2020/10/23
     * @Param [userId]
     */
    @Override
    public User queryUserInfoById(Integer userId) {

        User user = userDao.selectByPrimaryKey(userId);
        return user;
    }

    /**
     * @param fileImg
     * @return java.lang.String
     * @Description 上传头像
     * @Date 11:30 2020/10/23
     * @Param [fileImg]
     */
    @Override
    public String uploadImg(MultipartFile fileImg) {
        long size = fileImg.getSize();
        if (size <= 0) {
            throw new ApiException(Result.UPLOAD_IMG_ERROR);
        }
        String filPath = fileImg.getOriginalFilename();
        String suffix = filPath.substring(filPath.lastIndexOf("."));
        // 图片存储路径：如 D:/store/abcdef.jpg
        String fileName = UUID.randomUUID().toString() + suffix;
        filPath = storePath + File.separator + fileName;

        File file = new File(filPath);
        try {
            fileImg.transferTo(file);
            if (null == ImageIO.read(file)) {
                throw new ApiException(Result.UPLOAD_IMG_ERROR);
            }
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
