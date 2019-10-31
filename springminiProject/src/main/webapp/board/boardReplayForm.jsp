<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>    
<script src="../js/board.js" type="text/javascript"></script>
<h1>답글</h1>

<form id="boardReplayform" method="post" action="">
<input type="hidden" name="pseq" value="${pseq}">
<input type="hidden" name="pg" value="${pg}">

<table border="1" align="left"  width="700">
<tr>
	<th width="80"> 제목</th>
	<td><input type="text" name="subject" id="subject" placeholder="제목 입력"  style="width: 500px;">
		<div id="subjectDiv"></div>
	</td>
</tr>

<tr>
	<th width="80">내용</th>
	<td><textarea name ="content" placeholder ="내용을 입력" id="content" style="width: 500px; height: 500px;">
	</textarea>
		<div id="contentDiv"></div>
</tr>
	
<tr>
		<td colspan="2" align="center">
		<input type="button" value="답글쓰기" id="bodarReplyBtn"> 
		<input type="reset" value="다시작성">
	</td>
</tr>
</table>
</form>
