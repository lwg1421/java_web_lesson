<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.oraclejava.bean.Bmi" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
---------------------------------------<br/>
BMI의 프로퍼티값 얻기<br/>
---------------------------------------<br/>

	<%
	Bmi BMI = new Bmi();
	BMI.setHeight(171);
	BMI.setWeight(63.5);
	BMI.getBmi();
	//session.setAttribute를 무조건 작성해주어야 사용이 가능
	//위에서 만들어준 BMI 이라는 인스턴스를 BMI 라는이름으로 사용하겠다고 선언하는 것임
	session.setAttribute("bmi",BMI);
	%>
	<!-- 위에서BMI라고 이름을 지정해줬으니 사용하기 위해서는 TEST라고 불러와서 사용해야함 -->
	키:${bmi.height}cm<br/>
	몸무게:${bmi["weight"]}kg<br/>
	BMI결과:${sessionScope.bmi.bmi}입니다.<br/>

</body>
</html>