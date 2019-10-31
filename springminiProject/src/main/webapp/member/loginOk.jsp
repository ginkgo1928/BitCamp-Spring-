<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

${sessionScope.memName}님 로그인
<input type="button" value="로그아웃"  onclick="location.href='/springminiProject/member/logout'">
<input type="button" value="회원정보수정" onclick="location.href='/member/modifyForm?id=${sessionScope.memId}'">
