package com.cxy.spring.boot.module.base.format.encrypt;

import com.cxy.spring.boot.module.base.core.enums.FormatProperty;
import com.cxy.spring.boot.module.base.core.exception.EncryptionParamWrongException;
import com.cxy.spring.boot.module.base.util.AnnotationUtil;
import com.cxy.spring.boot.module.interceptor.util.DES3Util;
import com.cxy.spring.boot.module.interceptor.util.HttpHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 请求体加密解密.
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-08-05
 */
@Slf4j
@ControllerAdvice
public class RequestBodyEncryptHandler extends RequestBodyAdviceAdapter implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        final Class<?> declaringClass = Objects.requireNonNull(methodParameter.getMethod()).getDeclaringClass();
        if (!methodParameter.hasMethodAnnotation(Encrypt.class) && !AnnotationUtil.hasAnnotation(declaringClass, Encrypt.class)) {
            return false;
        }
        return methodParameter.hasParameterAnnotation(RequestBody.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return new HttpInputMessage() {
            @Override
            public InputStream getBody() throws IOException {
                final String requestBodyString = HttpHelper.getStringBody(httpInputMessage.getBody());
                final String requestParam = DES3Util.decryptMode(requestBodyString, FormatProperty.des(FormatProperty.DES_PARAM));
                log.debug("body ==>[{}]", requestParam);
                if (StringUtils.isBlank(requestParam)) {
                    throw new EncryptionParamWrongException(requestBodyString);
                }
                return new ByteArrayInputStream(requestParam.getBytes(StandardCharsets.UTF_8));
            }

            @Override
            public HttpHeaders getHeaders() {
                return httpInputMessage.getHeaders();
            }
        };
    }
}
