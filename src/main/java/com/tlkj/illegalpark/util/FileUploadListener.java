package com.tlkj.illegalpark.util;

import org.apache.commons.fileupload.ProgressListener;

public class FileUploadListener implements ProgressListener{

	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		// TODO Auto-generated method stub
		//pBytesRead 已经上传多少字节  
        //pContentLength 一共多少字节  
        //pItems 正在上传第几个文件  
        System.out.println(pBytesRead +"\t" + pContentLength +"\t" + pItems); 
	}

}
