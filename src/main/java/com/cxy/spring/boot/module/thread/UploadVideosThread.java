package com.cxy.spring.boot.module.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 线程
 */
public class UploadVideosThread implements Runnable{

	public static final Logger logger = LoggerFactory.getLogger(UploadVideosThread.class);

	private String folder;

	private Date currDate;

	public UploadVideosThread(String folder, Date currDate) {
		this.folder = folder;
		this.currDate = currDate;
    }

	@Override
	public void run() {
		//上传视频到白山云
//		AWSUtil.uploadVideos(videoFile, String.valueOf(videosVo.getUserId()), folder, currDate);
	}

}
