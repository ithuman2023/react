package com.tistory.itbaewom.SeleniumProject;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Itbaewom1 {
	public static void main(String[] args) {
		Random rnd = new Random();
		// 1. 웹드라이버의 경로를 지정해 주어야 한다.
		System.setProperty("webdriver.chrome.driver",
				new File("src/main/resources/chromedriver.exe").getAbsolutePath());

		// 2. Selenium으로 브라우저 열기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-popup-blocking"); // 팝업 무시
		options.addArguments("--disable-default-apps"); // 기본앱 사용안함
		options.addArguments("--headless"); // Browser를 띄우지 않음
		
		for (int x = 0; x < 123; x++) {
			try {
				WebDriver driver = new ChromeDriver(options);
				// 기본 URL로 리디렉션합니다.
				driver.get("https://itbaewom.tistory.com/" + (rnd.nextInt(390) + 15));
				// 브라우저 창을 최대화합니다.
				// driver.manage().window().maximize() ;
				// 암시 적 대기
				driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
				// iframe태그를 찾는다.
				List<WebElement> iframeList = driver.findElements(By.cssSelector("iframe"));
				System.out.println(iframeList.size() + "개");

				if (iframeList.size() > 1) {
					for (WebElement we : iframeList) {
						try {
							WebDriver wd = driver.switchTo().frame(we.getAttribute("name"));
							System.out.println(wd.getTitle());
							WebElement adLink = wd.findElement(By.cssSelector("a"));
							int count = rnd.nextInt(4) + 3;
							for (int j = 0; j < count; j++) {
								adLink.click();
								Thread.sleep(rnd.nextInt(2500));
							}
							// wd.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
						} catch (Exception e) {
							;
						}
					}
				}
				driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
				// 3. Selenium으로 브라우저 닫기(세션종료)
				driver.quit();
				try {
					Thread.sleep(rnd.nextInt(2000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				System.out.println("에러 : " + e.getMessage());
			}
		}
	}
}
