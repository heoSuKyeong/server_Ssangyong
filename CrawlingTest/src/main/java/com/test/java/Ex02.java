package com.test.java;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ex02 {
	public static void main(String[] args) {
		
		// 다음 영화 랭킹 크롤링
		
		try {
			
			String url = "https://movie.daum.net/ranking/boxoffice/weekly";
			
			Document doc = Jsoup.connect(url).get();
			
			//하나의 영화 정보를 담고 있는 list
			Elements list = doc.select(".item_poster");
			//System.out.println(list.size());

			//영화 제목 찍어보기
			for (Element movie : list) {
				//select : movie라는 div태그 안에서 찾는다.
				Element title = movie.selectFirst(".link_txt");
				Element date = movie.selectFirst(".txt_num");
				Element count = movie.selectFirst(".screen_out");
			
				System.out.printf("[%s] 개봉일 : %s\n", title.text(), date.text());
				System.out.println(count.nextSibling());
				
				Element poster = movie.selectFirst(".img_thumb");
				if (poster != null) {
					System.out.println(poster.attr("src"));
					getImage(poster.attr("src"), title.text());
					
					//Thread.sleep(2000); 	//여기서 잠시 멈추세요.
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}//main
	
	private static void getImage(String imgUrl, String title) {
		
		URL url = null;
		InputStream in = null;
		OutputStream out = null;
		
		try {
			
			url = new URL(imgUrl);
			in = url.openStream(); 	//파일 읽기
			out = new FileOutputStream("C:\\class\\code\\server\\CrawlingTest\\poster\\" + title + ".png"); 	//파일 저장하기(쓰기)
			
			//이미지를 읽고 하드디스크에 저장
			while(true) {
				int data = in.read();
				if (data == -1) break;
				out.write(data);
			}
			
			in.close();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
	}
	
}
