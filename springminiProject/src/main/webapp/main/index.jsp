<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/member.js"></script>



<table border="1" width="100%" cellspacing="0" cellpadding="5">
	<tr>
		<td colspan="2" align="center">
		<jsp:include page="../template/top.jsp" /></td>
	</tr>
	<tr>
		<td valign="top" width="250" height="400">
		<jsp:include page="../template/left.jsp" /></td>
		<td><jsp:include page="${display}" /></td>
	</tr>
	
	<tr>
		<td colspan="2">
		<jsp:include page="../template/bottom.jsp" /></td>
	</tr>
</table>

