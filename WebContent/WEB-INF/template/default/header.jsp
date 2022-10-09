<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark pt-3 pb-3">
  <div class="container-fluid">
    
     <c:choose>
      	<c:when test="${sessionScope.sessionid != null }">
      	<a class="navbar-brand" href="${mycontext}/manager/">HOTEL113 ������</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <c:if test="${sessionScope.sessionid == 'admin' }">
	        <li class="nav-item">
	          <a class="nav-link" href="${mycontext}/manager/admin/adminlist" style="color:#fff;">�����ڸ���Ʈ</a>
	        </li>
        </c:if>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/member/memberlist" style="color:#fff;">ȸ������Ʈ</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/room/listroom?cPage=1" style="color:#fff;">���Ǹ���Ʈ</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/reserve/adminreservelist" style="color:#fff;">���ฮ��Ʈ</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/roomservice/list" style="color:#fff;">�뼭�񽺸���Ʈ</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/comm/rcommList" style="color:#fff;">�ı�Խ��Ǵ�۸���Ʈ</a>
        </li>
      </ul>
      
      <ul class="navbar-nav d-flex flex-row-reverse">
        <li class="nav-item">
	      <a class="nav-link" href="${mycontext}/manager/member/detail">�������׾�������</a>
	    </li>
      	<li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/member/msgbox">Msg��</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/logout">�α׾ƿ�</a>
        </li>
      </ul>
      </div>
      </c:when>
		<c:when test="${sessionScope.sessionid == null }">
		<a class="navbar-brand" href="#">HOTEL113</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/member/room/listroom?cPage=1" style="color:#fff;">���Ǻ���</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${mycontext}/manager/member/detail" style="color:#fff;">�뼭��</a>
        </li>
      </ul>
      
      <ul class="navbar-nav d-flex flex-row-reverse">
		        <li class="nav-item">
		          <%-- a class="nav-link" href="${mycontext}/member/member/memberForm">ȸ������</a>--%>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="${mycontext}/member/etcloginform">�α���</a>
		        </li>
		      </ul>
		      </div>
		</c:when>
      </c:choose>
    </div>

</nav>