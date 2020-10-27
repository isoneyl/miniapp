package com.mini.app.home.controller;

import com.mini.app.home.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liyunlng
 * @ClassName: BulletinController
 * @Description:
 * @date 2020/10/27
 */
@Controller
@RequestMapping("/bulletin")
public class BulletinController {

    @Autowired
    private BulletinService bulletinService;
}
