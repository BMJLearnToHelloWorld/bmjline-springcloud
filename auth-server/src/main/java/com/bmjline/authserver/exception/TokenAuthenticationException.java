package com.bmjline.authserver.exception;

/**
 * @author bmj
 */
public class TokenAuthenticationException extends Throwable {
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public TokenAuthenticationException() {
        super();
    }

    public TokenAuthenticationException(int code, String message) {
        super(message);
        this.code = code;
    }
}
