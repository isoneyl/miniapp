package com.mini.app.manage.controller;

import com.mini.app.common.annotation.ValidateArgument;
import com.mini.app.common.annotation.ValidateArguments;
import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.ApiResponse;
import com.mini.app.common.entity.manage.College;
import com.mini.app.common.entity.manage.ManageUser;
import com.mini.app.common.enums.Result;
import com.mini.app.manage.service.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author liyunlng
 * @ClassName: ManageUser
 * @Description:
 * @date 2020/11/6
 */
@Controller
@RequestMapping(value = "/manage/class")
public class ManageUserController {

    @Autowired
    private ManageUserService manageUserService;

    @PostMapping(value = "addManageUser", produces = "application/json; charset=UTF-8")
    @ValidateArguments(validateArguments = {
            @ValidateArgument(fieldName = "manageAccound"),
            @ValidateArgument(fieldName = "manageName"),
            @ValidateArgument(fieldName = "phone"),
            @ValidateArgument(fieldName = "managePwd"),
            @ValidateArgument(fieldName = "gender")
    })
    public ApiResponse<Boolean> addManageUser(@RequestBody ApiRequest<ManageUser> college) {

        ManageUser data = college.getData();
        data.setCreateTime(new Date());
        manageUserService.addManageUser(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/updateManageUser", produces = "application/json; charset=UTF-8")
    @ValidateArguments(validateArguments = {
            @ValidateArgument(fieldName = "manageId")
    })
    public ApiResponse<Boolean> updateManageUser(@RequestBody ApiRequest<ManageUser> college) {

        ManageUser data = college.getData();
        manageUserService.updateManageUser(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/delManageUser", produces = "application/json; charset=UTF-8")
    public ApiResponse<Boolean> delManageUser(@RequestBody ApiRequest<Integer> college) {

        Integer data = college.getData();
        manageUserService.delCollege(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }
}
