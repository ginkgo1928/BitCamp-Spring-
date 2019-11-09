/* 
 * 날짜 : 2019.11.01 
 * 작성자 :ginkgo1928
 * 설명 : 회원가입 JavaScript 유효성 검사
 */
//Email 유효성 검사
function emailCheck(strVal, text) {
	var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
	if(strVal.length == 0)	{
		return true;
	}
	if (!strVal.match(regExp))	{
		return true;
	}
	return false;
}
//특수문자 검사
function passwordCheck(str) {
	// var reg =
	// /([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/;
	var reg = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,15}$/;
	return reg.test(str);
}

// Name 유효성 검사
function Ifn_NameCheck() {
	var name = $('#member_name').val();
	var reg = /[ 0-9\{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@#$%&\'\"\\\(\=]/gi;
	var jCont = "";
	if (name.length==0) {
		jCont = '<div class="msg_error01">이름을 입력해주세요.</div>';
		$('.item-input-info-Name').css('color', 'red').html(jCont);
		$('#member_name').focus();
		$('#member_name').addClass("error");
		return false;
	}else if(reg.test(name)){
		jCont = '<div class="msg_error02">한글,영문만 입력 가능합니다.</div>';
		$('.item-input-info-Name').css('color', 'red').html(jCont);
		$('#member_name').focus();
		$('#member_name').addClass("error");
		return false;
	}else{
		jCont = "";
		$('#member_name').removeClass("error");
		$('.item-input-info-Name').html(jCont);
		return true;
	}
	
}

// NickName유효성 검사
$(document).ready(function(){
	$('#member_nickname').focusout(function(){
	var nickName=$("#member_nickname").val();
	var jCont="";
	if(nickName.length==0){
		jCont = '<div class="msg_error">닉네임을 입력해주세요.</div>';
		$('.item-input-info-NickName').css('color', 'red').html(jCont);
		$('#member_nickname').focus();
		$('#member_nickname').addClass("error");
		return false;
	}else if(nickName.length< 3 || nickName.length>22){
		jCont = '<div class="msg_error">닉네임 3자~22자. 이하입니다.</div>';
		$('.item-input-info-NickName').css('color', 'red').html(jCont);
		$('#member_nickname').focus();
		$('#member_nickname').addClass("error");
		return false;
	}else{
		$.ajax({
			type:'post',
			url:'/bitcampmentor/member/writeNicknamecheck',
			data:{'member_nickname':nickName},
			dataType:'text',
			success:function(data){
				if(data=="exist"){
				jCont='<div class="msg_nickok">사용 가능한 닉네임 입니다.</div>';
				$('.item-input-info-NickName').css('color', 'blue').html(jCont);
				}else if(data=="not_exist"){
				jCont='<div class="msg_nickerror">입력하신 닉네임은 사용중인 닉네임 입니다.</div>';
				$('.item-input-info-NickName').css('color', 'red').html(jCont);
				}
			},error:function(e){
				conlose.log(e);
			}
		});
	}	
		return true;
	});
});

// Email 유효성 검사
$(document).ready(function(){
	$('#member_email').focusout(function(){
	var Email=$("#member_email").val();
	var jCont="";
	if(Email.length==0){
		jCont = '<div class="msg_error">이메일을 입력해주세요.</div>';
		$('.item-input-info-Email').css('color', 'red').html(jCont);
		$('#member_email').focus();
		$('#member_email').addClass("error");
		return false;	
	}else if(emailCheck(Email)){
		jCont = '<div class="msg_error">올바르지 않은 이메일 형식입니다.</div>';
		$('.item-input-info-Email').css('color', 'red').html(jCont);
		$('#member_email').focus();
		$('#member_email').addClass("error");
		return false;
	}else{
		$.ajax({
			type:'post',
			url:'/bitcampmentor/member/writeEmailCheck',
			data:{'member_email':Email},
			dataType:'text',
			success:function(data){
			if(data=="email_ok"){
				jCont='<div class="msg_emailok">사용 가능한 이메일 입니다.</div>';
				$('.item-input-info-Email').css('color', 'blue').html(jCont);
			}else if(data=="email_fail"){
				jCont='<div class="msg_emailerror">입력하신 이메일은 사용중인 이메일 입니다.</div>';
				$('.item-input-info-Email').css('color', 'red').html(jCont);
			}
			},error:function(e){
				conlose.log(e);
			}
		});
	
		return true;
		}
	});
});

//Pwd유효성 검사
function Ifn_PwdCheck(){
	var Pwd=$('#member_pwd').val();
	var jCont="";
	if(Pwd.length==0){
		jCont='<div class="msg_pwderror">비밀번호를 입력해주세요.</div>';
		$('.item-input-info-Pwd').css('color','red').html(jCont);
		$('#member_pwd').focus();
		$('#member_pwd').addClass("error");
		return false;
	}else if(Pwd.length< 8 ||Pwd.length >15){
		jCont='<div class="msg_pwderror">비밀번호는 8자~15자리 이하입니다.</div>';
		$('.item-input-info-Pwd').css('color','red').html(jCont);
		$('#member_pwd').focus();
		$('#member_pwd').addClass("error");
		return false;
	}else {
		jCont='<div class="msg_pwdok">사용가능 합니다.</div>';
		$('.item-input-info-Pwd').css('color','blue').html(jCont);
		$('#member_pwd').removeClass("error");
		return true;
	}
}
//Repwd유효성 검사
function Ifn_RepwdCheck(){
	if($('#member_pwd').val()!=$('#member_repwd').val()){
		jCont='<div class="msg_repwderror">비밀번호가 일치하지 않습니다.</div>';
		$('.item-input-info-Repwd').css('color','red').html(jCont);
		$('#member_repwd').focus();
		$('#member_repwd').addClass("error");
		return false;
	}else if($('#member_pwd').val()==$('#member_repwd').val()){
		jCont='<div class="msg_repwdok">일치합니다.</div>';
		$('.item-input-info-Repwd').css('color','blue').html(jCont);
		$('#member_repwd').removeClass("error");
		return true;
	}
}


function previewFile(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		var member_profile_img = input.id + "_img";
		reader.onload = function(e) {
		 $('#'+member_profile_img).attr('src', e.target.result) 
		 .width(100)
		 .height(100);
		 }
		reader.readAsDataURL(input.files[0]);
	}
	
}

// 회원가입 버튼을 눌렀을 떄 유효성 검사
$(document).ready(function(){
	$('#writeBtn').click(function(){
		var jCont="";
		if ($('#member_name').val()==false) {
			jCont = '<div class="msg_error01">이름에 내용을 입력해주세요</div>';
			$('.item-input-info-Name').css('color', 'red').html(jCont);
			$('#member_name').focus();
			$('#member_name').addClass("error");
		}else if($('#member_nickname').val()==false){
			jCont = '<div class="msg_error33">닉네임에 내용을 해주세요.</div>';
			$('#member_nickname').focus();
			$('.item-input-info-NickName').css('color', 'red').html(jCont);
			$('#member_nickname').addClass("error");
		}else if($('#member_email').val()==false){
			jCont = '<div class="msg_error">이메일을 내용을 입력주세요.</div>';
			$('#member_email').focus();
			$('.item-input-info-Email').css('color', 'red').html(jCont);
			$('#member_email').addClass("error");
		}else if($('#member_pwd').val()==false){
			jCont='<div class="msg_pwderror">비밀번호에 내용을 입력해주세요.</div>';
			$('.item-input-info-Pwd').css('color','red').html(jCont);
			$('#member_pwd').focus();
			$('#member_pwd').addClass("error");
		}else if($('#member_repwd').val()==false){
			jCont='<div class="msg_repwder">비밀번호에 내용을 입력해주세요.</div>';
			$('.item-input-info-Repwd').css('color','red').html(jCont);
			$('#member_repwd').focus();
			$('#member_repwd').addClass("error")
		}else if ($("#member_servicecheck").prop("checked")==false){
			jCont = '<div class="msg_error01">약관을 확인하고 체크해주세요.</div>';
			$('.item-input-info-checkbox').css('color', 'red').html(jCont);
			$('#member_servicecheck').addClass("error");
		}else if($("#member_servicecheck").prop("checked")==true){ 
			$('#member_servicecheck').removeClass("error");
			$('#member_name').removeClass("error");
			$('#member_nickname').removeClass("error");
			$('#member_email').removeClass("error");
			$('#member_pwd').removeClass("error");
			$('#member_repwd').removeClass("error");
			$('form[name=writeForm]').submit();
		}
	});
});

//Login
$(document).ready(function(){
	$('#loginBtn').click(function(){
		var jCont="";
		var email=$('#member_email').val();
		var pwd=$('#member_pwd').val();
		var cheboxId=$('#cheboxid').prop('checked');
		if(email.length==0){
			jCont = '<div class="msg_error">이메일을 입력해주세요.</div>';
			$('.login-member-email-Div').css('color', 'red').html(jCont);
			$('#member_email').focus();
			$('#member_email').addClass("error");
		}else if(pwd.length==0){
			jCont = '<div class="msg_error">비밀번호를 입력해주세요.</div>';
			$('.login-member-pwd-Div').css('color', 'red').html(jCont);
			$('#member_pwd').focus();
			$('#member_pwd').addClass("error");
		}else{
			$.ajax({
				type:'post',
				url:'/bitcampmentor/member/login',
				data:{'member_email':email,'member_pwd':pwd,'cheboxid':cheboxId},
				dataType:'text',
				success:function(data){
					if (data=="login_ok") {
						location.href = '/bitcampmentor/main/index'
					} else if (data =="login_fail") {
						$('.login-Div').text('아이디 또는 비밀번호가 틀립니다').css('color', 'red');
					}
				},error:function(e){
					conlose.log(e);
					
				}
			});
			$('#member_email').removeClass("error");
			$('#member_pwd').removeClass("error");
		}
	});
});

