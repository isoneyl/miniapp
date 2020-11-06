package com.mini.app.home.controller;

import com.mini.app.common.annotation.ValidateArgument;
import com.mini.app.common.annotation.ValidateArguments;
import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.ApiResponse;
import com.mini.app.common.entity.home.Bulletin;
import com.mini.app.common.entity.home.News;
import com.mini.app.common.enums.Result;
import com.mini.app.home.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author liyunlng
 * @ClassName: NewsController
 * @Description:
 * @date 2020/10/27
 */
@Controller
@RequestMapping("home/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping(value = "/addNews", produces = "application/json; charset=UTF-8")
    @ValidateArguments(validateArguments = {
            @ValidateArgument(fieldName = "bulletinTitle"),
            @ValidateArgument(fieldName = "publishOrg"),
            @ValidateArgument(fieldName = "manageId"),
            @ValidateArgument(fieldName = "manageName"),
            @ValidateArgument(fieldName = "content")
    })
    public ApiResponse<Boolean> addNews(@RequestBody ApiRequest<News> apiRequest) {

        News data = apiRequest.getData();
        data.setCreateTime(new Date());
        newsService.addNews(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/updateNews", produces = "application/json; charset=UTF-8")
    @ValidateArguments(validateArguments = {
            @ValidateArgument(fieldName = "id")
    })
    public ApiResponse<Boolean> updateNews(@RequestBody ApiRequest<News> apiRequest) {

        News data = apiRequest.getData();
        newsService.updateNews(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }

    @PostMapping(value = "/delNews", produces = "application/json; charset=UTF-8")
    public ApiResponse<Boolean> delNews(@RequestBody ApiRequest<Integer> apiRequest) {

        Integer data = apiRequest.getData();
        newsService.delNews(data);
        return ApiResponse.createApiResponse(null, Result.SUCCESS);
    }
}
