package com.mini.app.sys.controller;

import com.mini.app.common.annotation.ValidateArgument;
import com.mini.app.common.annotation.ValidateArguments;
import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.ApiResponse;
import com.mini.app.common.entity.user.User;
import com.mini.app.common.enums.Result;
import com.mini.app.common.exception.ApiException;
import com.mini.app.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;


/**
 * @author liyunlng
 * @ClassName: UserController
 * @Description:
 * @date 2020/10/23
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${imgstore.path}")
    private String storePath; // 文件存储路径


    /**
     * @Description 保存用户接口
     * @Date 10:40 2020/10/23
     * @Param [apiRequest]
     * @return com.mini.app.common.entity.ApiResponse<java.lang.Boolean>
     **/
    @PostMapping(value = "/saveUser", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ApiResponse<Boolean> saveUserInfo(@RequestBody ApiRequest<User> apiRequest) {
        Integer userId = apiRequest.getUserId();
        User data = apiRequest.getData().setUserId(userId);

        if (0 == userService.saveUserInfo(data)) {
            throw new ApiException(Result.VX_ERRO);
        }
        return ApiResponse.createApiResponse(true, Result.SUCCESS);
    }

    /**
     * @Description 根据ID查询用户信息
     * @Date 10:40 2020/10/23
     * @Param [apiRequest]
     * @return com.mini.app.common.entity.ApiResponse<com.mini.app.common.entity.user.User>
     **/
    @PostMapping(value = "/getUserById", produces = "application/json; charset=UTF-8")
    @ValidateArguments(
            validateArguments ={
                    @ValidateArgument(fieldName = "userId")
            })
    @ResponseBody
    public ApiResponse<User> queryUserInfo(@RequestBody ApiRequest<User> apiRequest) {
        Integer userId = apiRequest.getData().getUserId();
        User user = userService.queryUserInfoById(userId);
        return ApiResponse.createApiResponse(user, Result.SUCCESS);
    }

    /**
     * @Description 头像上传
     * @Date 11:50 2020/10/23
     * @Param [fileImg]
     * @return com.mini.app.common.entity.ApiResponse<java.lang.String>
     **/
    @PostMapping("/uploadImg")
    @ResponseBody
    public ApiResponse<String> uploadImg(@RequestParam("file") MultipartFile fileImg) {
        String path = userService.uploadImg(fileImg);
        return ApiResponse.createApiResponse(path, Result.SUCCESS);
    }

    @GetMapping(value = "/imageView/{imgName}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] imgView(@PathVariable("imgName") String imgName) {
        String imgPath = storePath + File.separator + imgName;
        File file = new File(imgPath);

        if (!file.exists()) {
            throw new ApiException(Result.IMG_PATH_ERROR);
        }

        byte[] bytes = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            bytes = new byte[in.available()];
            in.read(bytes, 0, in.available());
            in.close();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
