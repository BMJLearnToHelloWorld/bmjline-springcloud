package com.bmjline.userserver.controller;

import com.bmjline.common.api.CommonResult;
import com.bmjline.userserver.entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bmj
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public CommonResult<Map<String, Object>> userLogin(@RequestBody UserEntity userEntity, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if ("admin".equals(userEntity.getUsername())) {
            session.setAttribute("token", "admin-token");
            resultMap.put("token", "admin-token");
            return CommonResult.success(resultMap);
        } else {
            return CommonResult.failed();
        }
    }

    @GetMapping("/info")
    public CommonResult<Map<String, Object>> userLogin(@RequestParam("token") String token) {
        if ("admin-token".equals(token)) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            List<String> roles = new ArrayList<String>();
            roles.add("admin");
            resultMap.put("roles", roles);
            resultMap.put("introduction", "I am a super administrator");
            resultMap.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            resultMap.put("name", "Super Admin");
            return CommonResult.success(resultMap);
        } else {
            return CommonResult.failed();
        }
    }

    @PostMapping("/logout")
    public CommonResult<String> userLogout() {
        return CommonResult.success("success");
    }
}
