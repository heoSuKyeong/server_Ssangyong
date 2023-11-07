import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class OpenAPI_code {

    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.q-net.or.kr/api/service/rest/InquiryTestInformationNTQSVC/getJMList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=AWU1f088%2FVV23%2BsyldSRkB9R7W0aPPjvOzYchic9fzkzFibEutLntKVSRUhBgFCW3OL9gJNsbSefZBNmhutviQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("jmcd","UTF-8") + "=" + URLEncoder.encode("1320", "UTF-8")); /*종목코드*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
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
        System.out.println(sb.toString());
        System.out.println(urlBuilder.toString());
    		/*
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
    		 */
		
	}
	
}
