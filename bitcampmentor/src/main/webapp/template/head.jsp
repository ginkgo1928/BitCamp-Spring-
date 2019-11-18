<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
				<a class="logo" type="external" href="../main/index"> 
				<img src="../image/mentorlogo.jpg" /></a>
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
				<a class="button button-big" type="external" href="/mentors">멘토찾기</a>
				<a class="button button-big" type="external" href="/open_mentorings"> 콘텐츠 </a> 
				<a class="button button-big" type="external" href="/mentor_posts?featured_mentor_post=true">에세이</a>
				<div class="beta-div">
				<a class="button button-big program-button" type="external" href="/mentee_programs"> 모임 <span class='beta-text'>beta</span></a>
				</div>
				
				<a class="button button-big beta-div" type="external" href="/mentor_requests/new"> 멘토 지원하기 </a>
				<c:if test= "${memDTO==null}">
				<a class="button button-big" type="external" href="../member/writeForm">회원가입</a>
				<sec:authorize access="isAnonymous()">
				<a class="button button-big" type="external" href='<c:url value="/member/loginForm"/>'>로그인</a>
				</sec:authorize>
				</c:if>
				
				<c:if test="${memDTO!=null}">
				 <a type="internal" class="button button-big popover-open me-profile" data-popover=".js-me-popover" href="">
				 	<img src="../image/profile.jpg" width="28" height="28">
				</a>
				
				 <div class="popover popover-flat me-popover js-me-popover">
						<div class="popover-inner" >
							<div class="popover-angle on-top"></div>
							
							<div class="menu-list">
								<div class="list links-list no-hairlines-between">
									<ul>
										<li><a type="external" href="">멘토찾기</a></li>
										<li><a type="external" href="">콘텐츠</a></li>
										<li><a type="external" href="">에세이</a></li>
										<li><a type="external" href=""><span>모임</span><span class="beta-text">beta</span><span class="badge color-red alim">18</span></a></li>
										<li><a type="external" href="">멘토지원하기</a></li>
									</ul>
								</div>
								
								<div class="list links-list no-hairlines-between">
									<ul>
										<li><a type="external" href="">에세이 쓰기</a></li>
										<li><a type="external" href="">나의 질문/답변</a></li>
										<li><a type="external" href="">관심콘텐츠</a></li>
										<li><a type="external" href="">관심멘토</a></li>
									</ul>
								</div>
								
								<div class="list links-list no-hairlines-between">
									<ul>
										<li><a type="external" href="/bitcampmentor/member/modifyForm">계정설정</a></li>
										<sec:authorize access="isAuthenticated()">
										<li><a type="external" href="/bitcampmentor/member/logout">로그아웃</a></li>
										</sec:authorize>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</c:if>	
      </div>	
	</div>
</div>
</div>

