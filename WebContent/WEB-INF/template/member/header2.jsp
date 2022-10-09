<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark pt-3 pb-3">
  <div class="container-fluid">
    <a class="navbar-brand" href="${mycontext}/member/">HOTEL113</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/member/room/listroom?cPage=1" style="color:#fff;">객실보기</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/member/review/reviewlist" style="color:#fff;">후기게시판리스트</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/member/roomservice/info" style="color:#fff;">룸서비스</a>
        </li>
      </ul>
      
      <c:choose>
      	<c:when test="${sessionScope.sessionid == null }">
			<ul class="navbar-nav d-flex flex-row-reverse">
		        <li class="nav-item">
		          <a class="nav-link" href="${mycontext}/member/member/memberForm">회원가입</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="${mycontext}/member/etcloginform">로그인</a>
		        </li>
		      </ul>
		</c:when>
		<c:when test="${sessionScope.sessionid != null }">
			<ul class="navbar-nav d-flex flex-row-reverse">
		        <li class="nav-item">
		          <a class="nav-link" href="${mycontext}/member/member/myPage">마이페이지</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="${mycontext}/member/logout">로그아웃</a>
		        </li>
		        
        		<li class="nav-item"><a href="${mycontext}/member/reserve/reservelist" class="nav-link active" >예약확인</a></li>
		      </ul>
		</c:when>
      </c:choose>
    </div>
  </div>
</nav>