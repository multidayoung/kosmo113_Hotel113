<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<article class="container">
<table class="table table-striped">
<tr>
<td>제목</td>
<td>${vo.sub }</td>
</tr>
<tr>
<td>보낸이</td>
<td>${vo.sender}</td>
</tr>
<tr>
<td>수취인</td>
<td>${vo.receiver}</td>
</tr>
<tr>
<td>내용</td>
<td>${vo.cont}</td>
</tr>
<tr>
<td>날짜</td>
<td>${vo.sdate}</td>
</tr>
<tr>
<td colspan="2">
<input class="btn-primary" type="button" value="뒤로가기" onclick="location='msgbox'">
</td>
</tr>
</table>




</article>