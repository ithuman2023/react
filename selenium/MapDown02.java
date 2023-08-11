package com.tistory.itbaewom.SeleniumProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tistory.itbaewom.SeleniumProject.vo.ImageDownloadUtil;
import com.tistory.itbaewom.SeleniumProject.vo.MapAreaVO;

public class MapDown02 {
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		String baseURL = "http://www.event-tv.co.kr/";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<MapAreaVO> mapList = null;
		try(FileReader fr = new FileReader("src/main/resources/map/mainAreaAll.json")){
			mapList = gson.fromJson(fr, new TypeToken<List<MapAreaVO>>() {}.getType());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(mapList.size());
		for(int i=0;i<mapList.size();i++) {
			File file = new File("src/main/resources/map/" + mapList.get(i).getMainArea() + "/");
			if(!file.exists()) file.mkdirs();
			System.out.println(mapList.get(i).getMainArea());
			
			String filename = mapList.get(i).getMainImg().substring(mapList.get(i).getMainImg().lastIndexOf("/")+1);
			System.out.println("src/main/resources/map/" + mapList.get(i).getMainArea() + "/" + filename + "저장완료!!");
			
			ImageDownloadUtil.ImageDownload(mapList.get(i).getMainImg(), "src/main/resources/map/" + mapList.get(i).getMainArea() + "/", filename);
			for(MapAreaVO.Area vo : mapList.get(i).getAreaList()) {
				filename = vo.getSelimg().substring(vo.getSelimg().lastIndexOf("/")+1);
				ImageDownloadUtil.ImageDownload(baseURL+vo.getSelimg(), "src/main/resources/map/" + mapList.get(i).getMainArea() + "/", filename);
				System.out.println(filename + "저장완료!!");
			}
		}
	}
}
