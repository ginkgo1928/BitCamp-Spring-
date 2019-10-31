<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<link rel="stylesheet" href="../css/board.css">

<input type="hidden" id="pg" value="${pg}">


<table id="boardListTable" border="1" frame="hsides" rules="rows" cellspacing="0" cellpadding="5">
	<tr>
		<th width="100">글번호</th>
		<th width="300" >제목</th>
		<th width="100">작성자</th>
		<th width="100">작성일</th>
		<th width="100">조회수</th>
	</tr>
</table>

<div id="boardPagingDiv" style="width:700px; text-align: center;"></div>
<br><br>

<form id="boardSearchForm">
<div style="text-align: center;">
<input type="hidden" name="pg" value="1">
<select name="searchOption" id="searchOption" style="width: 80px;">
	<option value="subject">제목
	<option value="id">아이디
</select>
<input type="text" name="keyword" value="${keyword}" placeholder="검색어 입력">
<input type="button" id="boardSearchBtn" value="검색">
</div>
</form>


<script type="text/javascript"  src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="../js/board.js" type="text/javascript"></script>
<script type="text/javascript">
//keyword 에서 값을 줘서 여기에서 이걸 안해도 된다.
/* 	window.onload=function(){
		if('${searchOption}'=='subject' || '${searchOption}'=='id')
			document.getElementById('searchOption').value = '${searchOption}';
			
}
 */
//내가 리스트에서 강압적으로 줌
function boardSearch(pg){
//get 방식인데 결과는 post 여서 안된다.
/* 	location.href="/springProject/board/boardSearch?pg="+pg
	+"&searchOption=${searchOption}"
	+"&keyword="+encodeURIComponent("${keyword}"); */
	
//들어온 pg값을 그대로 form에 대입	

$('input[name=pg]').val(pg);
//검색 버튼 제이쿼리를 발생하라 -->trigger 사용
//내가 이벤트를 요청할 때 쓴다.(아무거나 써도 됨)

$('#boardSearchBtn').trigger('click','trigger');
	
}
</script>



