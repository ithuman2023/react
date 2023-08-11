package com.tistory.itbaewom.SeleniumProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tistory.itbaewom.SeleniumProject.vo.MapAreaVO;

public class MapArea2 {
	public static void main(String[] args) {

		// 1. 웹드라이버의 경로를 지정해 주어야 한다.
		System.setProperty("webdriver.chrome.driver", new File("src/main/resources/chromedriver.exe").getAbsolutePath());
		
		// 2. Selenium으로 브라우저 열기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized"); // 전체화면으로 실행
		options.addArguments("--disable-popup-blocking"); // 팝업 무시
		options.addArguments("--disable-default-apps"); // 기본앱 사용안함
		options.addArguments("--headless"); // Browser를 띄우지 않음
		WebDriver driver = new ChromeDriver(options);

		// 기본 URL로 리디렉션합니다.
		driver.get("http://www.event-tv.co.kr/core/newsninfo/festivarbooklet" );

		// 암시 적 대기
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		List<MapAreaVO> list = new ArrayList<MapAreaVO>();
		WebElement map = driver.findElement(By.cssSelector("map"));
		for(int i=1;i<=18;i++) {
			// 경기도와 충북이 에러남
			if(i==3 || i==8) continue;
			// 이미지 맵의 특정 부분을 클릭하는 코드
			WebElement area = map.findElement(By.cssSelector("[key=\"map" + String.format("%02d", i) + "\"]"));
			System.out.println(String.format("%02d", i)  + " : "  + area.getAttribute("alt"));
			area.click();
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
			
			MapAreaVO mapAreaVO = mapArea(driver);
			System.out.println(mapAreaVO.getAreaList().size() + "개");
			list.add(mapAreaVO);
		}
		System.out.println(list.size());
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try(PrintWriter pw = new PrintWriter("src/main/resources/map/mainArea.json")){
			gson.toJson(list, pw);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		// 3. Selenium으로 브라우저 닫기(세션종료)
		driver.quit();
	}
	
	private static MapAreaVO mapArea(WebDriver driver) {
		List<WebElement> imgs = driver.findElements(By.cssSelector("p.map_r img"));
		
		MapAreaVO mapAreaVO = new MapAreaVO();
		mapAreaVO.setMainArea(imgs.get(0).getAttribute("alt"));
		mapAreaVO.setMainImg(imgs.get(0).getAttribute("src"));
		
		List<WebElement> areas = driver.findElements(By.cssSelector("p.map_r map area"));
		
		List<MapAreaVO.Area> areaList = new ArrayList<MapAreaVO.Area>();
		for(WebElement e : areas) {
			if(!e.getAttribute("alt").equals("undefined")) {
				MapAreaVO.Area area = new MapAreaVO.Area();
				area.setAlt(e.getAttribute("alt"));
				area.setArea(e.getAttribute("area"));
				area.setCoords(e.getAttribute("coords"));
				area.setCode(e.getAttribute("code"));
				area.setSelimg(e.getAttribute("selimg"));
				area.setShape(e.getAttribute("shape"));
				areaList.add(area);
			}
		}
		mapAreaVO.setAreaList(areaList);
		return mapAreaVO;
	}
}
