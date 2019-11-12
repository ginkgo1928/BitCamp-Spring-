/* 
 * 날짜 : 2019.11.01 
 * 작성자 :ginkgo1928
 * 설명 : 회원가입 JavaScript 유효성 검사
 */

//Email 정규식 검사
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
	var reg = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,15}$/;
	return reg.test(str);
}

//Name 유효성 검사
function Ifn_NameCheck(){
	var name = $('#member_name').val();
	var reg = /[ 0-9\{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@#$%&\'\"\\\(\=]/gi;
	var jCont = "";
	if (name.length==0) {
		jCont = '<div class="msg_error01">이름을 입력해주세요.</div>';
		$('.item-input-info-Name').css('color', 'red').html(jCont);
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

//NickName 유효성 검사(isNicknameValid : 전역변수 사용)
var isNicknameValid = false;
$(document).ready(function(){
	$('#member_nickname').focusout(function(){
		var nickName=$("#member_nickname").val();
		var jCont="";
		if(nickName.length==0){
			jCont = '<div class="msg_error">닉네임을 입력해주세요.</div>';
			$('.item-input-info-NickName').css('color', 'red').html(jCont);
			$('#member_nickname').focus();
			$('#member_nickname').addClass("error");
			isNicknameValid =false;
		}else if(nickName.length< 3 || nickName.length>22){
			jCont = '<div class="msg_error">닉네임 3자~22자. 이하입니다.</div>';
			$('.item-input-info-NickName').css('color', 'red').html(jCont);
			$('#member_nickname').focus();
			$('#member_nickname').addClass("error");
			isNicknameValid= false;
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
				isNicknameValid=true;
					}else if(data=="not_exist"){
				jCont='<div class="msg_nickerror">입력하신 닉네임은 사용중인 닉네임 입니다.</div>';
				$('.item-input-info-NickName').css('color', 'red').html(jCont);
				isNicknameValid=false;
						}
			},error:function(e){
				conlose.log(e);
			}
			});
		}	
		isNicknameValid=true;
	});
});

//Email  유효성 검사(isEmailcheck : 전역변수 사용)
var isEmailcheck = false;
$(document).ready(function(){
	$('#member_email').focusout(function(){
		var Email=$("#member_email").val();
		var jCont="";
		if(Email.length==0){
			jCont = '<div class="msg_error">이메일을 입력해주세요.</div>';
			$('.item-input-info-Email').css('color', 'red').html(jCont);
			$('#member_email').addClass("error");
			isEmailcheck=false;	
		}else if(emailCheck(Email)){
			jCont = '<div class="msg_error">올바르지 않은 이메일 형식입니다.</div>';
			$('.item-input-info-Email').css('color', 'red').html(jCont);
			$('#member_email').addClass("error");
			isEmailcheck= false;
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
				isEmailcheck=true;
			}else if(data=="email_fail"){
				jCont='<div class="msg_emailerror">입력하신 이메일은 사용중인 이메일 입니다.</div>';
				$('.item-input-info-Email').css('color', 'red').html(jCont);
				isEmailcheck=false;
			}
			},error:function(e){
				conlose.log(e);
			}
		});
		isEmailcheck= true;
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
		$('#member_pwd').addClass("error");
		return false;
	}else if(Pwd.length< 8 ||Pwd.length >15){
		jCont='<div class="msg_pwderror">비밀번호는 8자~15자리 이하입니다.</div>';
		$('.item-input-info-Pwd').css('color','red').html(jCont);
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
		$('#member_repwd').addClass("error");
		return false;
	}else if($('#member_pwd').val()==$('#member_repwd').val()){
		jCont='<div class="msg_repwdok">일치합니다.</div>';
		$('.item-input-info-Repwd').css('color','blue').html(jCont);
		$('#member_repwd').removeClass("error");
		return true;
	}
}

//프로필 이미지 업로드 
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
//회원가입 체크 박스 검사 여부 (member_write : 전역변수 사용)
var member_write = false;
$(document).ready(function() {
	$(document).on("change","[data-check='check']",function() {
		var isWriteCheck = $('#member_servicecheck').prop('checked');
		var jCont = "";
		if (isWriteCheck == false) {
			jCont = '<div class="error_checkbox">약관을 확인하고 체크해주세요.</div>';
			$('.item-input-info-checkbox').css('color', 'red').html(jCont);
			$('#member_servicecheck').addClass("error");
			member_write = false;
		} else if (isWriteCheck !=false) {	
			$('#member_servicecheck').removeClass("error");
			member_write = true;
		}
	});
});
//회원 가입
$(document).ready(function(){
	$('#writeBtn').click(function(){
		if(Ifn_NameCheck()&&isEmailcheck==true&&isNicknameValid==true&&Ifn_PwdCheck()&&Ifn_RepwdCheck()&&member_write==true) {
			$('form[name=writeForm]').submit();
		}
	});
});

/* Email 저장을 눌렀을 JavaScript 쿠키 세팅*/
//Cookie Set
function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}
//Cookie Delete 
function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}
 
//Cookie Get
function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}
// Login 로그인
$(document).ready(function() {
	var member_Email = getCookie("Cookie_email"); // --> 저장된 저장된 Cookie의 값
	$("#member_email").val(member_Email); // Cookie담는다.
		if ($("#member_email").val() != "") { // --> 전에 이메일 저장을 눌렀을 경우 그대로 둔다.
			$("#cheboxid").attr("checked", true);
		
		}
		$("#cheboxid").change(function() { // --> Checkbox 변화 발생시
			if ($("#cheboxid").is(":checked")) {
				var member_Email = $("#member_email").val();
				setCookie("Cookie_email", member_Email, 7); // -->7일 동안 Cookie 보관
			} else {
				deleteCookie("Cookie_email");
			}
		});

		$("#member_email").keyup(function() {// Checkbox을 누르고 Email을 입력할때도 Cookie저장
			if ($("#cheboxid").is(":checked")) {
				var member_Email = $("#member_email").val();
				setCookie("Cookie_email", member_Email, 7);
			}
		});
		$("#loginBtn").click(function(){
		var jCont="";
		var email=$('#member_email').val();
		var pwd=$('#member_pwd').val();
			if(email.length==0){
				jCont = '<div class="msg_error">이메일을 입력해주세요.</div>';
				$('.login-member-email-Div').css('color', 'red').html(jCont);
				$('#member_email').focus();
				$('#member_email').addClass("error");
				return false;
			}else if(pwd.length==0){
				jCont = '<div class="msg_error">비밀번호를 입력해주세요.</div>';
				$('.login-member-pwd-Div').css('color', 'red').html(jCont);
				$('#member_pwd').focus();
				$('#member_pwd').addClass("error");
				return false;
			}else{
				$.ajax({
					type:'post',
					url:'/bitcampmentor/member/login',
					data:{'member_email':email,'member_pwd':pwd},
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
