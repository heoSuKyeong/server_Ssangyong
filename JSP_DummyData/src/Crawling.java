import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {

	public static void main(String[] args) {
		
		
		try {
			
			//접속해서 읽어온 페이지 소스를 관리하는 문서 객체
			Document doc = Jsoup.connect("https://richcat.tistory.com/entry/%EA%B5%AD%EA%B0%80%EC%9E%90%EA%B2%A9%EC%A6%9D-%EB%AA%A9%EB%A1%9D-%EC%A2%85%EB%AA%A9%EC%BD%94%EB%93%9C%EC%88%9C").get();
			
			//System.out.println(doc.html());
			
			//selectFirst : 요소 하나만 찾을 때
			//#content > div > div.entry-content > div.contents_style > table > tbody > tr:nth-child(2) > td:nth-child(4) > span > span
			//#content > div > div.entry-content > div.contents_style > table > tbody > tr:nth-child(2) > td:nth-child(5) > span > span
			//Element h1 = doc.selectFirst("#content > div > div.entry-content > div.contents_style > table > tbody > tr:nth-child(2) > td:nth-child(5) > span > span");
			
			//System.out.println(h1.text());
			
			/*
			//select : 복수형으로 리턴
			Elements item = doc.select(".item > div:nth-child(2)");
			
			for (Element ele :item) {
				System.out.println(ele.text());
			}
			 */
			Elements item = doc.select("#content > div > div.entry-content > div.contents_style > table > tbody>tr");
			for (Element ele :item) {
				Element itemName = ele.selectFirst("td:nth-child(4) > span > span");	//종목명
				Element qualgbCd = ele.selectFirst("td:nth-child(6) > span > span");//자격구분코드
				Element jmCd = ele.selectFirst("td:nth-child(5) > span > span");//종목코드
				
				System.out.printf("%s, %s, %s\n", itemName.text(), qualgbCd.text(), jmCd.text());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
