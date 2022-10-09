<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<c:set var="list">cat01.jpg,mio.png,Shrek01.jpg,spongebob.png</c:set>
<style>
	img{
		width:200px; height:200px;
	}
</style>
<article class="container">
<h3>RoomService Info</h3>
<table class="table">
<thead>
<tr>
<td scope="col">��������</td>
<td scope="col">���񽺸�</td>
<td scope="col">����</td>
<td scope="col">100000</td>
</tr>
</thead>
<tbody>
<c:forEach var="e" items="${list }" varStatus="i" >
<tr>
<td scope="col"><img src="${ctx}/resources/image/${e}" class="img-thumbnail" alt="service1"></td>
<td scope="col">���� ${i.count }</td>
<td scope="col">�ֻ��� ���� ${i.count } �ְ��� ǰ���� ����</td>
<td scope="col">100000</td>
</tr>
</c:forEach>
</tbody>
</table>
</article>