<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<article class="container">
<div class="container boder-danger">

<table class="table table-striped">
<thead>
<tr>
<th>번호</th>
<th>보낸이</th>
<th>받는이</th>
<th>제목</th>
<th>날짜</th>
</tr>
</thead>


<tbody>
<c:forEach var="e" items="${msglist }" varStatus="i" >
<tr>
<td>${fn:length(msglist)-i.count}<span hidden="hidden">${e.msgnum }</span></td>
<td>${e.sender }</td>
<td>${e.receiver }</td>
<td>${e.sub }</td>
<td>${e.sdate }</td>
</tr>
</c:forEach>

</tbody>
</table>
</div>
</article>
<script>
$(function() {
	$('table tr').click(function() {
				console.log(1);
				console.log("msgdetail?msg="+$(this).children('td').children('span').text());
				location="msgdetail?msgnum="+$(this).children('td').children('span').text();
			});

});

</script>