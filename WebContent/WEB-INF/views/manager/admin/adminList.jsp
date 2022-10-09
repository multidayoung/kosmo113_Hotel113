<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<style>
	a:link{text-decoration:none;}
	table img {width:80px;}
	
	/* paging */
	.paging {list-style:none; text-align:center; padding:0; margin:10px 0;}
	.paging li {display:inline-block;}
	.paging li.disable {padding:3px 10px; border:1px solid silver; color:silver;}
	.paging li.now {padding:3px 10px; border:1px solid #000; background:#000; color:white; font-weight:bold;}
	.paging li a {display:block; padding:3px 10px; border:1px solid #000; color:#000; font-weight:bold;}
	.paging li a:hover {background:#000; color:#fff; font-weight:bold;}
</style>
      
<article class="container">
   <header>
      <h1>관리자 리스트</h1>
   </header>
   <ul class="list-unstyled">
      <li class="border-top my-3"></li>
   </ul>
   <div>
      <table class="table table-bordered">
         <thead>
            <tr>
               <th>고유번호</th>
               <th>아이디</th>
               <th>비번</th>
               <th>이름</th>
               <th>부서</th>
               <th>일상태</th>
               <th>가입날짜</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach var="e" items="${list }">
               <tr>
                  
                  <td>${e.anum }</td>
                  <td>${e.aid }</td>
                  <td>${e.apwd }</td>
                  <td>${e.aname }</td>
                  <td>${e.ajob }</td>
                  <td>${e.astate }</td>
                  <td>${e.adate }</td>
               </tr>
            </c:forEach>
         </tbody>
         <tfoot>
            <tr>
               <td colspan="7">
                  <ol class="paging">
                     <c:choose>
                        <c:when test="${startPage < 6 }">
                           <li class="page-item disable">이전으로</li>
                        </c:when>
                        <c:otherwise>
                           <li><a
                              href="adminlist?cPage=${startPage - 1 }">이전으로</a></li>
                        </c:otherwise>
                     </c:choose>
                     <!-- i.index 사용해서 페이징의 인덱스가 유지 -->
                     <c:forEach varStatus="i" begin="${startPage }" end="${endPage }"
                        step="1">
                        <c:choose>
                           <c:when test="${i.index == nowPage }">
                              <li class="now">${i.index }</li>
                           </c:when>
                           <c:otherwise>
                              <li><a href="adminlist?cPage=${i.index }">${i.index }</a></li>
                           </c:otherwise>
                        </c:choose>
                     </c:forEach>
                      <c:choose>
                        <c:when test="${endPage >= totalPage }">
                           <li class="page-item disable">다음으로</li>
                        </c:when>
                        <c:otherwise>
                           <li class="page-item"><a class="page-link" href="adminlist?cPage=${endPage + 1}">
                           다음으로</a></li>
                        </c:otherwise>      
                     </c:choose>
                  </ol>              
               </td>
            </tr>
         </tfoot>  
      </table>
 
   </div>
</article>