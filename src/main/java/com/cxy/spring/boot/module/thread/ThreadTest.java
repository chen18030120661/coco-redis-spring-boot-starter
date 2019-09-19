package com.cxy.spring.boot.module.thread;

import java.util.Date;

/**
 * 调用案例
 */
public class ThreadTest {
    public static void main(String[] args) {
        ThreadPoolManager.getInstance().execute(new UploadVideosThread("folder",new Date()));
    }
}
