<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- <span>
	<img src="../img/zoom.png" onclick="bigImage()" style="cursor: pointer;">
</span> -->

<table border="1" cellspacing="0" cellpadding="3">
<tr align="center">
	<td rowspan="4">
	<img id="image1" width="200" height="200"></td>
	<th width="100">상품명</th>
	<td width="150"><span id="imageNameSpan"></span></td>
</tr>

<tr align="center">
	<th>단가</th>
	<td>
		<span id="imagePriceSpan"></span>원
	</td>
</tr>

<tr align="center">
	<th>개수</th>
	<td><span id="imageQtySpan"></span>개</td>
</tr>

<tr align="center">
	<th>합계</th>
	<td><span id="imageTotalSpan"></span>원
	</td>
</tr>

<tr>
	<td colspan="3" height="200" valign="top">
		<pre><span id="imageContentSpan"></span></pre>
	</td>
</tr>
</table>

<input type="button" value="목록" onclick="location.href='/springminiProject/imageboard/imageboardList?pg=${pg}'">

<script>
function bigImage(){
	var newWindow = window.open("","","width=500 height=500");
	
	var img = newWindow.document.createElement("img");
	img.setAttribute("src", "http://localhost:8090//springminiProject/storage/${imageboardDTO.image1}");
	img.setAttribute("width", "500");
	img.setAttribute("height", "500");
	newWindow.document.body.appendChild(img);
}
</script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	$.ajax({
		type:'post',
		url:'/springminiProject/imageboard/getImageboardView',
		data:'seq=${seq}',
		dataType:'json',
		success:function(data){
		//alert(JSON.stringify(data));
			var total = data.imageboardDTO.imagePrice*data.imageboardDTO.imageQty;
			$('#image1').attr('src', '../storage/'+data.imageboardDTO.image1);
			$('#imageNameSpan').text(data.imageboardDTO.imageName);
			$('#imagePriceSpan').text(data.imageboardDTO.imagePrice);
			$('#imageQtySpan').text(data.imageboardDTO.imageQty);
			$('#imageTotalSpan').text(total.toLocaleString());
			$('#imageContentSpan').text(data.imageboardDTO.imageContent);
		},error:function(err){
			consol.log(err);
		}
	});
});
</script>
















