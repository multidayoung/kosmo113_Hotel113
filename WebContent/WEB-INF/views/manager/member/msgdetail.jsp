<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<article class="container">
<table class="table table-striped">
<tr>
<td>����</td>
<td>${vo.sub }</td>
</tr>
<tr>
<td>������</td>
<td>${vo.sender}</td>
</tr>
<tr>
<td>������</td>
<td>${vo.receiver}</td>
</tr>
<tr>
<td>����</td>
<td>${vo.cont}</td>
</tr>
<tr>
<td>��¥</td>
<td>${vo.sdate}</td>
</tr>
<tr>
<td colspan="2">
<input class="btn-primary" type="button" value="�ڷΰ���" onclick="location='msgbox'">
</td>
</tr>
</table>




</article>