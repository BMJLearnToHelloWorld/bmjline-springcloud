package com.bmjline.adminserver.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author bmj
 */
public class BcryptUtil {
    /**
     * 对密码进行加密
     *
     * @param password
     * @return
     */
    public static String encode(String password) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(password);
        return hashPass;
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
        boolean result = bcryptPasswordEncoder.matches(password, encodedPassword);
        return result;
    }

//    public static void main(String[] args) {
//        String hashPass = encode("2963h5IA7XGK1ezIW4IU2g==");
//        System.out.println(hashPass);
//        System.out.println(match("2963h5IA7XGK1ezIW4IU2g==", hashPass));
//        System.out.println(match("2963h5IA7XGK1ezIW4IU2g==", "$2a$10$JJFqI09wr9O9IECCZAPjHuQqROswx30OXdC/m7OASg6909TD/vzLe"));
//    }
}
