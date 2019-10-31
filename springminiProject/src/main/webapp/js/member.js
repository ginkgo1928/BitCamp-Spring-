$(document).ready(function () {
	$('#LoginBtn').click(function () {
		$('#logidDiv').empty();
		$('#pwdDiv').empty();
		if ($('#loginId').val() == '') {
			$('#logidDiv').text('아이디를 입력해주세요').css('color', 'red')
		} else if ($('#loginPwd').val() == '') {
			$('#logpwdDiv').text('비밀번호를 입력해주세요').css('color', 'red')
		} else {
			$.ajax({
				type: 'post',
				url: '/springminiProject/member/login',
				data: 'id=' + $('#loginId').val() + '&pwd=' + $('#loginPwd').val(),
				dataType: 'text',
				success: function (data) {
					if (data == 'login_ok') {
						location.href = '/springminiProject/main/index'
					} else if (data == 'login_fail') {
						$('#logpwdDiv').text('아이디 또는 비밀번호가 틀립니다').css('color', 'red')
					}
				}, error: function (err) {
					console.log(e);
				}
			});//aj
		}//el
	});
});

$(document).ready(function () {
	$('#writeBtn').click(function () {
		$('#nameDiv').empty();
		$('#idDiv').empty();
		$('#pwdDiv').empty();
		$('#repwdDiv').empty();
		if ($('#name').val() == '') {
			$('#nameDiv').text('이름을 입력해주세요').css('color', 'red')
		} else if ($('#id').val() == '') {
			$('#idDiv').text('아이디를 입력해주세요').css('color', 'red')
		} else if ($('#pwd').val() == '') {
			$('#pwdDiv').text('비밀번호를 입력해주세요').css('color', 'red')
		} else if ($('#pwd').val() != $('#repwd').val()) {
			$('#repwdDiv').text('비밀번호가 맞지 않습니다').css('color', 'red')
		} else if ($('#id').val() != $('#check').val()) {
			$('#idDiv').text('중복 체크해주세요').css('color', 'red')
		} else {
			$('form[name=writeForm]').submit();
		}
	});
});
$(document).ready(function () {
	$('#id').focusout(function () {
		if ($('#id').val() == '') {
			$('#idDiv').text('먼저 아이디를 입력해주세요').css('color', 'red')
		} else {
			$.ajax({
				type: 'post',
				url: '/springminiProject/member/checkId',
				data: 'id=' + $('#id').val(),
				dataType: 'text',
				success: function (data) {
					if (data == "exist")
						$('#idDiv').text('사용 불가능').css('color', 'red')
					else if (data == "not_exist")
						$('#idDiv').text('사용 가능').css('color', 'blue')
					$('#check').val($('#id').val());
				},
				error: function (err) {
					console.log(err);

				}

			});//aj
		}
	});
});
$(document).ready(function () {
	$('#checkPostBtn').click(function () {
		window.open("/springminiProject/member/checkPost", "", "width=500 height=500 scrollbars=yes");
	});
});


$(document).ready(function () {
	$('#postSearchBtn').click(function () {
		$('#sidoDiv').empty();
		$('#sigunguDiv').empty();
		$('#roadnameDiv').empty();
		if ($('#sido').val() == '시도선택')
			$('#sidoDiv').text('시도를 선택하세요').css('color', 'red')
		else if ($('#sido').val() != '세종' && $('#sigungu').val() == '')
			$('#sigunguDiv').text('시군구를 입력하세요').css('color', 'red')
		else if ($('#roadname').val() == '')
			$('#roadnameDiv').text('도로명을 입력하세요').css('color', 'red')
		else
			$.ajax({
				type: 'post',
				url: '/springminiProject/member/postSearch',
				data: $('#postSearchForm').serialize(),
				dataType: 'json',
				success: function (data) {
					//alert(JSON.stringify(data));
					//추가 제거
					//address.replact -주소값을 가져와서 null이 있으면 0으로 변환
					$('table tr:gt(2)').remove();
					$.each(data.list, function (index, items) {
						var address = items.sido + '' + items.sigungu + '' + items.yubmyundong + '' + items.ri + '' + items.buildingname;
						address = address.replace(/null/g, '');
						$('<tr/>').append($('<td/>', {
							align: 'center',
							text: items.zipcode
						})).append($('<td/>', {
							colspan: '3',
						}).append($('<a/>', {
							href: '#',
							id: 'addressA',
							text: address
						}))
						).appendTo($('#postTable'));
					});//each
					$('a').click(function () {
						//alert($(this).parent().prop('tagName'));-->현재 내 TB
						//앞에 있는 td를 가져오기 위해 prev
						//alert($(this).parent().prev().text());
						$('#daum_zipcode', opener.document).val($(this).parent().prev().text());
						$('#daum_addr1', opener.document).val($(this).text());
						//창이 두개 열리면 나한테 우선권이 있음.
						window.close();
					});
				},
				error: function (err) {
					console.log(err);
				}
		});//aj
	});
});






