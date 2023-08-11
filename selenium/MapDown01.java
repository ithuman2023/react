package com.tistory.itbaewom.SeleniumProject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tistory.itbaewom.SeleniumProject.vo.ImageDownloadUtil;
import com.tistory.itbaewom.SeleniumProject.vo.MapVO;

public class MapDown01 {
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		String baseURL = "http://www.event-tv.co.kr/";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<MapVO> mapList = null;
		try(FileReader fr = new FileReader("src/main/resources/map/mainMap.json")){
			mapList = gson.fromJson(fr, new TypeToken<List<MapVO>>() {}.getType());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(mapList.size());
		for(MapVO vo : mapList) {
			String filename = vo.getImg().substring(vo.getImg().lastIndexOf("/")+1);
			ImageDownloadUtil.ImageDownload(baseURL+vo.getImg(), "src/main/resources/map/", filename);
			System.out.println(filename + "저장완료!!");
		}
	}
}
