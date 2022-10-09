<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<article class="container">
        <header>
            <h1>ReviewComm Demo</h1>
        </header>
<table class="table table-bordered">
<thead>
   <tr>
		<th>게시글번호</th>
		<th>관리자번호</th>
		<th>내용</th>
		<th>작성날짜</th>
	</tr>
</thead>
<tbody>
<c:forEach var="e" items="${commList }">
<tr>
<td class="align-middle text-center"><a href="commDetail?renum=${e.renum }">${e.renum }</td>
<td class="text-center">${e.anum }</td>
<td class="align-middle"> ${e.rccontent }</td>
<td class="align-middle"> ${e.rcdate }</td>

</tr>
</c:forEach>
</tbody>
</table>
</article>