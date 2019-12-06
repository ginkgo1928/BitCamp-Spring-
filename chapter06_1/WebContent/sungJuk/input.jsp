<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/chapter06_1/sungJuk/result.do">
<table border="1">
<tr>
	<th>이름</th>
	<td><input type="text" name="name"></td>
</tr>

<tr>
	<th>국어</th>
	<td><input type="text" name="kor"></td>
</tr>

<tr>
	<th>수학</th>
	<td><input type="text" name="math"></td>
</tr>

<tr>
	<th>영어</th>
	<td><input type="text" name="eng"></td>
</tr>
	
<tr>
	<td colspan="2" align="center">
	<input type="submit" value="계산"></td>
</tr>

</table>
</form>
</body>
</html>