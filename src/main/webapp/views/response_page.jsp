<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 이구문을 스크립틀릿(scriptlet)이라고 한다
	// 자바코드를 쓸 수 있다

	// 현재 JSP상에서 출력해줄 값 => request의 Attribute로 set해서 전달받아야 한다
	
	// request.getAttribute("name값") : Object
	
	String name = (String)request.getAttribute("name");
	String gender = (String)request.getAttribute("gender");
	int age = (int)request.getAttribute("age");
	double height = (double)request.getAttribute("height");
	String[]foods = (String[])request.getAttribute("foods");
	String city = (String)request.getAttribute("city");

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답페이지</title>
</head>
<body>

<!-- HTML에 자바코드넣기 -->
<!-- JSP는 서블릿으로 변환이 된다 즉, 자바 클래스이다 -->

	<h1>위임(배정)받는지 확인</h1>
	
	<h3><%= name %>님의 정보~</h3>
	
	나이 : <%= age %> <br>
	키 : <%= height %> <br>
	지역 : <%= city %> <br>
	
	성별 : 
	<% if("선택 안 함".equals(gender)){ %>
	선택 안했음 <br>
	<% }else if("남자".equals(gender)) { %>
		남자~~ <br>
	<% } else {%>
		여자~~ <br>
	<% } %>
	
	좋아하는 음식~
	<% if(foods == null) { %>
		안골랐음~
	<%}else { %>
	<ul>
		<%for(int i=0; i<foods.length; i++){ %>
			<li><%= foods[i] %> </li>
			<% } %>
	</ul>
	 입니다.
	<%} %>
	
	
	<hr>
	${ msg }
	
	
	
	
	

</body>
</html>