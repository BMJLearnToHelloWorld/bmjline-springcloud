package com.bmjline.adminserver.util;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author bmj
 */
public class VerifyCodeUtil {

    private VerifyCodeUtil() {
        throw new IllegalStateException("VerifyCodeUtil class");
    }

    public static String randomString(String baseString, int length) {
        if (StringUtils.isEmpty(baseString)) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder(length);
            if (length < 1) {
                length = 1;
            }

            int baseLength = baseString.length();

            for (int i = 0; i < length; ++i) {
                int number = randomInt(baseLength);
                sb.append(baseString.charAt(number));
            }

            return sb.toString();
        }
    }

    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    public static int randomInt(int limit) {
        return getRandom().nextInt(limit);
    }

}
