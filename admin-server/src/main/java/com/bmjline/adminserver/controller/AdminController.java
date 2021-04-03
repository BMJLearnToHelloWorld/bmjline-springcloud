package com.bmjline.adminserver.controller;

import com.bmjline.adminserver.util.Md5Util;
import com.bmjline.adminserver.util.VerifyCodeUtil;
import com.bmjline.adminserver.util.VerifyImageUtil;
import com.bmjline.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * @author bmj
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final StringRedisTemplate stringRedisTemplate;

    private static final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";

    public AdminController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Value("${token.expire}")
    private long tokenExpireNumber;

    /**
     * 后台生成图形验证码 ：有效
     *
     * @param response
     * @param key
     */
    @GetMapping(value = "/verifyImage/{key}")
    public CommonResult<String> randomImage(HttpServletResponse response, @PathVariable String key) {
        // 获取4个字符
        String code = VerifyCodeUtil.randomString(BASE_CHECK_CODES, 4);
        String lowerCaseCode = code.toLowerCase();
        String realKey = Md5Util.encode(lowerCaseCode + key);
        stringRedisTemplate.opsForHash().put(realKey, key, lowerCaseCode);
        stringRedisTemplate.expire(realKey, tokenExpireNumber / 6, TimeUnit.HOURS);
        String base64 = null;
        try {
            base64 = VerifyImageUtil.generate(code);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return CommonResult.success(base64);
    }

}
