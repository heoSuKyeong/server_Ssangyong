package com.test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class Ex04 {
	public static void main(String[] args) {

		//m1();		//네이버 검색하기
		//m2();		//학원 lms 로그인하기
		m3();
		
	}

	private static void m3() {
		
		//자바스크립트에 있는 값 읽어오기
		
		String webDriverID = "webdriver.edge.driver";
		String path = "C:\\class\\dev\\edgedriver_win32\\msedgedriver.exe";
		
		System.setProperty(webDriverID, path);
		
		//ChromeOptions options = new ChromeOptions();
		//options.setCapability("ignoreProtectedModeSettings", true);
		
		//브라우저 참조 객체
		WebDriver driver = new EdgeDriver();
		
		String url = "http://localhost:8090/memo/list.do";
		
		driver.get(url);
		
		WebElement btn1 = driver.findElement(By.id("btn1"));
		btn1.click();
		
		WebElement result = driver.findElement(By.id("result"));
		System.out.println(result.getText());
		
		//jsoup 은 소스를 읽어오는 것이고
		//셀리니움은 현재 실행중인 프로그램을 읽는다.
		
	}

	private static void m2() {
		
		//학원 lms 로그인하기
		
		String webDriverID = "webdriver.edge.driver";
		String path = "C:\\class\\dev\\edgedriver_win32\\msedgedriver.exe";
		
		System.setProperty(webDriverID, path);
		
		//ChromeOptions options = new ChromeOptions();
		//options.setCapability("ignoreProtectedModeSettings", true);
		
		//브라우저 참조 객체
		WebDriver driver = new EdgeDriver();
		
		String url = "http://lms1.sist.co.kr/worknet/SLogin.asp";
		
		driver.get(url);
		
		WebElement id = driver.findElement(By.id("strLoginID"));
		WebElement pw = driver.findElement(By.id("strLoginPwd"));
		
		id.sendKeys("허수경");
		pw.sendKeys("6019");
		
		WebElement btn = driver.findElement(By.cssSelector("#content > div > form > table > tbody > tr > td > div > div.login-form > div.login-btn > input"));
		btn.click();
		
		//페이지 전환될 때 딜레이가 발생하여 그 시간만큼 멈췄다가 밑의 코드를 실행한다.
		try {
			//driver.wait(1000);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//페이지가 넘어가기 전에 실행되어 정보를 읽을 수 없음
		WebElement date = driver.findElement(By.cssSelector("#content > div > div > div > div.panel-body > form > table > thead > tr:nth-child(5) > td:nth-child(2)"));
		//#content > div > div > div > div.panel-body > form > table > thead > tr:nth-child(5) > td:nth-child(2)
		System.out.println(date.getText());
		
	}

	private static void m1() {
		
		//네이버 검색하기
		
		String webDriverID = "webdriver.edge.driver";
		String path = "C:\\class\\dev\\edgedriver_win32\\msedgedriver.exe";
		
		System.setProperty(webDriverID, path);
		
		//ChromeOptions options = new ChromeOptions();
		//options.setCapability("ignoreProtectedModeSettings", true);
		
		//브라우저 참조 객체
		WebDriver driver = new EdgeDriver();
		
		String url = "http://www.naver.com/";
		
		driver.get(url);
		
		//네이버 검색 창으로 셀레니움 검색하기
		WebElement query = driver.findElement(By.id("query"));
		query.sendKeys("셀레니움");
		
		WebElement btn = driver.findElement(By.className("btn_search"));
		btn.click();
		
	}

}
