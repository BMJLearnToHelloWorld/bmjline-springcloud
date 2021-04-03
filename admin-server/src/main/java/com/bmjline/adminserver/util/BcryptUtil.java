package com.bmjline.adminserver.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author bmj
 */
public class BcryptUtil {

    private BcryptUtil() {
        throw new IllegalStateException("BcryptUtil class");
    }

    /**
     * 对密码进行加密
     *
     * @param password
     * @return
     */
    public static String encode(String password) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        return bcryptPasswordEncoder.encode(password);
    }

    /**
     * 对原密码和已加密的密码进行匹配，判断是否相等
     *
     * @param password
     * @param encodedPassword
     * @return
     */
    public static boolean match(String password, String encodedPassword) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        return bcryptPasswordEncoder.matches(password, encodedPassword);
    }

}
