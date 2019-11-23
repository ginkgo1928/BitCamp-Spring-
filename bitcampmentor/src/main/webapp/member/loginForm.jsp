<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="../js/memberjoin.js"></script>
<div class="page navbar-fixed devise sessions new" data-name="sessions-new">
	<div class="page-content" style="margin-bottom: 100px;">
		<div class="content-wrapper">
			<h1 align="center">로그인</h1>
			<div class="block inset social-block">
				<a class="button button-big button-fill bg-naver-color" type="external" href=""> 네이버 아이디로 로그인</a> 
				<a class="button button-big button-fill bg-kakao-color col" type="external" href=""> 카카오 아이디로 로그인</a>
			</div>

			<div class="block inset login-block">
				<form class="simple_form new_user" name="loginForm" id="new_user" novalidate="novalidate" action="<c:url value='/member/login'/>" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<div class="signup-or-separator">
						<span class="signup-or-separator--text">또는</span>
						<hr>
					</div>

					<div class="list form-list no-hairlines no-margin-top">
						<ul>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input autocapitalize="off" autocomplete="email"  class="string email required"  
											placeholder="이메일 주소" type="email" value="" name="member_email"id="member_email" />
											<div class="login-member-email-Div"></div>
									</div>
								</div>
							</li>

							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input required="required" class="password required" placeholder="비밀번호" type="password" name="member_pwd" id="member_pwd" />
										<div class="login-member-pwd-Div"></div>
									</div>
								</div>
							</li>
						</ul>
					</div>
			
					<div class="login-Div">
					</div>
				
					
					<div class="block-footer forgot-password text-align-right">
						<input type="checkbox" id="cheboxid" name="cheboxid" value="" data-check="check">이메일 저장 &nbsp;&nbsp;&nbsp;&nbsp;
						<a class="color-gray" type="external" href="../member/setpwdForm">비밀번호를 잊으셨나요? </a>
					</div>

					<input type="button" id="loginBtn" value="로그인" class="btn button button-big button-fill submit-button" data-disable-with="요청중..." />
				</form>
			</div>


			<div class="block inset text-align-center">
				<a class="color-gray" type="external" href="../member/writeForm"> 계정이 없으세요? 회원가입 </a>
			</div>
		</div>
	</div>
</div>
<script type='text/javascript'>
let status = '${status}';
isgetLogin(status);
</script>
