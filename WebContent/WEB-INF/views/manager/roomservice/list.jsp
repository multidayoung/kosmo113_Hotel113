<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <article class="container">
<c:if test="${admin.ajob=='admin' }">
        <header>
            <h1>RoomService Work State</h1>
        </header>
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
        <div>
<table class="table table-bordered">
    <thead>
      <tr>
        <th>번호</th>
        <th>예약/사용자번호</th>
        <th>서비스명</th>
        <th>서비스 시간</th>
        <th>명단</th>
      </tr>
    </thead>
    <tbody>
<c:forEach var="vo" items="${list}">
<c:choose>
<c:when test="${vo.servicestate==1}">

   <tr class="table-primary">
      <td><span hidden="hidden">detail?servicenum=${vo.servicenum}</span>${vo.servicenum}</td>
      <td>${vo.rvnum}</td>
      <td>${vo.service}</td>
      <td>${vo.servicedate}</td>
      <td>
          <c:forEach var="e" items="${vo.worklist }">
         ${e.admin.aname }
         </c:forEach> 
      </td>            
   </tr>   


</c:when>
<c:when test="${vo.servicestate==3}">
   <tr class="table-warning">
      <td><span hidden="hidden">detail?servicenum=${vo.servicenum}</span>${vo.servicenum}</td>
      <td>${vo.rvnum}</td>
      <td>${vo.service}</td>
      <td>${vo.servicedate}</td>
      <td>
          <c:forEach var="e" items="${vo.worklist }">
         ${e.admin.aname }
         </c:forEach> 
      </td>                  
   </tr>   
</c:when>
<c:otherwise>
<tr class="table-danger">
   <td><span hidden="hidden">detail?servicenum=${vo.servicenum}</span>${vo.servicenum}</td>
   <td>${vo.rvnum}</td>
   <td>${vo.service}</td>
   <td>${vo.servicedate}</td>
      <td>
          <c:forEach var="e" items="${vo.worklist }">
         ${e.admin.aname }
         </c:forEach> 
      </td>               
</tr>      
</c:otherwise>
</c:choose>
</c:forEach>
                 <tr>
<th colspan="5" style="text-align: center; ">
      <ul class="pagination" style="margin:0 auto; width: 35%">
         <c:choose>
         <c:when test="${nowPage<6}">
           <li class="page-item disabled" ><a class="page-link" href="${pageContext.request.contextPath}/manager/roomservice/list?cPage=${nowPage-pagePerBlock}">Previous</a></li>
         </c:when>
         <c:otherwise>
           <li class="page-item" ><a class="page-link" href="${pageContext.request.contextPath}/manager/roomservice/list?cPage=${nowPage-pagePerBlock}">Previous</a></li>
         </c:otherwise>        
         </c:choose>
   
      <c:forEach begin="${startPage}" end="${endPage}" varStatus="i">
      <c:choose>
      <c:when test="${nowPage eq (startPage+i.count-1)}">
        <li class="page-item active" ><a class="page-link" href="${pageContext.request.contextPath}/manager/roomservice/list?cPage=${nowPage}">${nowPage}</a></li>
      </c:when>
      <c:otherwise>
        <li class="page-item" ><a class="page-link" href="${pageContext.request.contextPath}/manager/roomservice/list?cPage=${startPage+i.count-1}">${startPage+i.count-1}</a></li>
      </c:otherwise>   
   
      </c:choose>

      </c:forEach>
         <c:choose>
         <c:when test="${(endPage)/5*6>(totalPage-1)/5*6}">
           <li class="page-item disabled" ><a class="page-link" href="${pageContext.request.contextPath}/manager/roomservice/list?cPage=${endPage}">Next</a></li>
         </c:when>
         <c:otherwise>
         <c:choose>
         <c:when test="${nowPage+pagePerBlock>totalPage }">
           <li class="page-item" ><a class="page-link" href="${pageContext.request.contextPath}/manager/roomservice/list?cPage=${totalPage}">Next</a></li>
         </c:when>
         <c:otherwise>
           <li class="page-item" ><a class="page-link" href="${pageContext.request.contextPath}/manager/roomservice/list?cPage=${nowPage+pagePerBlock}">Next</a></li>
      </c:otherwise>
      </c:choose>
         </c:otherwise>        
         </c:choose>
      
      </ul>
 </th>
</tr>
    </tbody>
  </table>
        </div>
 </c:if>
</article>
<script>
$(function() {
   let trs=$('tbody tr');
   trs.click(function() {
      if(!$(this).is(trs.last())){
      let str = $(this).children('td').children('span').text();
      console.log(str);
      console.log($(this).children('td').val());
      location = str;
      }
   });
   let a = "";
   trs.hover(function() {
      if(!$(this).is(trs.last())){
      a=$(this).attr("class");
      console.log(a);
      $(this).attr("class", "table-success");
      }
   }, function() {
      if(!$(this).is(trs.last())){
      $(this).attr("class", a);
      }
   });

         

});

</script>