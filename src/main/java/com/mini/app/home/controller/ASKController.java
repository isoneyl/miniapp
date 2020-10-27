package com.mini.app.home.controller;

import com.mini.app.home.service.ASKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liyunlng
 * @ClassName: ASKController
 * @Description:
 * @date 2020/10/27
 */
@Controller
@RequestMapping("/ask")
public class ASKController {
    @Autowired
    private ASKService askService;
}
