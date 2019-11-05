<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script src="https://d2ljmlcsal6xzo.cloudfront.net/packs/vendor-d00d42ee38137ae39144.js"></script>
<script src="https://d2ljmlcsal6xzo.cloudfront.net/packs/application-978dcbe0c8e9f17a9e95.js"></script>
<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/member.js"></script>

<link rel="shortcut icon" href="../image/mentorlogo.ico" type="image/x-icon"/>
<link rel="stylesheet"  href="../css/ly.css" type="text/css" />

<body class="color-theme-pink">
		<div id="app" class="framework7-root">

			<div class="head">
				<jsp:include page="../template/head.jsp" />
			</div>
			
			<!-- display -->
			<div class="container" id="container">
				<jsp:include page="${display}" />
			</div>


			<!-- footer  -->
			<div id="foot" id="foot">
				<jsp:include page="../template/footer.jsp" />
			</div>
		</div>
</body>
