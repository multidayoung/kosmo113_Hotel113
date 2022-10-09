<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article class="container">
 	<div class="container marketing">
	    <div class="row">
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#079CF2"/></svg>
	        <h2 class="mt-3"><b>�Ѵٿ�</b></h2>
	        <p><b>����</b><br>ȸ������, �ı� ���</p>
	      </div>
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#0566DB"/></svg>
	        <h2 class="mt-3"><b>������</b></h2>
	        <p><b>���� - �ٽ�</b><br>���� ���� �ý���</p>
	      </div>
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#079CF2"/></svg>
	        <h2 class="mt-3"><b>�ӵ���</b></h2>
	        <p><b>���� - �ٽ�</b><br>�뼭�� �ý���</p>
	      </div>
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#0566DB"/></svg>
	        <h2 class="mt-3"><b>������</b></h2>
	        <p><b>����</b><br>���� ����</p>
	      </div>
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#079CF2"/></svg>
	        <h2 class="mt-3"><b>�Ӻ���</b></h2>
	        <p><b>����</b><br>ȸ�� ����</p>
	      </div>
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#0566DB"/></svg>
	        <h2 class="mt-3"><b>������</b></h2>
	        <p><b>����</b><br>�α��� ����</p>
	      </div>
	      <div class="col-lg-3">
	        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"><rect width="100%" height="100%" fill="#079CF2"/></svg>
	        <h2 class="mt-3"><b>ȫ��ȣ</b></h2>
	        <p><b>����</b><br>�ı� ����</p>
	      </div>
	    </div>
    </div>
    <br>    
    <div>
    <h3 style="text-align: center;">������ �����ܺ��� ����</h3>
    <ol class="list-group list-group-numbered container" style="width: 40%">
    <c:forEach var="e" items="${list }" begin="0" end="4">
  		<li class="list-group-item d-flex justify-content-between align-items-start" 
  		onclick="location='${pageContext.request.contextPath}/member/room/detailfroom?rnum=${e.rnum}'"
  		style="cursor: pointer;">
    	<div class="ms-2 me-auto" >
      	<div class="fw-bold">${e.rnum }ȣ</div>
      		Content for list item
    	</div>
    	<span class="badge bg-primary rounded-pill">${e.cnt }</span>
  		</li>
 	 </c:forEach>
	</ol>
    </div>  	
</article>