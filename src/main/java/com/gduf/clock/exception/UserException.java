package com.gduf.clock.exception;

public class UserException extends RuntimeException {
    /**
     * 默认构造函数
     */
    public UserException() {
    }

    /**
     * 构造函数
     *
     * @param errMsg 异常消息
     */
    public UserException(String errMsg) {
        super(errMsg);
    }

    /**
     * 构造函数
     *
     * @param cause 原始异常
     */
    public UserException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造函数
     *
     * @param errMsg 异常消息
     * @param cause  原始异常
     */
    public UserException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }
}
