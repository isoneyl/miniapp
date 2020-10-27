package com.mini.app.home.controller;

import com.mini.app.home.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
