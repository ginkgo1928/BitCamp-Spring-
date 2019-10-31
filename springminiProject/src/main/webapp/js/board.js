$(document).ready(function () { //등록 
	$('#BoardBtn').click(function () {
		$('#boardWri').empty();
		if ($('#subject').val() == '') {
			$('#boardWri').text("제목을 입력해주세요").css('color', 'red')
		} else if ($('#content').val() == '') {
			$('#boardWri').text("내용을 입력해주세요").css('color', 'red')
		} else {
			$.ajax({
				type: 'post',
				url: '/springminiProject/board/boardWrite',
				data: { 'subject': $('#subject').val(), 'content': $('#content').val() },
				success: function () {
					alert("글쓰기 성공");
					location.href = '/springminiProject/board/boardList';

				},
				error: function (err) {
					console.log(err);
				}
			});
		}
	});
}); 

$(document).ready(function () { //list
	$.ajax({
		type: 'post',
		url: '/springminiProject/board/getBoardList',
		data: 'pg=' + $('#pg').val(),
		dataType: 'json',
		success: function (data) {
			//alert(JSON.stringify(data));
			$.each(data.list, function (index, items) {

				$('<tr/>').append($('<td/>', {
					align: 'center',
					text: items.seq
				})).append($('<td/>', {
					
				}).append($('<a/>', {
					href: 'javascript:void(0)',
					text: items.subject,
					id: 'subjectA',
					class: items.seq + ''
				}))).append($('<td/>', {
					align: 'center',
					text: items.id
				})).append($('<td/>', {
					align: 'center',
					text: items.logtime
				})).append($('<td/>', {
					align: 'center',
					text: items.hit
				})).appendTo($('#boardListTable'));
				//답글 이미지(공백)-before(앞에 붙어라),
				for(i=0; i<items.lev; i++){
					$('.'+items.seq).before('&emsp;');
				}
				if(items.pseq!=0){
					$('.'+items.seq).before($('<img/>',{
						src:'../img/reply.gif'
					}));

				}
			});//each

			//페이징처리
			$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
			//로그인 여부 
			//(선택자).on(function(이벤트,자손))
			$('#boardListTable').on('click', '#subjectA', function () {
				//alert($(this).text());
				if (data.memId == null)
					alert("먼저 로그인 하세요.");

				else
					//alert($(this).parent().prev().text());
					//alert($(this).attr('class'))--seq값 물어보기;
					location.href='/springminiProject/board/boardView?seq='
						+$(this).attr('class')+'&pg='+$('#pg').val();
			});//subjectA
		},error: function (e) {
			console.log(e);
		}
	});
});


$(document).ready(function () {
	//트리거가 이벤트를 발생했을 때 내가 이벤트와 문자열을 받는다.
	$('#boardSearchBtn').click(function (event,str) {
		//넘어온 문자가 trigger 아닐때 pg 값을 1로 줘.
		if(str!='trigger')$('input[name=pg]').val(1);

		if ($('input[name=keyword]').val() == '') {
			alert("검색할 단어를 입력해주세요.");

		} else {
			$.ajax({
				type: 'post',
				url: '/springminiProject/board/boardSearch',
				data: $('#boardSearchForm').serialize(),
				dataType:'json',
				success:function(data){
					//alert(JSON.stringify(data));
					 $('#boardListTable tr:gt(0)').remove(); 

					 $.each(data.list, function(index,items){
					$('<tr/>').append($('<td/>',{
						align: 'center',
						text: items.seq
					})).append($('<td/>',{
						
					}).append($('<a/>', {
						href: 'javascript:void(0)',
						text: items.subject,
						id: 'subjectA',
						class: items.seq+''
					}))).append($('<td/>',{
						align: 'center',
						text: items.id
					})).append($('<td/>',{
						align: 'center',
						text: items.logtime
					})).append($('<td/>',{
						align: 'center',
						text: items.hit
					})).appendTo($('#boardListTable'));
				});
				//검색 페이징 
				$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
				},error:function(err){
					console.log(err);
				},
			});//aj
		}//el
	});//ci	
}); //JQ

$(document).ready(function () {
	$('#bodarReplyBtn').click(function () {
		$('#subjectDiv').empty();
		$('#contentDiv').empty();

		if ($('#subject').val() == '')
			$('#subjectDiv').text('제목을 입력하세요').css('color', 'red').css('font-size', '8pt');
		else if ($('#content').val() == '')
			$('#contentDiv').text('내용을 입력하세요').css('color', 'red').css('font-size', '8pt');
		else
			$.ajax({
				type: 'post',
				url: '/springminiProject/board/boardReply',
				/*
				data: {'pseq' : '${pseq}',
					   'subject': $('#subject').val(),
					   'content': $('#content').val()},
				*/
				data: $('##boardReplyForm').serialize(),
				success: function () {
					alert("답글쓰기 성공");
					location.href='/springminiProject/board/boardList?pg=${pg}';
				},
				error: function (err) {
					console.log(err);
				}
			});
	});
});