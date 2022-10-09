<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark pt-3 pb-3">
  <div class="container-fluid">
    
     <c:choose>
      	<c:when test="${sessionScope.sessionid != null }">
      	<a class="navbar-brand" href="${mycontext}/manager/">HOTEL113 관리자</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <c:if test="${sessionScope.sessionid == 'admin' }">
	        <li class="nav-item">
	          <a class="nav-link" href="${mycontext}/manager/admin/adminlist" style="color:#fff;">관리자리스트</a>
	        </li>
        </c:if>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/member/memberlist" style="color:#fff;">회원리스트</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/room/listroom?cPage=1" style="color:#fff;">객실리스트</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/reserve/adminreservelist" style="color:#fff;">예약리스트</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/roomservice/list" style="color:#fff;">룸서비스리스트</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/comm/rcommList" style="color:#fff;">후기게시판답글리스트</a>
        </li>
      </ul>
      
      <ul class="navbar-nav d-flex flex-row-reverse">
        <li class="nav-item">
	      <a class="nav-link" href="${mycontext}/manager/member/detail">내정보및업무보기</a>
	    </li>
      	<li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/member/msgbox">Msg함</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/logout">로그아웃</a>
        </li>
      </ul>
      </div>
      </c:when>
		<c:when test="${sessionScope.sessionid == null }">
		<a class="navbar-brand" href="#">HOTEL113</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/member/room/listroom?cPage=1" style="color:#fff;">객실보기</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/member/detail" style="color:#fff;">룸서비스</a>
        </li>
      </ul>
      
      <ul class="navbar-nav d-flex flex-row-reverse">
		        <li class="nav-item">
		          <%-- a class="nav-link" href="${mycontext}/member/member/memberForm">회원가입</a>--%>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="${mycontext}/member/etcloginform">로그인</a>
		        </li>
		      </ul>
		      </div>
		</c:when>
      </c:choose>
    </div>

</nav>