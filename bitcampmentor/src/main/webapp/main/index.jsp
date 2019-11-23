<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="../js/jquery-3.4.1.min.js"></script>
<script src="https://d2ljmlcsal6xzo.cloudfront.net/packs/js/application-a92a237ea64ef641a7be.js"></script>
<script src="https://d2ljmlcsal6xzo.cloudfront.net/assets/application-8fe7c196d09d6d277687b19bbdb09b271e65976d2cee1f68c35e3f8a0ee97085.js"></script>

<link rel="shortcut icon" href="../image/mentorlogo.ico" type="image/x-icon"/>
<link rel="stylesheet"  href="../css/ly.css" type="text/css" />

<body class="color-theme-pink">
   <div id="app" class="framework7-root">
      <div class="view view-main">
         <!-- head -->
         <div class="head">
            <jsp:include page="../template/head.jsp" />
         </div>
         
         <!-- display -->
         <div class="container" id="container">
            <jsp:include page="${display}" />
         </div>
         
        </div>
   </div>
   <!-- footer  -->
   <div id="foot" id="foot">
      <jsp:include page="../template/footer.jsp" />
   </div>
</body>
</html>