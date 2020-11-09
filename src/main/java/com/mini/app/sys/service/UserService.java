package com.mini.app.sys.service;

import com.mini.app.common.entity.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author liyunlng
 * @ClassName: UserService
 * @Description:
 * @date 2020/10/23
 */
public interface UserService {

    /**
     * @Description 保存用户信息并回显
     * @Date 10:16 2020/10/23
     * @Param [data]
     * @return com.mini.app.common.entity.user.User
     **/
    int saveUserInfo(User data);

    /**
     * @Description 根据ID查询用户信息
     * @Date 10:36 2020/10/23
     * @Param [userId]
     * @return com.mini.app.common.entity.user.User
     **/
    User queryUserInfoById(Integer userId);

    /**
     * @Description 上传头像
     * @Date 11:30 2020/10/23
     * @Param [fileImg]
     * @return java.lang.String
     **/
    String uploadImg(MultipartFile fileImg);


    List<User> queryUsers(Integer pageNo, Integer pageSize, User data);
}
