<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
				<a class="logo" type="external" href="/"> 
				<img src="../image/mentorlogo.jpg"/>
				</a>
			</div>

			<form class="searchbar" id="searchbar-autocomplete" action="/searchs">
				<div class="searchbar-inner">
					<!-- <div class="searchbar-input-wrap">
						<input type="search" name="q" value=""
							placeholder="직무, 회사, 멘토, 제목" autocomplete="off"> 
							<i class="searchbar-icon"></i> <span class="input-clear-button"></span>
					</div> -->
				</div>
			</form>
		
			<div class="right">
				<a class="button button-big" type="external" href="/mentors">
					멘토찾기 </a> <a class="button button-big" type="external" href="/open_mentorings"> 콘텐츠 </a> 
					<a class="button button-big" type="external" href="/mentor_posts?featured_mentor_post=true">에세이 </a>
				<div class="beta-div">
					<a class="button button-big program-button" type="external" href="/mentee_programs"> 모임 
					<span class='beta-text'>beta</span>
					</a>
				</div>
				<a class="button button-big beta-div" type="external"
					href="/mentor_requests/new"> 멘토 지원하기 </a> 
					<a class="button button-big" type="external" href="../member/writeForm">회원가입</a>
				<a class="button button-big" type="external" href="../member/loginForm">로그인</a>
			</div>
		</div>
	</div>
</div>




