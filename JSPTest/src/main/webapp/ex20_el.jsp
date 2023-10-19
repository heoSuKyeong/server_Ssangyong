<%@page import="com.test.jsp.Score"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex20_el</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body>

	<h1>EL</h1>

	<%
		int a = 10;
		pageContext.setAttribute("b", 20);
		request.setAttribute("c", 30);
	%>
	
	<h2>표현식</h2>
	<div>a: <%= a %></div>
	<div>b: <%= pageContext.getAttribute("b") %></div>
	<div>c: <%= request.getAttribute("c") %></div>
	
	<h2>EL</h2>
	<div>a: ${a}</div> 
	<%-- <div>b: ${pageContext.getAttribute("b")}</div>
	<div>c: ${request.getAttribute("c")}</div> --%>
	
	<div>b: ${b}</div>
	<div>c: ${c}</div>

	<hr>
	
	<h3>EL 연산 기능</h3>
	<div>b+10 = <%=(int)pageContext.getAttribute("b")+ 10 %></div>
	<div>b+10 = ${b+10}</div>
	
	<div>b+10 = ${b+10}</div>
	<div>b-10 = ${b-10}</div>
	<div>b*10 = ${b*10}</div>
	<div>b/10 = ${b/10}</div>
	<div>b%10 = ${b%10}</div>
	<div>b mod 10 = ${b mod 10}</div>
	
	<hr>
	
	<h3>비교 연산</h3>	
	<div>b &gt; 10 = ${b > 10}</div>
	<div>b &lt; 10 = ${b < 10}</div>
	
	<div>b &gt; 10 = ${b gt 10}</div>
	<div>b &lt; 10 = ${b lt 10}</div>

	<div>b &gt;= 10 = ${b >= 10}</div>
	<div>b &gt;= 10 = ${b ge 10}</div>
	
	<div>b == 20 = ${b == 20}</div>
	<div>b != 20 = ${b != 20}</div>
	
	<div>b == 20 = ${b eq 20}</div>
	<div>b != 20 = ${b ne 20}</div>
	
	<hr>
	
	<h3>논리 연산</h3>
	<div>${false && true}</div>
	<div>${true || false}</div>
	<div>${!true}</div>
	
	
	<hr>
	
	<div>${b > 0 ? "양수" : "음수" }</div>
	
	<div>${"문자열".equals("문자열") }</div>
	<!-- //메소드 없이 연산자로 비교 가능 -->
	<div>${"문자열" == "문자열" }</div>		
	<!-- 문자열 쌍따옴표, 꽃따옴표 둘 다 지원 -->
	<div>${"문자열" == '문자열' }</div>		
	
	<hr>
	<%
	
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("kor", 100);
		map.put("eng", 90);
		map.put("math", 80);
		
		pageContext.setAttribute("map", map);
		
	%>
	<!-- 단일값과 다르게 복합데이터는  -->
	<h3>객체 출력(HashMap)</h3>
	<div>국어: <%=map.get("kor") %></div>
	<div>영어: <%=map.get("eng") %></div>
	<div>수학: <%=map.get("math") %></div>
	
	<div>국어: ${map.get("kor") }</div>
	<div>국어: ${map["kor"] }</div>
	<div>국어: ${map.kor}</div>
	<div>영어: ${map.eng}</div>
	<div>수학: ${map.math}</div>
	
	
	<%
	
		Score score = new Score();
	
		score.setKor(99);
		score.setEng(88);
		score.setMath(77);
		
		pageContext.setAttribute("score", score);
	%>
	
	<h3>객체 출력(일반 객체)</h3>
	<div>국어: <%=score.getKor() %></div>
	<div>국어: ${score.getKor()}</div>
	<div>국어: ${score.kor}</div>
	<div>국어: ${score["kor"]}</div>
	
	<!-- .kor , ["kor"] 은 getter 메소드이다.
	EL 내에서 kor에 get를 붙여서 getKor()로 만들어준다. -->

	<div>영어:${score.eng }</div>
	<div>수학:${score.math }</div>

	<div>총점: ${score.kor + score.eng + score.math}</div>
	
	
	<%
		//충돌이 나면
		//부모와 자식이 충돌 > 자식
		//넓은 범위와 구체적 충돌 > 구제적
		
		//EL은 생명주기 순으로 탐색한다.
		//pageContext > request > session > application
		pageContext.setAttribute("color", "tomato");			//민증 홍길동
		request.setAttribute("color", "orange");				//서울
		session.setAttribute("color", "gold");					//대한민국
		application.setAttribute("color", "cornflowerblue");	//지구
	
	
	%>
	
	<div style="background-color:${color};">${color}</div>	<!-- //pageContext -->
	<div style="background-color:${requestScope.color};">${requestScope.color}</div>
	
	
	
	

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="http://pinnpublic.dothome.co.kr/cdn/example-min.js"></script>
	
	<script>
	
	</script>
</body>
</html>