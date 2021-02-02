package com.bmjline.adminserver.controller;

import com.bmjline.adminserver.entity.UserInfoEntity;
import com.bmjline.adminserver.service.UserService;
import com.bmjline.adminserver.util.BcryptUtil;
import com.bmjline.adminserver.util.HttpUtil;
import com.bmjline.authserver.exception.TokenAuthenticationException;
import com.bmjline.authserver.util.JwtUtil;
import com.bmjline.common.api.CommonResult;
import com.bmjline.common.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    public CommonResult<Map<String, Object>> userLogin(@RequestBody UserEntity userEntity, HttpServletRequest request) {
        String remoteAddr = HttpUtil.getIpAddr(request);
        Map<String, Object> resultMap = new HashMap<String, Object>(16);
        String userId = userService.getUserIdByUsername(userEntity.getUsername());
        if (StringUtils.isEmpty(userId)) {
            return CommonResult.failed("username wrong, please check!");
        }
        if (BcryptUtil.match(userEntity.getPassword(), userService.getPassByUsername(userEntity.getUsername()))) {
            String token = JwtUtil.generateToken(userId, userId);
            if (StringUtils.isNotEmpty(token)) {
//                String uuidKey = UuidUtil.generateUuid();
                resultMap.put("token", token);
                return CommonResult.success(resultMap);
            }
        }
        return CommonResult.failed("login failed");
    }

    @GetMapping("/user/info")
    public CommonResult<UserInfoEntity> userLogin(@RequestHeader("X-Token") String xToken) {
        String userId = JwtUtil.getUserId(xToken);
        if (StringUtils.isEmpty(userId)) {
            return CommonResult.failed("authentication failed");
        }
        try {
            JwtUtil.verifyToken(xToken, userId);
        } catch (TokenAuthenticationException e) {
            return CommonResult.failed(e.getMessage());
        }
        UserInfoEntity userInfoEntity = userService.getUserInfoByUserId(userId);
        userInfoEntity.setRoles(userService.getRolesByUserId(userId));
        return CommonResult.success(userInfoEntity);
    }

    @PostMapping("/user/logout")
    public CommonResult<String> userLogout() {
        return CommonResult.success("success");
    }
}
