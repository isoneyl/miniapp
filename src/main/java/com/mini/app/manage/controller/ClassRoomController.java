package com.mini.app.manage.controller;

import com.mini.app.common.annotation.ValidateArgument;
import com.mini.app.common.annotation.ValidateArguments;
import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.ApiResponse;
import com.mini.app.common.entity.manage.ClassRoom;
import com.mini.app.common.entity.manage.College;
import com.mini.app.common.enums.Result;
import com.mini.app.manage.service.ClassRoomService;
import com.mini.app.manage.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author liyunlng
 * @ClassName: ClassRoomController
 * @Description:
 * @date 2020/11/5
 */
@Controller
@RequestMapping("/manage/class")
public class ClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    @PostMapping(value = "/addClassRoom", produces = "application/json; charset=UTF-8")
    @ValidateArguments(validateArguments = {
            @ValidateArgument(fieldName = "classRoom"),
            @ValidateArgument(fieldName = "collegeId"),
            @ValidateArgument(fieldName = "collegeName")
    })
    @ResponseBody
    public ApiResponse<Boolean> addClassRoom(@RequestBody ApiRequest<ClassRoom> apiRequest) {

        ClassRoom data = apiRequest.getData();
        data.setCreateTime(new Date());
        classRoomService.addClassRoom(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/updateClassRoom", produces = "application/json; charset=UTF-8")
    @ValidateArguments(validateArguments = {
            @ValidateArgument(fieldName = "manageId"),
            @ValidateArgument(fieldName = "manageName")
    })
    @ResponseBody
    public ApiResponse<Boolean> updateClassRoom(@RequestBody ApiRequest<ClassRoom> apiRequest) {

        ClassRoom data = apiRequest.getData();
        classRoomService.updateClassRoom(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/delClassRoom", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ApiResponse<Boolean> delClassRoom(@RequestBody ApiRequest<Integer> apiRequest) {

        Integer data = apiRequest.getData();
        classRoomService.delClassRoom(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/queryClassRooms", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ApiResponse<List<ClassRoom>> queryColleges(@RequestBody ApiRequest<ClassRoom> apiRequest) {
        Integer pageNo = apiRequest.getPageNo();
        Integer pageSize = apiRequest.getPageSize();
        ClassRoom data = apiRequest.getData();
        List<ClassRoom> users = classRoomService.queryUsers(pageNo, pageSize, data);
        return ApiResponse.createApiResponse(users, Result.SUCCESS);
    }
}
