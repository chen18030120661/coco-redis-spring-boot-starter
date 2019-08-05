package com.cxy.spring.boot.module.base.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.charset.Charset;

/**
 * 字符编码.
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-08-05
 */
@Getter
@AllArgsConstructor
public enum CharsetCode {
    UTF8("UTF-8");
    private String name;

    public static Charset forUtf8() {
        return Charset.forName(UTF8.getName());
    }
}
