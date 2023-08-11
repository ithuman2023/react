package com.tistory.itbaewom.SeleniumProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tistory.itbaewom.SeleniumProject.vo.MapAreaVO;

public class Ex06 {
	public static void main(String[] args) {
		map03();
		map08();
	}

	private static void map03() {
		// 경기도
		String filename = "src/main/resources/map03.html";
		Document doc=null;
		try {
			File input = new File(filename);
			doc = Jsoup.parse(input, "UTF-8");
			System.out.println(doc.title());
			
			Elements elements = doc.select("p.map_r img");
			System.out.println(elements.size() + "개");
			System.out.println(elements.get(0).attr("src"));
			System.out.println(elements.get(0).attr("alt"));
			
			MapAreaVO mapAreaVO = new MapAreaVO();
			mapAreaVO.setMainArea(elements.get(0).attr("alt"));
			mapAreaVO.setMainImg(elements.get(0).attr("src"));
			
			elements = doc.select("p.map_r map area");
			System.out.println(elements.size() + "개");
			
			List<MapAreaVO.Area> areaList = new ArrayList<MapAreaVO.Area>();
			for(Element e : elements) {
				if(!e.attr("alt").equals("undefined")) {
					MapAreaVO.Area area = new MapAreaVO.Area();
					area.setAlt(e.attr("alt"));
					area.setArea(e.attr("area"));
					area.setCoords(e.attr("coords"));
					area.setCode(e.attr("code"));
					area.setSelimg(e.attr("selimg"));
					area.setShape(e.attr("shape"));
					System.out.println(area);
					
					areaList.add(area);
				}
			}
			mapAreaVO.setAreaList(areaList);
			System.out.println(areaList.size() + "개");
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			try(PrintWriter pw = new PrintWriter("src/main/resources/map/map03.json")){
				gson.toJson(mapAreaVO, pw);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void map08() {
		// 충청북도
		String filename = "src/main/resources/map08.html";
		Document doc=null;
		try {
			File input = new File(filename);
			doc = Jsoup.parse(input, "UTF-8");
			System.out.println(doc.title());
			
			Elements elements = doc.select("p.map_r img");
			System.out.println(elements.size() + "개");
			System.out.println(elements.get(0).attr("src"));
			System.out.println(elements.get(0).attr("alt"));
			
			MapAreaVO mapAreaVO = new MapAreaVO();
			mapAreaVO.setMainArea(elements.get(0).attr("alt"));
			mapAreaVO.setMainImg(elements.get(0).attr("src"));
			
			elements = doc.select("p.map_r map area");
			System.out.println(elements.size() + "개");
			
			List<MapAreaVO.Area> areaList = new ArrayList<MapAreaVO.Area>();
			for(Element e : elements) {
				if(!e.attr("alt").equals("undefined")) {
					MapAreaVO.Area area = new MapAreaVO.Area();
					area.setAlt(e.attr("alt"));
					area.setArea(e.attr("area"));
					area.setCoords(e.attr("coords"));
					area.setCode(e.attr("code"));
					area.setSelimg(e.attr("selimg"));
					area.setShape(e.attr("shape"));
					System.out.println(area);
					
					areaList.add(area);
				}
			}
			mapAreaVO.setAreaList(areaList);
			System.out.println(areaList.size() + "개");
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			try(PrintWriter pw = new PrintWriter("src/main/resources/map/map08.json")){
				gson.toJson(mapAreaVO, pw);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
