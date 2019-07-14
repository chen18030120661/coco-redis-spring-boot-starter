package com.springboot.cxy.redis.test.callback;

/**
 * @类名称 Test
 * @类描述 <pre></pre>
 * @作者 chenxinye chenxinye@tansun.com.cn
 * @创建时间 2019/7/14 16:19
 * @版本 1.00
 * @修改记录 <pre>
 *     版本                       修改人 		修改日期 		 修改内容描述
 *     ----------------------------------------------
 *     1.00 	chenxinye 	2019/7/14
 *     ----------------------------------------------
 * </pre>
 */
public class Test {
    public static void main(String[] args) {
        B b=new B();
        A a=new A(b);
        a.ask("1+1=?");
    }
}
