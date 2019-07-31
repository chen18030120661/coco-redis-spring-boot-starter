package com.springboot.cxy.redis.test.redis;

import com.alibaba.fastjson.JSON;

public class Test {
    public static void main(String[] args) {
//        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=0f72dc161a83f32b5bed68c4313713bb934b5244c43533284dd2b42571f69a43");
//        OapiRobotSendRequest request = new OapiRobotSendRequest();
//        request.setMsgtype("text");
//        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
//        text.setContent("测试文本消息");
//        request.setText(text);
//        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//        at.setAtMobiles(Arrays.asList("13261303345"));
//        request.setAt(at);
//
//        request.setMsgtype("link");
//        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
//        link.setMessageUrl("https://www.dingtalk.com/");
//        link.setPicUrl("");
//        link.setTitle("时代的火车向前开");
//        link.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" +
//                "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林");
//        request.setLink(link);
//
//        request.setMsgtype("markdown");
//        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
//        markdown.setTitle("杭州天气");
//        markdown.setText("#### 陈盼 @156xxxx8827\n" +
//                "> 下班不积极，脑袋有问题\n\n" +
//                "> ![screenshot](https://gw.alipayobjects.com/zos/skylark-tools/public/files/84111bbeba74743d2771ed4f062d1f25.png)\n"  +
//                "> ###### 18点04分发布 [神秘来客](http://www.thinkpage.cn/) \n");
//        request.setMarkdown(markdown);
//        try {
//            OapiRobotSendResponse response = client.execute(request);
//        } catch (ApiException e) {
//            e.printStackTrace();
//        }
//        String timeRegex = "  ";
//        String timeRegex = "^\\d+$";
//        System.out.println(timeRegex);
//        boolean matches = "125a22".matches(timeRegex);
//        System.out.println(matches);
    testAa("dasda","d2da","d2dsdaa");
    }
    public static void testAa(String... params){
        for (String param : params) {
            System.out.println(param);
        }
    }
}
