<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="page navbar-fixed devise registrations new" data-name="registrations-new">
		<div class="page-content">
			<div class="content-wrapper">
				<div class="block inset social-block">
					<a class="button button-big button-fill bg-facebook-color" type="external" href="/users/auth/facebook"> 페이스북 아이디로 가입 </a> 
					<a class="button button-big button-fill bg-naver-color" type="external" href="/users/auth/naver"> 네이버 아이디로 가입 </a> 
					<a	class="button button-big button-fill color-red col" type="external" href="/users/auth/google_oauth2"> 구글 아이디로 가입 </a>
					<a class="button button-big button-fill bg-kakao-color col" type="external" href="/users/auth/kakao"> 카카오 아이디로 가입 </a>
				</div>

				<div class="block inset login-block">
					<form class="simple_form new_user" id="new_user" novalidate="novalidate" action="/users" accept-charset="UTF-8"
						method="post">
						<input name="utf8" type="hidden" value="&#x2713;" />
						<input type="hidden" name="authenticity_token"
							value="q9gQa6FJAGeyUXa/2Rqo3qFfAoRnIW7mWbC2OW7Ng3dkG0S1/JtHsoBa/zD5xY2Pud8JaBJCD5iKZsrsODST3A==" />
						<div class="signup-or-separator">
							<span class="signup-or-separator--text">또는</span>
							<hr>
						</div>

						<div class="list form-list no-hairlines no-margin-top">
							<ul>
								<li class="item-content item-input">
									<div class="item-inner">
										<div class="item-input-wrap">
											<input autocorrect="off" autocomplete="name" class="string required" 
											placeholder="이름" type="text"
												name="user[name]" id="user_name" />
										</div>
									</div>
								</li>

								<li class="item-content item-input">
									<div class="item-inner">
										<div class="item-input-wrap">
											<input autocapitalize="off" autocorrect="off" autocomplete="email" required="required"
												class="string email required" aria-required="true"
												placeholder="이메일 주소" type="email" value=""
												name="user[email]" id="user_email" />
										</div>
									</div>
								</li>

								<li class="item-content item-input">
									<div class="item-inner">
										<div class="item-input-wrap">
											<input required="required" autocapitalize="off" autocomplete="new-password" class="password required"
												aria-required="true" placeholder="비밀번호" type="password"
												name="user[password]" id="user_password" />
										</div>
									</div>
								</li>
							</ul>
						</div>

						<div class="block-footer term-footer">
							<input name="user[terms_of_service]" type="hidden" value="0" />
							<input type="checkbox" value="1" checked="checked"
								name="user[terms_of_service]" id="user_terms_of_service" />
							회원가입을 하면 MENTORMAN의 <a href="/terms/term" type="external"
								target="_blank">이용약관</a> 및 <a href="/terms/privacy"
								type="external" target="_blank">개인정보처리방침</a>에 동의하는 것으로 간주합니다.
							<div></div>
						</div>

						<input type="submit" name="commit" value="회원가입"
							class="btn button button-big button-fill submit-button"
							data-disable-with="요청중..." />
					</form>
				</div>


				<div class="block inset text-align-center">
					<a class="color-gray" type="external" href="/users/sign_in"> 이미 계정이 있나요? 로그인 </a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>