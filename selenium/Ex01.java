package com.tistory.itbaewom.SeleniumProject;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Ex01 {
	public static void main(String[] args) {
		// 기본적으로 Selenium 4는 Chrome v75 이상과 호환됩니다. Chrome 브라우저 버전과 chromedriver 버전이 주 버전과 일치해야 합니다.

		// 1. 웹드라이버의 경로를 지정해 주어야 한다.
		System.setProperty("webdriver.chrome.driver", new File("src/main/resources/chromedriver.exe").getAbsolutePath());
		// 2. Selenium으로 브라우저 열기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);

		// 3. Selenium으로 브라우저 닫기
		driver.quit();
	}
}
