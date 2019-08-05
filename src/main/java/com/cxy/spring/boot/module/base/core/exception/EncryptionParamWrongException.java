package com.cxy.spring.boot.module.base.core.exception;

/**
 * 加密参数错误.
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-08-05
 */
public class EncryptionParamWrongException extends com.cxy.spring.boot.module.interceptor.core.exception.CommonException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     *
     * @param parameterStr  the error msg
     */
    public EncryptionParamWrongException(String parameterStr) {
        super(416, String.format("加密参数错误[%s]", parameterStr));
    }
}
