package com.mini.app.home.controller;

import com.mini.app.common.annotation.ValidateArgument;
import com.mini.app.common.annotation.ValidateArguments;
import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.ApiResponse;
import com.mini.app.common.entity.home.Ask;
import com.mini.app.common.entity.manage.ClassRoom;
import com.mini.app.common.enums.Result;
import com.mini.app.home.service.ASKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author liyunlng
 * @ClassName: ASKController
 * @Description:
 * @date 2020/10/27
 */
@Controller
@RequestMapping("/home/ask")
public class ASKController {

    @Autowired
    private ASKService askService;

    @PostMapping(value = "/addAsk", produces = "application/json; charset=UTF-8")
    @ValidateArguments(validateArguments = {
            @ValidateArgument(fieldName = "publishOrg"),
            @ValidateArgument(fieldName = "manageId"),
            @ValidateArgument(fieldName = "manageName"),
            @ValidateArgument(fieldName = "content")
    })
    public ApiResponse<Boolean> addAsk(@RequestBody ApiRequest<Ask> apiRequest) {

        Ask data = apiRequest.getData();
        data.setCreateTime(new Date());
        askService.addAsk(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/updateAsk", produces = "application/json; charset=UTF-8")
    @ValidateArguments(validateArguments = {
            @ValidateArgument(fieldName = "id")
    })
    public ApiResponse<Boolean> updateAsk(@RequestBody ApiRequest<Ask> apiRequest) {

        Ask data = apiRequest.getData();
        askService.updateAsk(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/delAsk", produces = "application/json; charset=UTF-8")
    public ApiResponse<Boolean> delAsk(@RequestBody ApiRequest<Integer> apiRequest) {

        Integer data = apiRequest.getData();
        askService.delAsk(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }
}
