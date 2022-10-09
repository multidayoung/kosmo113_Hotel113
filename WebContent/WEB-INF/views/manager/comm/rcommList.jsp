<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<article class="container">
        <header>
            <h1>ReviewComm List Demo</h1>
        </header>
<table class="table table-bordered">
<thead>
   <tr>
		<th>게시글번호</th>
		<th>예약번호</th>
		<th>게시글제목</th>
		<th>게시글작성자</th>
		<th>게시글작성날짜</th>
		<th>답글작성자</th>
		<th>답글내용</th>
		<th>답글작성날짜</th>
		
	</tr>
</thead>
<tbody>
<c:forEach var="e" items="${rcommList }">
<tr>
<td class="align-middle text-center"><a href="rcview?renum=${e.renum }">${e.renum }</a></td>
<td class="text-center">${e.rvnum }</td>
<td class="align-middle"> ${e.retitle }</td>
<td class="align-middle"> ${e.rewriter }</td>
<td class="align-middle"> ${e.redate }</td>
<td class="align-middle"> ${e.comm.admin.aname }</td>
<td class="align-middle"> ${e.comm.rccontent }</td>
<td class="align-middle"> ${e.comm.rcdate }</td>

</tr>
</c:forEach>
</tbody>
</table>

</article>
