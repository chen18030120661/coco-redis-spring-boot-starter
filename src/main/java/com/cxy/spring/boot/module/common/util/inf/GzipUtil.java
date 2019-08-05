package com.cxy.spring.boot.module.common.util.inf;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public final class GzipUtil {

    // 解压缩
    public static String uncompress(InputStream gzippedResponse) throws IOException {

        InputStream decompressedResponse = new GZIPInputStream(gzippedResponse);
        Reader reader = new InputStreamReader(decompressedResponse, "UTF-8");
//        Reader reader = new InputStreamReader(gzippedResponse, "UTF-8");
        StringWriter writer = new StringWriter();
        int count = 0;
        char[] buffer = new char[10240];
        for(int length = 0; (length = reader.read(buffer)) > 0;){
            count=count+length;
            writer.write(buffer, 0, length);
        }
        writer.close();
        reader.close();
        decompressedResponse.close();
        gzippedResponse.close();

        return writer.toString();
    }

    public static void main(String[] args) throws IOException {

         URL url = new URL("https://test.xinyan.com/data/carrier/v3/zip/mobile/201806051801110125001923?mobile=");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("memberId", "8000013189");
        connection.setRequestProperty("terminalId", "8000013189");
        connection.connect();
        System.out.println(uncompress(connection.getInputStream()));
        connection.disconnect();

    }


}
