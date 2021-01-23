package com.bmjline.common.api;

/**
 * 枚举了一些常用API操作码
 */
public enum ResultCode implements IErrorCode {
    /**
     * SUCCESS
     */
    SUCCESS(200, "success"),
    /**
     * FAILED
     */
    FAILED(500, "failed"),
    /**
     * VALIDATE_FAILED
     */
    VALIDATE_FAILED(404, "validate failed"),
    /**
     * UNAUTHORIZED
     */
    UNAUTHORIZED(401, "unauthorized"),
    /**
     * NOT_FOUND
     */
    NOT_FOUND(404, "not found"),
    /**
     * SERVICE_UNAVAILABLE
     */
    SERVICE_UNAVAILABLE(503, "service unavailable"),
    /**
     * FORBIDDEN
     */
    FORBIDDEN(403, "forbidden");
    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
