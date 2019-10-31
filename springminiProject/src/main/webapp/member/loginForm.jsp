<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
#loginForm {font-size: 9pt;}
</style>


<form id="loginForm" method="post" action="/member/login">
<table border="1" cellspacing="0" cellpadding="5">

<tr>

	<th width="80">아이디</th>
	<td><input type="text" id="loginId" placeholder="아이디 입력" >
		<div id="logidDiv"></div>
	</td>
</tr>
<tr>
	<th width="80">비밀번호</th>
	<td><input type="password" id="loginPwd" placeholder="비밀번호 입력">
		<div id="logpwdDiv"></div>
	</td>
</tr>

<tr>
	<td colspan="2" align="center">
		<input type="button" value="로그인" id="LoginBtn">
		<input type="button" value="회원가입" onclick="location.href='/springminiProject/member/writeForm'">
	</td>
</tr>
</table>
</form>

