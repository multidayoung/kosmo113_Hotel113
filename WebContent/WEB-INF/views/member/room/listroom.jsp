<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<style>
   a:link{text-decoration:none;}
   .info {margin-bottom:20px;}
   .info p {margin:0; line-height:38px;}
   .thumbnail {margin-bottom:50px;}
   .thumbnail img {width:100%;}
   .thumbnail .caption {margin-top:10px;}
   
   /* paging */
   .paging {list-style:none; text-align:center; padding:0; margin:10px 0;}
   .paging li {display:inline-block;}
   .paging li.disable {padding:3px 10px; border:1px solid silver; color:silver;}
   .paging li.now {padding:3px 10px; border:1px solid #000; background:#000; color:white; font-weight:bold;}
   .paging li a {display:block; padding:3px 10px; border:1px solid #000; color:#000; font-weight:bold;}
   .paging li a:hover {background:#000; color:#fff; font-weight:bold;}
</style>
      
<article class="container">
   <div class="info d-flex justify-content-between">
      <p> �� ���� �� : ${roomcnt}��</p>
   </div>
   
   <div class="row">
      <c:forEach var="vo" items="${listroom}">
      <div class="col-sm-6 col-md-4">
         <div class="thumbnail">
            <img src="${mycontext}/resources/roomimg/${vo.rimg}">
            <div class="caption">
               <h3><b>${vo.rname}</b></h3>
               <p>
                  <b>�ο�</b> : ${vo.rmax}�� <br>
                  <b>����</b> : ${vo.rprice}��
               </p>
               <a href="${mycontext}/member/room/detailroom?rnum=${vo.rnum}" class="btn btn-primary">�󼼺���</a>
            </div>
         </div>
      </div>
      </c:forEach>
   </div>
   
   <ul class="paging">
      <c:choose>
         <c:when test="${startPage < 6}">
            <li class="disable">����</li>
         </c:when>
         <c:otherwise>
            <li><a href="listroom?cPage=${startPage-1}">����</a></li>
         </c:otherwise>
      </c:choose>
      <c:forEach varStatus="i" begin="${startPage}" end="${endPage}" step="1" >
         <c:choose>
            <c:when test="${i.index == nowPage}">
               <li class="now">${i.index}</li>
            </c:when>
            <c:otherwise>
               <li><a href="listroom?cPage=${i.index}">${i.index}</a></li>
            </c:otherwise>
         </c:choose> 
      </c:forEach>
      <c:choose>
         <c:when test="${endPage >= totalPage}">
            <li class="disable">����</li>
         </c:when>
         <c:otherwise>
            <li><a href="listroom?cPage=${endPage+1}">����</a></li>
         </c:otherwise>
      </c:choose>
   </ul>
</article>