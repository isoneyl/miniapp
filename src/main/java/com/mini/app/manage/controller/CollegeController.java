package com.mini.app.manage.controller;

import com.mini.app.common.annotation.ValidateArgument;
import com.mini.app.common.annotation.ValidateArguments;
import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.ApiResponse;
import com.mini.app.common.entity.manage.College;
import com.mini.app.common.entity.manage.ManageUser;
import com.mini.app.common.enums.Result;
import com.mini.app.manage.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author liyunlng
 * @ClassName: CollegeController
 * @Description:
 * @date 2020/11/5
 */
@Controller
@RequestMapping("/manage/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @PostMapping(value = "/addCollege", produces = "application/json; charset=UTF-8")
    @ValidateArguments(validateArguments = {
            @ValidateArgument(fieldName = "collegeName")
    })
    @ResponseBody
    public ApiResponse<Boolean> addCollege(@RequestBody ApiRequest<College> college) {

        College data = college.getData();
        data.setCreateTime(new Date());
        collegeService.addCollege(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/addCollegeUser", produces = "application/json; charset=UTF-8")
    @ValidateArguments(validateArguments = {
            @ValidateArgument(fieldName = "manageId"),
            @ValidateArgument(fieldName = "manageName")
    })
    @ResponseBody
    public ApiResponse<Boolean> addCollegeUser(@RequestBody ApiRequest<College> college) {

        College data = college.getData();
        collegeService.addCollegeUser(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/delCollegeUser", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ApiResponse<Boolean> delCollegeUser(@RequestBody ApiRequest<Integer> college) {

        Integer data = college.getData();
        collegeService.delCollege(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/queryColleges", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ApiResponse<List<College>> queryColleges(@RequestBody ApiRequest<College> apiRequest) {
        Integer pageNo = apiRequest.getPageNo();
        Integer pageSize = apiRequest.getPageSize();
        College data = apiRequest.getData();
        List<College> users = collegeService.queryUsers(pageNo, pageSize, data);
        return ApiResponse.createApiResponse(users, Result.SUCCESS);
    }

}
