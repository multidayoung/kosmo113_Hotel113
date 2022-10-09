<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article class="container">
 	<div class="container marketing">
	    <div class="row">
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#079CF2"/></svg>
	        <h2 class="mt-3"><b>한다영</b></h2>
	        <p><b>팀장</b><br>회원가입, 후기 답글</p>
	      </div>
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#0566DB"/></svg>
	        <h2 class="mt-3"><b>김태형</b></h2>
	        <p><b>팀원 - 핵심</b><br>객실 예약 시스템</p>
	      </div>
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#079CF2"/></svg>
	        <h2 class="mt-3"><b>임동명</b></h2>
	        <p><b>팀원 - 핵심</b><br>룸서비스 시스템</p>
	      </div>
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#0566DB"/></svg>
	        <h2 class="mt-3"><b>구지혜</b></h2>
	        <p><b>팀원</b><br>객실 관리</p>
	      </div>
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#079CF2"/></svg>
	        <h2 class="mt-3"><b>임병렬</b></h2>
	        <p><b>팀원</b><br>회원 관리</p>
	      </div>
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#0566DB"/></svg>
	        <h2 class="mt-3"><b>조예찬</b></h2>
	        <p><b>팀원</b><br>로그인 관리</p>
	      </div>
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#079CF2"/></svg>
	        <h2 class="mt-3"><b>홍영호</b></h2>
	        <p><b>팀원</b><br>후기 관리</p>
	      </div>
	    </div>
    </div>
    <br>    
    <div>
    <h3 style="text-align: center;">고객들이 눈여겨보는 객실</h3>
    <ol class="list-group list-group-numbered container" style="width: 40%">
    <c:forEach var="e" items="${list }" begin="0" end="4">
  		<li class="list-group-item d-flex justify-content-between align-items-start" 
  		onclick="location='${pageContext.request.contextPath}/member/room/detailfroom?rnum=${e.rnum}'"
  		style="cursor: pointer;">
    	<div class="ms-2 me-auto" >
      	<div class="fw-bold">${e.rnum }호</div>
      		Content for list item
    	</div>
    	<span class="badge bg-primary rounded-pill">${e.cnt }</span>
  		</li>
 	 </c:forEach>
	</ol>
    </div>  	
</article>