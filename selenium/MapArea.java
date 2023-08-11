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
import com.tistory.itbaewom.SeleniumProject.vo.MapVO;

public class MapArea {
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
		
		List<WebElement> areas = driver.findElements(By.cssSelector("p.map_l map area"));
		
		List<MapVO> mapList = new ArrayList<MapVO>();
		for(WebElement e : areas) {
			if(!e.getAttribute("alt").equals("undefined")) {
				MapVO area = new MapVO();
				area.setShape(e.getAttribute("shape"));
				area.setCoords(e.getAttribute("coords"));
				area.setKey(e.getAttribute("key"));
				area.setTourcode(e.getAttribute("tourcode"));
				area.setImg(e.getAttribute("img"));
				area.setArea(e.getAttribute("area"));
				System.out.println(area);
				
				mapList.add(area);
			}
		}
		System.out.println(mapList.size());
		System.out.println("-".repeat(100));
		
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try(PrintWriter pw = new PrintWriter("src/main/resources/map/mainMap.json")){
			gson.toJson(mapList, pw);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// 3. Selenium으로 브라우저 닫기(세션종료)
		driver.quit();
	}
}
