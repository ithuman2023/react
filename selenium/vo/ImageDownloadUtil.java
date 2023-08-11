package com.tistory.itbaewom.SeleniumProject.vo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloadUtil {
	public static void ImageDownload(String urlAddress, String path, String fileName) {
		URL url = null;
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			url = new URL(urlAddress);
			// 경로가 없으면 만든다.
			File file = new File(path);
			if(!file.exists()) file.mkdirs();
			
			fos = new FileOutputStream(path + fileName);
			is = url.openStream();
			byte[] bytes = new byte[2048000];
			int n = 0;
			while((n=is.read(bytes))>0) {
				fos.write(bytes, 0, n);
				fos.flush();
			}
			//System.out.println(fileName + "저장 완료!!!");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is!=null) is.close();
				if(fos!=null) fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
