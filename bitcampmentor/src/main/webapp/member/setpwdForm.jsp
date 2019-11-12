<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet"  href="../css/mypg.css" type="text/css" />    
<div class="page navbar-fixed devise passwords new" data-page="passwords-new">    
<div class="page-content">
	<div class="content-wrapper">
		<div class="block inset hero-title">
			<h1 class="title">비밀번호 재설정</h1>

			<div class="description">계정으로 사용하는 이메일 주소를 입력하시면, 비밀번호 재설정 링크를 전송해 드립니다.</div>
		</div>

		<div class="block inset">
			<form class="simple_form new_user" id="new_user" name="setrepwdform"novalidate="novalidate" action="" method="post">
				<div class="list form-list no-hairlines no-margin-top">
					<ul>
						<li class="item-content item-input">
							<div class="item-inner">
								<div class="item-input-wrap">
									<input autocapitalize="off"  class="string email optional" placeholder="이름" type="email" value="" name="member_name" id="member_name" />
								</div>
							</div>
						</li>
						<div class="name-setpwd-Div"></div>
						<li class="item-content item-input">
							<div class="item-inner">
								<div class="item-input-wrap">
									<input autocapitalize="off"  class="string email optional" placeholder="이메일 주소" type="email" value="" name="member_email" id="member_email" />
								</div>
							</div>
						</li>	
					<div class="email-setpwd-Div"></div>
					<li class="item-content item-input" id="setpwdEmailOn">
							<div class="item-inner">
								<div class="item-input-wrap">
									<input autocapitalize="off"  class="string email optional" placeholder="인증번호 입력" type="text" value="" name="" id="" />
								</div>
							</div>
						</li>
					<input type="button" name="setpwdOnBtn" id="setpwdOnBtn" value="확인"  class="btn button button-big button-fill submit-button">
					</ul>
					<div class="setPwd-Div"></div>
				</div>
				<input type="button" name="setpwdBtn" id="setpwdBtn" value="비밀번호 재설정 메일 발송" class="btn button button-big button-fill submit-button" data-disable-with="요청중..." />
			</form>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	setPwdEmail=false;
	$("#setpwdEmailOn").hide();
	$("#setpwdOnBtn").hide();
	$("#setpwdBtn").click(function(){
		var jCont="";
		if($("#member_name").val()==''){
			jCont = '<div class="msg_error">이름을 입력해주세요.</div>';
			$('.name-setpwd-Div').css('color', 'red').html(jCont);
			$('#member_name').focus();
			$('#member_name').addClass("error");
			setPwdEmail=false;
		}else if($("#member_email").val()==''){
			jCont = '<div class="msg_error">이메일을 입력해주세요.</div>';
			$('.email-setpwd-Div').css('color', 'red').html(jCont);
			$('#member_email').focus();
			$('#member_email').addClass("error");
			setPwdEmail=false;
		}else if($('#member_name').val()!='' || $("#member_email").val()!='') {
			$.ajax({
				type:'post',
				url:'/bitcampmentor/member/setmemberpwd',
				data:'member_name='+$('#member_name').val()+'&member_email='+$('#member_email').val(),
				dataType:'text',
				success:function(data){
					 if(data=="get_member"){
						jCont='<div class="msg_emailok">비밀번호 재설정 인증번호를 발송했습니다.</div>';
						$('.setPwd-Div').css('color', 'blue').html(jCont);
						$("#setpwdEmailOn").show();
						$("#setpwdOnBtn").show();
						
					}else if(data=="not_member"){
						jCont='<div class="msg_emailfail">찾을수가 없습니다. 다시확인해주세요.</div>';
						$('.setPwd-Div').css('color', 'red').html(jCont);
						setPwdEmail=false;
					}
					},error:function(e){
						conlose.log(e);
					}
			});//aj
			$('#member_name').removeClass("error");
			$('#member_email').removeClass("error");
		}
	});
});
</script>
