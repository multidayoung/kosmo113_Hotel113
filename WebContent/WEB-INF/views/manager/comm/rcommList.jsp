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
		<th>�Խñ۹�ȣ</th>
		<th>�����ȣ</th>
		<th>�Խñ�����</th>
		<th>�Խñ��ۼ���</th>
		<th>�Խñ��ۼ���¥</th>
		<th>����ۼ���</th>
		<th>��۳���</th>
		<th>����ۼ���¥</th>
		
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
