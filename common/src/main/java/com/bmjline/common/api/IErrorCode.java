package com.bmjline.common.api;

/**
 * 封装API的错误码
 * @author bmj
 */
public interface IErrorCode {
    /**
     * get error code
     *
     * @return long
     */
    long getCode();

    /**
     * get error message
     *
     * @return String
     */
    String getMessage();
}
