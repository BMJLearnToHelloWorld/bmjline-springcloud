package com.bmjline.common.util;

import java.util.UUID;

public class UuidUtil {

    public static String generateUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}