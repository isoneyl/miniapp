package com.mini.app.home.controller;

import com.mini.app.common.annotation.ValidateArgument;
import com.mini.app.common.annotation.ValidateArguments;
import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.ApiResponse;
import com.mini.app.common.entity.home.Ask;
import com.mini.app.common.entity.home.Bulletin;
import com.mini.app.common.enums.Result;
import com.mini.app.home.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author liyunlng
 * @ClassName: BulletinController
 * @Description:
 * @date 2020/10/27
 */
@Controller
@RequestMapping("home/bulletin")
public class BulletinController {

    @Autowired
    private BulletinService bulletinService;

    @PostMapping(value = "/addBulletin", produces = "application/json; charset=UTF-8")
    @ValidateArguments(validateArguments = {
            @ValidateArgument(fieldName = "bulletinTitle"),
            @ValidateArgument(fieldName = "publishOrg"),
            @ValidateArgument(fieldName = "manageId"),
            @ValidateArgument(fieldName = "manageName"),
            @ValidateArgument(fieldName = "content")
    })
    public ApiResponse<Boolean> addBulletin(@RequestBody ApiRequest<Bulletin> apiRequest) {

        Bulletin data = apiRequest.getData();
        data.setCreateTime(new Date());
        bulletinService.addBulletin(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/updateBulletin", produces = "application/json; charset=UTF-8")
    @ValidateArguments(validateArguments = {
            @ValidateArgument(fieldName = "id")
    })
    public ApiResponse<Boolean> updateBulletin(@RequestBody ApiRequest<Bulletin> apiRequest) {

        Bulletin data = apiRequest.getData();
        bulletinService.updateBulletin(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/delBulletin", produces = "application/json; charset=UTF-8")
    public ApiResponse<Boolean> delBulletin(@RequestBody ApiRequest<Integer> apiRequest) {

        Integer data = apiRequest.getData();
        bulletinService.delBulletin(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

}
