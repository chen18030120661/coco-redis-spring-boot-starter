package com.springboot.cxy.redis.util;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

public class SpelUtil {

    /**
     * 支持 #p0 参数索引的表达式解析
     *
     * @param joinPoint
     * @param cacheKey
     * @return
     */
    public static String parse(ProceedingJoinPoint joinPoint, String cacheKey) {
        //获取当前切面方法的形参
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();//获取对应方法
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);//spring提供的获取方法中形参的函数
        Object[] args = joinPoint.getArgs();
        return getRequest(cacheKey, parameterNames, args);
    }

    /**
     * 通过spring Spel 获取参数
     *
     * @param key            定义的key值 以#开头 例如:#user
     * @param parameterNames 形参
     * @param values         形参值
     * @return
     */
    public static String getRequest(String key, String[] parameterNames, Object[] values) {
        //spel解析器
        ExpressionParser parser = new SpelExpressionParser();
        //spel上下文
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], values[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }
}
