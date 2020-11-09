package com.mini.app.sys.controller;

import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.ApiResponse;
import com.mini.app.common.entity.user.Role;
import com.mini.app.common.enums.Result;
import com.mini.app.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author liyunlng
 * @ClassName: RoleController
 * @Description:
 * @date 2020/11/9
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/getRoles", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ApiResponse<List<Role>> queryRoles(@RequestBody ApiRequest<Role> apiRequest) {
        List<Role> roles = roleService.queryRole(apiRequest);
        return ApiResponse.createApiResponse(roles, Result.SUCCESS);
    }

}
