package com.springboot.cxy.redis.test.callback;

import com.springboot.cxy.redis.test.callback.CallBack;

public class B {
    public void executeMessage(CallBack callBack, String question){
        System.out.println(callBack.getClass()+"问的问题--》"+question);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result="答案是2";
        callBack.solve(result);
    }
}