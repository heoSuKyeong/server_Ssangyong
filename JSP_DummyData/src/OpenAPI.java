import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OpenAPI {

	public static void main(String[] args) throws IOException {
		
		//int[] code = {9500,9501,9502,9503,9624,9625,9626,9627,9628,9629,9630,9631,9632,9633,9634,9635,9640,9641,9653,9654,9655,9657,9658,9659,9660,9661,9662,9663,9664,9665,9666,9667,9668,9669,9670,9671,9672,9673,9674,9675,9676,9677,9678,9679,9680,9681,9682,9683,9684,9685,9686,9687,9688,9689,9690,9691,9692,9693,9694,9695,9696,9697,9698,9699,9700,9701,9702,9703,9704,9705,9706,9711,9712,9713,9714,9715,9716,9721,9722,9723,9728,9729,9730,9731,9732,9733,9734,9735,9736,9737,9738,9739,9740,9741,9744,9745,9746,9747,9748,9749,9750,9751,9752,9753,9754,9755,9756,9757,9762,9763,9800};
		int[] code = {11,12,50,60,71,80,94,110,120,130,210,230,250,261,270,301,320,360,370,390,400,410,420,430,431,432,451,460,470,480,481,490,501,502,510,511,551,561,570,575,591,601,622,650,670,680,690,700,701,702,710,720,730,740,750,751,752,760,770,780,790,800,801,802,810,825,840,841,851,872,875,891,920,930,938,940,948,950,951,960,970,1021,1022,1023,1024,1025,1027,1028,1029,1030,1040,1048,1050,1051,1060,1070,1080,1104,1110,1114,1121,1130,1140,1150,1160,1170,1175,1176,1177,1178,1179,1195,1220,1240,1250,1282,1297,1301,1320,1321,1322,1325,1340,1350,1351,1370,1380,1390,1391,1401,1402,1403,1431,1440,1441,1442,1443,1450,1471,1472,1500,1511,1512,1513,1530,1540,1541,1550,1555,1560,1562,1563,1564,1570,1575,1576,1581,1590,1592,1600,1601,1611,1612,1613,1614,1615,1617,1625,1630,1632,1633,1641,1661,1662,1663,1730,1740,1741,1750,1751,1760,1780,1790,1800,1837,1875,1900,1910,1938,1948,1950,1982,1988,1989,2011,2012,2025,2026,2027,2028,2029,2030,2031,2034,2035,2036,2037,2040,2041,2045,2047,2048,2050,2051,2070,2085,2090,2101,2104,2105,2106,2107,2108,2114,2120,2121,2130,2135,2140,2142,2150,2160,2170,2175,2176,2177,2193,2195,2220,2230,2240,2251,2253,2264,2268,2277,2282,2290,2301,2302,2320,2324,2325,2330,2340,2350,2351,2381,2390,2391,2395,2420,2431,2432,2434,2441,2450,2470,2471,2472,2481,2491,2500,2520,2521,2530,2531,2551,2562,2570,2571,2572,2573,2582,2590,2592,2600,2610,2611,2612,2613,2614,2625,2630,2631,2751,2760,2835,2880,2900,2910,2938,2950,2960,2971,2972,2973,2974,2975,2980,2982,3021,3061,3081,3095,3100,3110,3120,3150,3170,3200,3210,3221,3230,3240,3270,3360,3375,3380,3410,3611,3621,3770,3911,3922,3923,3924,3925,6012,6032,6033,6040,6042,6051,6105,6120,6151,6175,6176,6213,6222,6223,6251,6281,6285,6291,6292,6293,6294,6298,6300,6301,6320,6335,6351,6352,6381,6415,6420,6461,6480,6490,6500,6515,6530,6543,6560,6592,6697,6772,6785,6790,6791,6792,6793,6801,6835,6836,6837,6892,6893,6910,6920,6921,6960,6980,6990,7010,7020,7030,7061,7070,7072,7080,7102,7103,7110,7114,7120,7121,7130,7132,7140,7150,7151,7152,7161,7170,7180,7210,7301,7302,7330,7380,7450,7460,7471,7472,7473,7474,7480,7481,7482,7483,7524,7591,7612,7620,7625,7630,7631,7632,7644,7650,7660,7670,7671,7680,7700,7723,7761,7780,7785,7795,7796,7798,7834,7835,7861,7862,7863,7864,7866,7871,7875,7888,7889,7890,7892,7893,7900,7901,7904,7908,7910,7911,7912,7913,7914,7916,7917,7918,7920,7930,7931,7932,7937,7940,7947,7957,7960,7967,7970,7980,7992,9105,9510,9511,9520,9521,9530,9535,9536,9537,9538,9539,9540,9541,9545,9546,9611,9612,9621,9622,9623,9937,9938,9939,9940,9961,9971,9972,9973};
		//int[] code = {9611,9510};
		
    	for (int i=0; i<code.length; i++) {
    		
    		StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B490007/qualExamSchd/getQualExamSchdList"); /*URL*/
    		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=AWU1f088%2FVV23%2BsyldSRkB9R7W0aPPjvOzYchic9fzkzFibEutLntKVSRUhBgFCW3OL9gJNsbSefZBNmhutviQ%3D%3D"); /*Service Key*/
    		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
    		urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
    		urlBuilder.append("&" + URLEncoder.encode("dataFormat","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답 데이터 표준 형식 - xml / json (대소문자 구분 없음)*/
    		urlBuilder.append("&" + URLEncoder.encode("implYy","UTF-8") + "=" + URLEncoder.encode("2023", "UTF-8")); /*시행년도*/
    		//urlBuilder.append("&" + URLEncoder.encode("qualgbCd","UTF-8") + "=" + URLEncoder.encode("S", "UTF-8")); /*자격구분코드 - T : 국가기술자격 - C : 과정평가형자격 - W : 일학습병행자격 - S : 국가전문자격*/
    		urlBuilder.append("&" + URLEncoder.encode("qualgbCd","UTF-8") + "=" + URLEncoder.encode("T", "UTF-8")); /*자격구분코드 - T : 국가기술자격 - C : 과정평가형자격 - W : 일학습병행자격 - S : 국가전문자격*/
    		urlBuilder.append("&" + URLEncoder.encode("jmCd","UTF-8") + "=" + URLEncoder.encode(code[i]+"", "UTF-8")); /*종목코드 값 (예) 7910 : 한식조리 기능사(검정형)*/
    		URL url = new URL(urlBuilder.toString());
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod("GET");
    		conn.setRequestProperty("Content-type", "application/json");
    		//System.out.println("Response code: " + conn.getResponseCode());
    		BufferedReader rd;
    		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
    			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    		} else {
    			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    		}
    		StringBuilder sb = new StringBuilder();
    		String line;
    		while ((line = rd.readLine()) != null) {
    			sb.append(line);
    		}
    		rd.close();
    		conn.disconnect();
    		//System.out.println(sb.toString());
    		System.out.println();
    		
    		try {
    			
    			String str = sb.toString();
    			
    			JSONParser parser = new JSONParser();
    			
    			JSONObject jsonResponse = (JSONObject) parser.parse(str);
    			JSONObject body = (JSONObject)jsonResponse.get("body");
    			
    			ArrayList<String> temp = new ArrayList<String>();
    			if (body.get("items") instanceof JSONObject) {
    				System.out.print(code[i]+",");
    				
    				JSONObject item = (JSONObject)body.get("items");
    				System.out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n"
//    						,code[i]
							,item.get("implYy")
							,item.get("implSeq")
							,item.get("qualgbCd")
							,item.get("qualgbNm")
							,item.get("description")
							,item.get("docRegStartDt")
							,item.get("docRegEndDt")
							,item.get("docExamStartDt")
							,item.get("docExamEndDt")
							,item.get("docPassDt")
							,item.get("pracRegStartDt")
							,item.get("pracRegEndDt")
							,item.get("pracExamStartDt")
							,item.get("pracExamEndDt")
							,item.get("pracPassDt")
							,item.get("numOfRows")
							,item.get("pageNo")
							, item.get("totalCount"));
    				
    			} else {
    				
    				JSONArray arr = (JSONArray)body.get("items");
    				//System.out.print(code[i]+",");

    				int n=1;
    				for (Object obj : arr) {
    					JSONObject item = (JSONObject)obj;
    					System.out.printf("%d, %s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n"
    											,code[i]
    											,item.get("implYy")
    											,item.get("implSeq")
    											,item.get("qualgbCd")
    											,item.get("qualgbNm")
    											,item.get("description")
    											,item.get("docRegStartDt")
    											,item.get("docRegEndDt")
    											,item.get("docExamStartDt")
    											,item.get("docExamEndDt")
    											,item.get("docPassDt")
    											,item.get("pracRegStartDt")
    											,item.get("pracRegEndDt")
    											,item.get("pracExamStartDt")
    											,item.get("pracExamEndDt")
    											,item.get("pracPassDt")
    											,item.get("numOfRows")
    											,item.get("pageNo")
    											, item.get("totalCount"));
    					
    					n++;
    				}
    				
    			}
    			
    		} catch (Exception e) {
    			System.out.println("OpenAPI.main()");
    			e.printStackTrace();
    		}
    	}
		
	}
	
}
