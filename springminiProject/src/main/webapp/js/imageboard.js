$(document).ready(function () { //등록 
	$('#imageboardWriteBtn').click(function () {
		$('#imageIdDiv').empty();
		$('#imageNameDiv').empty();
		$('#imagePriceDiv').empty();
		$('#imageQtyDiv').empty();
		$('#imageContentDiv').empty();
		$('#imageImgDiv').empty();

		if ($('#imageId').val() == '') {
			$('#imageIdDiv').text("상품코드를 입력하세요.").css('color', 'red').css('font-size', '8pt');
			$('#imageId').focus();
		} else if ($('#imageName').val() == '') {
			$('#imageNameDiv').text("상품명를 입력하세요.").css('color', 'red').css('font-size', '8pt');
			$('#imageName').focus();
		} else if ($('#imagePrice').val() == '') {
			$('#imagePriceDiv').text("상품 단가를 입력하세요.").css('color', 'red').css('font-size', '8pt');
			$('#imagePrice').focus();
		} else if ($('#imageQty').val() == '') {
			$('#imageQtyDiv').text("상품 개수를 입력하세요.").css('color', 'red').css('font-size', '8pt');
			$('#imageQty').focus();
		} else if ($('#imageContent').val() == '') {
			$('#imageContentDiv').text("상품 단가를 입력하세요.").css('color', 'red').css('font-size', '8pt');
			$('#imageContent').focus();
		} else {
			//$('#imageboardWriteForm').submit();
			var formData = new FormData($('#imageboardWriteForm')[0]);
			$.ajax({
				type: 'post',
				enctype: 'multipart/form-data',
				processData: false, //데이터를 컨텐트 타입에 맞게 변환 여부
				contentType: false, //요청 컨텐트 타입, contentType: "application/json"
				url: '/springminiProject/imageboard/imageboardWrite',
				data: formData,
				success: function () {
					alert("이미지 등록 성공");
					location.href='/springminiProject/imageboard/imageboardList';
				},
				error: function (err) {
					console.log(err);
				}
			});


		}
	});
});

