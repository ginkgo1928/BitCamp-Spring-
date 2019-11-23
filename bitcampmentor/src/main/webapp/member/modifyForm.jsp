<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"  href="../css/mypg.css" type="text/css" />	

<body>
	<div class="page navbar-fixed settings accounts show" data-name="accounts-show">
		<div class="page-content">
			<div class="row">
				<div class="col-100 tablet-30">
					<div class="list no-hairlines no-hairlines-between">
						<ul>
							<li><a class="list-button color-gray item-link active" type="external" href=""> 계정 설정 </a></li>
							<li><a class="list-button color-gray item-link" type="external" href=""> 멘티 정보 </a></li>
							<li><a class="list-button color-gray item-link" type="external" href=""> 비밀번호 설정 </a></li>
						</ul>
					</div>
				</div>

				<div class="col-100 tablet-70">
					<div class="block block-strong no-hairlines hero-title">
						<h1 class="title">계정 설정</h1>
					</div>
					<div class="block inset">
						<form class="simple_form edit_user" id=""
							enctype="multipart/form-data" action="" method="post">
							<div class="list form-list no-hairlines">
							
								<ul>
									<div class="label-title">
										<label class="string required" for="user_name">이름 <abbr title="required">*</abbr></label>
									</div>

									<li class="item-content item-input">
										<div class="item-inner">
											<div class="item-input-wrap">
												<input class="is-valid string required" placeholder="이름" type="text" value="" name="" id="" />
											</div>
										</div>
									</li>
									
									<div class="label-title">
										<label class="string required" for="user_name">닉네임<abbr title="required">*</abbr></label>
									</div>
									
									<li class="item-content item-input">
										<div class="item-inner">
											<div class="item-input-wrap">
												<input class="is-valid string required" placeholder="닉네임" type="text" value="" name="" id=""/>
											</div>
										</div>
									</li>
									
									<div class="label-title">
										<label class="email required" for="user_email">이메일 주소<abbr title="required">*</abbr></label>
									</div>

									<li class="item-content item-input">
										<div class="item-inner">
											<div class="item-input-wrap">
												<input class=" is-valid string email  optional disabled" placeholder="이메일 주소" type="email" value="" name="" id="" />
											</div>
										</div>
									</li>

									<div class="label-title">
										<label class="file optional" for="user_profile_image">프로필 사진</label>
									</div>
									
									<div class="block no-margin no-padding">
										<div class="file-container">
											<div class="cover">
												<label for="member_profile" class="member-img-upbtn"> 이미지 업로드 </label>
												<input class="member_profile" accept=".jpg, .jpeg, .png" onchange="previewFile(this);" type="file" name="" id="member_profile" />
											</div>
										</div>
										<p><img id="member_profile_img" src="../image/profile.jpg" /></p>

										<div class="block-footer"> 
										- 얼굴이 포함된 사진을 등록해주세요.<br> 
										- 해당 사진의 최적 사이즈는 800 x 800px 입니다.<br>
										- 등록 가능한 파일 형식은 jpg, png, gif 입니다.<br>
										</div>
									</div>
								</ul>
							</div>
							<input type="button" name="commit" value="설정 저장하기" class="btn button button-big button-fill" data-disable-with="저장중..." />
						</form>
						</div>
						<div class="block block-strong inset">
							<div class="block-footer">
							<div>
								<a class="button button-inline color-gray delete-account-button" href="">회원탈퇴</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
