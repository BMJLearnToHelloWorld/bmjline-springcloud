package com.bmjline.adminserver.controller;

import com.bmjline.adminserver.service.UserService;
import com.bmjline.adminserver.util.BcryptUtil;
import com.bmjline.common.api.CommonResult;
import com.bmjline.common.entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bmj
 */
@RestController
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/login")
    public CommonResult<Map<String, Object>> userLogin(@RequestBody UserEntity userEntity) {
        Map<String, Object> resultMap = new HashMap<String, Object>(16);
        if (BcryptUtil.match(userEntity.getPassword(), userService.getPassByUsername(userEntity.getUsername()))) {
            resultMap.put("token", "admin-token");
            return CommonResult.success(resultMap);
        } else {
            return CommonResult.failed("login failed");
        }
    }

    @GetMapping("/user/info")
    public CommonResult<Map<String, Object>> userLogin(@RequestParam("token") String token) {
        if ("admin-token".equals(token)) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            List<String> roles = new ArrayList<String>();
            roles.add("admin");
            resultMap.put("roles", roles);
            resultMap.put("introduction", "I am a super administrator");
            resultMap.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            resultMap.put("name", "Admin");
            return CommonResult.success(resultMap);
        } else {
            return CommonResult.failed();
        }
    }

    @PostMapping("/user/logout")
    public CommonResult<String> userLogout() {
        return CommonResult.success("success");
    }
}
