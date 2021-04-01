package com.bmjline.common.util;

import java.util.UUID;

/**
 * @author bmj
 */
public class UuidUtil {

    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
