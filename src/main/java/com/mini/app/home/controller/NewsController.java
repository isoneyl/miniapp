package com.mini.app.home.controller;

import com.mini.app.home.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liyunlng
 * @ClassName: NewsController
 * @Description:
 * @date 2020/10/27
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
}
