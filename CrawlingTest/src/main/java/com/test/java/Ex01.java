package com.test.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ex01 {
	
	public static void main(String[] args) {
		
		//메모 프로젝트 크롤링
		
		try {
			
			//접속해서 읽어온 페이지 소스를 관리하는 문서 객체
			Document doc = Jsoup.connect("http://localhost:8090/memo/list.do").get();
			
			//System.out.println(doc.html());
			
			//selectFirst : 요소 하나만 찾을 때
			Element h1 = doc.selectFirst("body > h1");
			
			System.out.println(h1.text());
			
			//select : 복수형으로 리턴
			Elements item = doc.select(".item > div:nth-child(2)");
			
			for (Element ele :item) {
				System.out.println(ele.text());
			}
			
			Element result = doc.selectFirst("#result");
			System.out.println("result: " + result.text());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
