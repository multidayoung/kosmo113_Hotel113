<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<style>
   article {max-width:767px; margin:0 auto;}
   article img {max-width:767px;}
   table {width:100%; margin:20px 0;}
   table th {width:15%; background:#eee; padding:20px;}
   table td {width:85%; padding:20px;}
</style>

<article>
   <h1 class="text-center m-4"><b>${vo.rname}</b></h1>
   <div class="text-center">
      <img src="${pageContext.request.contextPath}/resources/roomimg/${vo.rimg}">
   </div>
   <table>
      <tr>
         <th>방이름</th>
         <td>${vo.rname}</td>
      </tr>
      <tr>
         <th>인원</th>
         <td>${vo.rmax}명</td>
      </tr>
      <tr>
         <th>가격</th>
         <td>${vo.rprice}원</td>
      </tr>
      <tr>
         <th>내용</th>
         <td>${vo.rdesc}</td>
      </tr>
   </table>
   <div class="d-flex justify-content-end">
      <input type="button" value="목록" onclick="location='${mycontext}/member/room/listroom?cPage=${nowPage}'" class="btn btn-primary">&nbsp;
      <input type="button" value="예약하기" onclick="location='${mycontext}/member/reserve/reservepage?rnum=${vo.rnum}&rprice=${vo.rprice }'" class="btn btn-secondary"/>
   </div>
</article>