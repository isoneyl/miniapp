package com.mini.app.home.controller;

import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.ApiResponse;
import com.mini.app.common.entity.home.Apply;
import com.mini.app.common.enums.Result;
import com.mini.app.common.exception.ApiException;
import com.mini.app.home.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liyunlng
 * @ClassName: ApplyController
 * @Description:
 * @date 2020/10/27
 */
@Controller
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    @RequestMapping(value = "/saveApply", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ApiResponse<Apply> saveApply(@RequestBody ApiRequest<Apply> apiRequest) {
        int rows = applyService.addApply(apiRequest.getData());
        if (rows == 0)
            throw new ApiException(Result.SAVE_ERROR);

        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

}
