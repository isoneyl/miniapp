package com.mini.app.sys.controller;

import com.mini.app.common.annotation.ValidateArgument;
import com.mini.app.common.annotation.ValidateArguments;
import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.ApiResponse;
import com.mini.app.common.entity.user.Role;
import com.mini.app.common.entity.user.UserRole;
import com.mini.app.common.enums.Result;
import com.mini.app.common.exception.ApiException;
import com.mini.app.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author liyunlng
 * @ClassName: RoleController
 * @Description:
 * @date 2020/10/31
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * @Description 查询角色
     **/
    @RequestMapping(value = "/addRole", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ApiResponse<List<Role>> queryRole(@RequestBody ApiRequest<Role> request) {

        List<Role> roles = roleService.queryRole(request);

        return ApiResponse.createApiResponse(roles, Result.SUCCESS);
    }

    /**
     * @Description 添加角色
     **/
    @RequestMapping(value = "/addRole", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ApiResponse<Boolean> addRole(@RequestBody ApiRequest<Role> request) {

        int i = roleService.addUserRole(request.getData());

        if (i == 0)
            throw  new ApiException(Result.SAVE_ERROR);

        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    /**
     * @Description 删除角色
     **/
    @RequestMapping(value = "/delRole", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ValidateArguments(
            validateArguments ={
                    @ValidateArgument(fieldName = "id")
            })
    public ApiResponse<Boolean> delRole(@RequestBody ApiRequest<Role> request) {
        Integer id = request.getData().getId();

        int i = roleService.delUserRole(id);
        if (i == 0)
            throw  new ApiException(Result.SAVE_ERROR);

        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    /**
     * @Description 给用户添加角色
     **/
    @RequestMapping(value = "/addUserRole", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ValidateArguments(
            validateArguments ={
                    @ValidateArgument(fieldName = "userId"),
                    @ValidateArgument(fieldName = "roleId")
            })
    public ApiResponse<Boolean> addUserByRole(@RequestBody ApiRequest<UserRole> request) {

        int i = roleService.addUserByRole(request.getData());
        if (i == 0)
            throw  new ApiException(Result.SAVE_ERROR);

        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    /**
     * @Description 给用户添加角色
     **/
    @RequestMapping(value = "/queryUserPermission", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ValidateArguments(
            validateArguments ={
                    @ValidateArgument(fieldName = "data", isNumberType = true)
            })
    public ApiResponse<Boolean> queryUserPermission(@RequestBody ApiRequest<Integer> request) {

        return ApiResponse.createApiResponse(
                roleService.queryRoleByUserId(request.getData()), Result.SUCCESS);

    }
}
