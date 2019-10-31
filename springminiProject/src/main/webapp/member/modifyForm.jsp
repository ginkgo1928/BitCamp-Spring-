<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>회원정보</h1>
<form name="modifyform" method="post" action="/springminiProject/member/modify.do"> 
<table border="1"  cellspacing="0" cellpadding="5" width="600">
	<tr>
	<th width="100">이름</th>
	<td><input type="text" name="name" style="width: 150px;" value="${requestScope.memName}"></td>
	</tr> 
	
	<tr>
	<th width="100">아이디</th>
		<td>
		<input type="text" name="id" value="${requestScope.memId}" readonly style="width: 150px;" readonly>
		</td>
	</tr>
	
	<tr>
	<th width="100">패스워드</th>
	<td><input type="password" name="pwd" style="width: 150px;"></td>
	</tr>
	
	<tr>
	<th width="110">비밀번호 확인</th>
	<td><input type="password" name="repwd" style="width: 150px;" ></td> 
	</tr>
	
	<tr>
	<th width="100">성별</th>
		<td>
		<input type="radio"  name="gender" value="0">남
		<input type="radio"  name="gender" value="1">여
		</td> 
	</tr>
	
	<tr>
		<th>이메일</th>
		<td>
			<input type="text" name="email1" value="${requestScope.memEmail1}">@
			<input list="mailaddr" name="email2" id="email2" placeholder="직접입력">
			<datalist id="mailaddr">
			<option value="gmail.com"></option>
			<option value="naver.com"></option>
			<option value="daum.net"></option>
			</datalist>
		</td>	
	</tr>
	
	<tr>
	<th width="80">핸드폰</th>
	<td>
		<select name="tel1" id="tel1" style="width: 50px;">
			<option value="010">010</option>
			<option value="011">011</option>
			<option value="012">019</option>
		</select>
		-
		<input type="text" name="tel2" style="width: 50px;" value="${requestScope.memTel2}">
		-
		<input type="text" name="tel3" style="width: 50px;" value="${requestScope.memTel3}">
	</td>
</tr>
	
	<tr>
	<th>주소</th>
	<td>
	   <input type = "text" id="daum_zipcode" name="zipcode" size = 5  value="${requestScope.memZipcode}" readonly> 
	   <input type = "button" value = "우편번호검색" onclick = "checkPost()"> <br>
	   <input type = "text" id = "daum_addr1" name = "addr1" size = 40   value="${requestScope.memAddr1}"readonly><br>
	   <input type = "text" id = "daum_addr2" name = "addr2" size = 40 value="${requestScope.memAddr2}">
	</td>
	</tr>
	
	<tr>
		<td colspan = 2 align = "center"> 
		<input type = "button" value = "회원수정" onclick="checkModeify()">
		<input type = "reset" value = "다시작성">     
	 	</td>   
	</tr>  
	  	
</table>
</form>

<script type="text/javascript" src="../js/member.js"></script>
<script type="text/javascript">
window.onload=function(){
	document.modifyform.gender['${requestScope.memGender}'].checked = true;
	document.getElementById("email2").value ='${requestScope.memEmail2}';
	document.getElementById("tel1").value='${requestScope.memTel1}';
}
</script>

