/* 
 * 날짜 : 2019.11.01 
 * 작성자 :ginkgo1928
 * 설명 : 회원가입 JavaScript
 */


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

function Ifn_NameCheck() {
	var name = $("#member_name").val();
	var reg = /[ 0-9\{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@#$%&\'\"\\\(\=]/gi;
	var jCont = "";
	
	if (reg.test(name)) {
		jCont = '<div class="msg_error">한글,영문만 입력가능 합니다.</div>';
		$('.item-input-info-Name').css('color', 'red').html(jCont);
		$('#member_name').focus();
		$('#member_name').addClass("error");
		
	}else {
		jCont = "";
		$('#member_name').removeClass("error");
		$('.item-input-info-Name').html(jCont);
	}
	
}


function Ifn_NickCheck(){
	var nickName=$("#member_nickname").val();
	var jCont="";
	
	if(nickName.length==0){
		jCont = '<div class="msg_error 01">닉네임을 입력해주세요.</div>';
		$('.item-input-info-NickName').css('color', 'red').html(jCont);
		$('#member_nickname').focus();
		$('#member_nickname').addClass("error");
		
	}else if(nickName.length< 3 || nickName.length>22){
		jCont = '<div class="msg_error 02">닉네임 3자~22자. 이하입니다.</div>';
		$('.item-input-info-NickName').css('color', 'red').html(jCont);
		$('#member_nickname').focus();
		$('#member_nickname').addClass("error");
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
	
}

function Ifn_EmailCheck(){
	var Email=$("#member_email").val();
	var jCont="";
	if(Email.length==0){
		jCont = '<div class="msg_error 03">이메일을 입력해주세요.</div>';
		$('.item-input-info-Email').css('color', 'red').html(jCont);
		$('#member_email').focus();
		$('#member_email').addClass("error");
	
		
	}else if(emailCheck(Email)){
		jCont = '<div class="msg_error 03">올바르지 않은 이메일 형식입니다.</div>';
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
	}

}

function Ifn_PwdCheck(){
	var Pwd=$('#member_pwd').val();
	var jCont="";
	if(Pwd.length==0){
		jCont='<div class="msg_pwderror">비밀번호를 입력해주세요.</div>';
		$('.item-input-info-Pwd').css('color','red').html(jCont);
		$('#member_pwd').focus();
		$('#member_pwd').addClass("error");
	}else if(Pwd.length< 8 ||Pwd.length >15){
		jCont='<div class="msg_pwderror">비밀번호는 8자~15자리 이하입니다.</div>';
		$('.item-input-info-Pwd').css('color','red').html(jCont);
		$('#member_pwd').focus();
		$('#member_pwd').addClass("error");
	}else {
		jCont='<div class="msg_pwdok">사용가능 합니다.</div>';
		$('.item-input-info-Pwd').css('color','blue').html(jCont);
		$('#member_pwd').removeClass("error");
	}
}

function Ifn_RepwdCheck(){
	if($('#member_pwd').val()!=$('#member_repwd').val()){
		jCont='<div class="msg_repwderror">비밀번호가 일치하지 않습니다.</div>';
		$('.item-input-info-Repwd').css('color','red').html(jCont);
		$('#member_repwd').focus();
		$('#member_repwd').addClass("error");
	}else if($('#member_pwd').val()==$('#member_repwd').val()){
		jCont='<div class="msg_repwdok">일치합니다.</div>';
		$('.item-input-info-Repwd').css('color','blue').html(jCont);
		$('#member_repwd').removeClass("error");
	}
}

$(document).ready(function(){
	$('#commitBtn').click(function(){
		if(Ifn_NameCheck()){
			
		}
	});
});
