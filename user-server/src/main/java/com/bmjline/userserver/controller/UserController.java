package com.bmjline.userserver.controller;

import com.bmjline.common.api.CommonResult;
import com.bmjline.userserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody Map<String, Object> req) {
        try {
            userService.login(req);
            return CommonResult.success("success");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.success("failed");
        }
    }
}
