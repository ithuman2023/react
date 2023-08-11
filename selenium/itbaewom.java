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

// 기본적으로 Selenium 4는 Chrome v75 이상과 호환됩니다. Chrome 브라우저 버전과 chromedriver 버전이 주 버전과 일치해야 합니다.

/*
왜 우리는 셀레늄을 기다릴 필요가 있습니까?
대부분의 웹 애플리케이션은 Ajax와 Javascript를 사용하여 개발되었습니다. 
페이지가 브라우저에 의해로드 될 때 상호 작용하려는 요소는 다른 시간 간격으로로드 될 수 있습니다.
요소 식별이 어려울뿐 아니라 요소가 없으면 " ElementNotVisibleException"예외가 발생합니다. Waits를 사용하여이 문제를 해결할 수 있습니다.
 */
public class itbaewom {
	public static void main(String[] args) {

		for (int i = 0; i < 28; i++) {
			// 1. 웹드라이버의 경로를 지정해 주어야 한다.
			System.setProperty("webdriver.chrome.driver",
					new File("src/main/resources/chromedriver.exe").getAbsolutePath());

			// 2. Selenium으로 브라우저 열기
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-popup-blocking"); // 팝업 무시
			options.addArguments("--disable-default-apps"); // 기본앱 사용안함
			// options.addArguments("--headless"); // Browser를 띄우지 않음
			WebDriver driver = new ChromeDriver(options);

			// 기본 URL로 리디렉션합니다.
			Random rnd = new Random();
			driver.get("https://itbaewom.tistory.com/" + (rnd.nextInt(200) + 100));
			// 브라우저 창을 최대화합니다.
			driver.manage().window().maximize();
			// 암시 적 대기
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
			List<WebElement> iframes = driver.findElements(By.cssSelector("iframe"));
			System.out.println(iframes.size());
			if (iframes.size() > 0) {
				// 찾은 버튼을 클릭 한다.
				// adLink.click();
				try {
					for (WebElement e : iframes) {
						// System.out.println(e.getAttribute("name"));
						// System.out.println(e.getAttribute("id"));
						WebDriver wd = driver.switchTo().frame(e.getAttribute("name"));
						// System.out.println(wd.getTitle());
						// System.out.println(wd.getPageSource());
						WebElement we = wd.findElement(By.cssSelector("a"));
						// System.out.println(we.getTagName());
						// System.out.println(we.getText());
						we.click();
						we.click();
						we.click();
						//System.out.println("하하하하");
					}
				} catch (Exception e) {
					// System.out.println(e.getMessage());
				}
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
			// 3. Selenium으로 브라우저 닫기(세션종료)
			// driver.quit();
			
			try {
				Thread.sleep(rnd.nextInt(10000) + 10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
