<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 내가 작성한 글을 다시 수정 하는 창이야 -->    
<script src="../js/board.js"></script>

<input type="hidden" id="seq" value="${seq}">
<input type="hidden" id="pg" value="${pg}">
    
<form name="boardModifyForm" method="post" action="/springminiProject/board/boardModify.do">


<h1>글 수정</h1>
<table border="1" align="left"  width="700" >	
<tr>
	<th width="80"> 제목</th>
	<td><input type="text" name="subject" id="subject" style="width: 500px;"></td>
</tr>

<tr>
	<th width="80">내용</th>
	<td><textarea name ="content" id="content" style="width:500px; height:500px;"></textarea>
</tr>
	
<tr>
		<td colspan="2" align="center">	
		
		<input type="button" value="수정" onclick="checkBoard()" > 
		<input type="reset" value="다시작성">
	</td>
</tr>
</table>
</form>

<script src="../js/boardjs.js" type="text/javascript"></script>
