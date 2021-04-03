package com.bmjline.common.util;

import java.util.UUID;

/**
 * @author bmj
 */
public class UuidUtil {

    private UuidUtil() {
        throw new IllegalStateException("UuidUtil class");
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
