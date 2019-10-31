<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>	
<script src="../js/imageboard.js" type="text/javascript"></script>    

<form name="imageboardWriteForm" id="imageboardWriteForm" method="post" enctype="multipart/form-data">
<table border="1" cellspacing="0" cellpadding="3">
<tr>
	<th width="80">상품코드</th>
	<td>
		<input type="text" name="imageId" id="imageId" placeholder="상품코드 입력">
		<div id="imageIdDiv"></div>
	</td>
</tr>
<tr>
	<th>상품명</th>
	<td>
		<input type="text" name="imageName" id="imageName" placeholder="상품명 입력">
		<div id="imageNameDiv"></div>
	</td>
</tr>
<tr>
	<th>단가</th>
	<td>
		<input type="text" name="imagePrice" id="imagePrice" placeholder="단가 입력">
		<div id="imagePriceDiv"></div>
	</td>
</tr>
<tr>
	<th>개수</th>
	<td>
		<input type="text" name="imageQty" id="imageQty" placeholder="개수 입력">
		<div id="imageQtyDiv"></div>	
	</td>
</tr>
<tr>
	<th>내용</th>
	<td>
		<textarea rows="15" cols="50" name="imageContent" id="imageContent" placeholder="내용 입력"></textarea>
		<div id="imageContentDiv"></div>
	</td>
</tr>
 <tr>
	<td colspan="2" align="center">
		<input type="file" name="img" id="img1" size="60">
		<div id="img1Div"></div>
	</td>
</tr>

<tr> 
	<td colspan="2" align="center">
		<input type="file" name="img" id="img2" size="60">
		<div id="img1Div"></div>
	</td>
</tr>

<tr>
	<td colspan="2" align="center">
		<input type="file" name="img[]" id="img3" size="60" multiple>
		<div id="img1Div"></div>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="button" value="이미지등록" id="imageboardWriteBtn">
		<input type="reset" value="다시작성">
	</td>
</tr>
</table>
</form>















