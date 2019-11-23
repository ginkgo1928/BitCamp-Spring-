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
//Login 로그인 (아이디 저장을 누르면 JavaScript Cookie에서 저장)
$(document).ready(function() {
	var member_Email = getCookie('Cookie_email'); // --> 저장된 저장된 Cookie의 값
	$('#member_email').val(member_Email); // Cookie담는다.
		if ($('#member_email').val() != '') { // --> 전에 이메일 저장을 눌렀을 경우 그대로 둔다.
			$('#cheboxid').attr('checked', true);
		}
	$('#cheboxid').change(function() { // --> Checkbox 변화 발생시
		if ($('#cheboxid').is(':checked')) {
			var member_Email = $('#member_email').val();
			setCookie('Cookie_email', member_Email, 7); // -->7일 동안 Cookie 보관
		} else {
				deleteCookie('Cookie_email');
		}
	});
	$('#member_email').keyup(function() {// Checkbox을 누르고 Email을 입력할때도 Cookie저장
		if ($('#cheboxid').is(':checked')) {
			var member_Email = $('#member_email').val();
			setCookie('Cookie_email', member_Email, 7);
		}
	});
	$('#loginBtn').click(function(){
	var jCont = '';
	var email = $('#member_email').val();
	var pwd = $('#member_pwd').val();
		if (email.length == 0) {
			jCont = '<div class="msg_error">이메일을 입력해주세요.</div>';
			$('.login-member-email-Div').css('color', 'red').html(jCont);
		} else if (pwd.length == 0) {
			jCont = '<div class="msg_error">비밀번호를 입력해주세요.</div>';
			$('.login-member-pwd-Div').css('color', 'red').html(jCont);
		} else if (email != 0 && pwd != 0) {
			$('.login-member-pwd-Div').remove();
			$('.login-member-email-Div').remove();
			$('form[name=loginForm]').submit();
			}
		});
	
	});

function isgetLogin(status) {
	if (status == 'fail') {
		document.addEventListener("DOMContentLoaded", function(event) {
			var toastTop = app.toast.create({
				text : '이메일 또는 비밀번호가 잘못되었습니다.',
				position : 'top',
				closeButton : true,
			});
			toastTop.open();
		});
	}else if(status=='error'){
		document.addEventListener("DOMContentLoaded", function(event) {
			var toastTop = app.toast.create({
				text : '이미 로그인 되어있습니다.',
				position : 'top',
				closeButton : true,
			});
			toastTop.open();
		});
	}
}

