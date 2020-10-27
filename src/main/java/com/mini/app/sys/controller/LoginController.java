package com.mini.app.sys.controller;

import com.mini.app.common.annotation.ValidateArgument;
import com.mini.app.common.annotation.ValidateArguments;
import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.ApiResponse;
import com.mini.app.common.entity.vx.VXCode;
import com.mini.app.common.enums.Result;
import com.mini.app.sys.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @ClassName: LoginController
 * @Description:
 * @date 2020/10/22
 */
@Controller
@RequestMapping("/sys")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @ValidateArguments(
        validateArguments ={
        @ValidateArgument(fieldName = "appid"),
        @ValidateArgument(fieldName = "secret"),
        @ValidateArgument(fieldName = "js_code"),
        @ValidateArgument(fieldName = "grant_type")
    })
    public ApiResponse<Map<String, Object>> loninSys(@RequestBody ApiRequest<VXCode> apiRequest) {
        VXCode data = apiRequest.getData();

        return ApiResponse.createApiResponse(loginService.loninSys(data), Result.SUCCESS);
    }
}
