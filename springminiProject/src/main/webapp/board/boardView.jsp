<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="../js/board.js"></script>
<!-- 글 수정에서 값을 불러오는 창이야 -->    
<style type="text/css">
#subject {font-size: 30pt; font-weight: bold; }
</style>
<form name="boardViewForm">
<input type="hidden" id="seq" name="seq" value="${seq}">
<input type="hidden" id="pg" name="pg" value="${pg}">
<%-- <input type="hidden" id="pseq" name="seq" value="${seq}"> --%>

<h1><span id="subjectSpan"></span></h1>

<table border="1"  width="700" frame="hsides" rules="rows">
<tr>
	<th width="70" align="center">글번호<span id="seqSpan"></span></th>
	<th width="70" align="center">작성자<span id="idSpan"></span></th>
	<th width="70" align="center">조회수<span id="hitSpan"></span></th>
</tr>

<tr>

	<td width="70" align="center"></td>
	<td width="70" align="center"></td>
	<td width="70" align="center"></td>

</tr>

<tr>
	<td colspan="3" valign="top" width="200">
	<pre style="white-space: pre-line; word-break: break-all;"><span id="contentSpan"></span></pre>
	</td>
</tr>

</table>
</form>
	<input type="button" value="목록" onclick="location.href='/springminiProject/board/boardList?pg=${pg}'">
	<input type="button" value="답글" onclick="mode(3)">
	<!--  pseq 원글번호, pg 페이지 -->
	
	<span id="boardViewDiv">

	<input type = "button" value ="수정" onclick="mode(1)">
	<input type = "button" value ="삭제" onclick="mode(2)">

	</span>

<script type="text/javascript">
function mode(num){
	if(num==1){//수정
		document.boardViewForm.method = 'post';
		document.boardViewForm.action = '/springminiProject/board/boardModifyForm';
		document.boardViewForm.submit();
	}else if(num==2){//삭제
		document.boardViewForm.method = 'post';
		document.boardViewForm.action = '/springminiProject/board/boardDelete';
		document.boardViewForm.submit();
	}else if(num==3){//답글
		document.boardViewForm.method = 'post';
		document.boardViewForm.action = '/springminiProject/board/boardReplyForm';
		document.boardViewForm.submit(); //submit이면 데이터 다 들고감 seq(나한테 원글번호)
	}
}
</script>


<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function () { //글 보기
	$.ajax({
		type: 'post',
		url: '/springminiProject/board/getBoardView',
		//나 아직 data에 pg 안줌
		data:'seq='+$('#seq').val()+'&pg='+$('#pg').val(),
		dataType: 'json',
		success: function (data) {
			//alert(JSON.stringify(data));
			$('#subjectSpan').text(data.boardDTO.subject);
			$('#seqSpan').text(data.boardDTO.seq);
			$('#idSpan').text(data.boardDTO.id);
			$('#hitSpan').text(data.boardDTO.hit);
			$('#contentSpan').text(data.boardDTO.content);
			//강제로 대입해
			
			if(data.memId==data.boardDTO.id)
				$('#boardViewDiv').show();
			else 
				$('#boardViewDiv').hide();
		}
	
		,err: function (err) {
			console.log(err);
		}
	});//aj
});
</script>


