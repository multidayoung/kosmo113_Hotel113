<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="mycontext" value="${pageContext.request.contextPath }" ></c:set>

<article class="container">
<c:if test="${admin.ajob=='admin' }">
        <header>
            <h1>RoomServiceDetail</h1>
        </header>
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
         <form action="" method="post" id="form2">
<div>
 <div class="mb-4">
 <div class="row">
  <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">Num</label>
  <div class="col-sm-10">
    <input type="text" class="form-control form-control-lg" value="${detail.servicenum }" readonly>
  </div>
</div>       
 <div class="row">
  <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">호수</label>
  <div class="col-sm-10">
    <input type="text" class="form-control form-control-lg"   value="${detail.reserve.rnum }" readonly>
  </div>
</div>       
 
 <div class="row">
  <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">룸서비스명</label>
  <div class="col-sm-10">
    <input type="text" class="form-control form-control-lg"  value="${detail.service }" readonly>
  </div>
</div>
 
 <div class="row">
  <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">날짜</label>
  <div class="col-sm-10">
    <input type="text" class="form-control form-control-lg" id="servicetime"  value="${detail.servicedate }" readonly>
  </div>
</div>


 <div  class="row">
  <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">상태</label>
  <div  class="col-sm-10">
    <input id="statebox" type="text" class="form-control form-control-lg" id="servicestate"  value="${detail.servicestate }" readonly>
  </div>
  
</div>

 <div  class="row">
  <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">인원</label>
 <div class="col-sm-10" id="floatingTextarea2" style="height: 100px; border:1px solid gray;">

    <c:forEach var='e' items='${detail.worklist}'>
   <c:choose>
   <c:when test="${detail.servicestate!=4 }">
   <c:choose>


   <c:when test="${e.wstate==1 }">                  
                     
         <label class="btn btn-warning">         
      ${e.admin.aname } <input type="checkbox" value="${e.aid }" id="dellist" name="dellist" checked="checked" class="form-check-input-warning">
   </label>
   </c:when>


   <c:otherwise>
         <label class="btn btn-info">      
      ${e.admin.aname } <input type="checkbox" value="${e.aid }" id="dellist" name="dellist" checked="checked" class="form-check-input">
   </label>
   </c:otherwise>

   </c:choose>
   </c:when>
   <c:otherwise>
      ${e.admin.aname } <input type="checkbox" value="${e.aid }" id="dellist" name="dellist" checked="checked" disabled="disabled">
   </c:otherwise>
   </c:choose>
    </c:forEach>

    </div>
</div>
<div class="m-5">
 <figure class="figure">
  <img id="imgx" src="..." class="figure-img img-fluid rounded" alt="imgx">
  <figcaption class="figure-caption">...한 서비스입니다</figcaption>
</figure>
 </div>
 </div>
 <div class="form-check">

        <div>
<table class="table table-bordered">
    <thead>
      <tr>
        <th>사원번호</th>
        <th>사원명</th>
        <th>직책</th>
        <th>상태</th>
        <th>콜</th>
      </tr>
    </thead>
    <tbody>
<c:forEach var="vo" items="${list}">
<c:choose>
<c:when test="${vo.astate==1}">
   <tr class="table-primary">
      <td>${vo.anum}</td>
      <td>${vo.aname }</td>
      <td>${vo.ajob}</td>
      <td>${vo.astate}</td>
      <td>
<input type="checkbox" value="${vo.aid }" id="chklist" name="chklist">

  </td>            
   </tr>   
</c:when>
<c:when test="${vo.astate==2}">
   <tr class="table-primary">
      <td>${vo.anum}</td>
      <td>${vo.aname }</td>
      <td>${vo.ajob}</td>
      <td>${vo.astate}</td>
      <td>
<input type="checkbox" value="${vo.aid }" id="chklist" name="chklist" disabled="disabled">

  </td>            
   </tr>   
</c:when>
<c:when test="${vo.astate==4}">
   <tr class="table-danger">
      <td>${vo.anum}</td>
      <td>${vo.aname }</td>
      <td>${vo.ajob}</td>
      <td>${vo.astate}</td>
      <td>
<input type="checkbox" value="${vo.aid }" id="chklist" name="chklist" disabled="disabled">

      </td>            
   </tr>   
</c:when>
<c:otherwise>
   <tr class="table-warning">
      <td>${vo.anum}</td>
      <td>${vo.aname }</td>
      <td>${vo.ajob}</td>
      <td>${vo.astate}</td>
      <td>
      <input type="checkbox" value="${vo.aid }" id="chklist" name="chklist" disabled>
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
           <li class="page-item disabled" ><a class="page-link" href="${mycontext}/manager/roomservice/detail?servicenum=${detail.servicenum }&cPage=${nowPage-pagePerBlock}">Previous</a></li>
         </c:when>
         <c:otherwise>
           <li class="page-item" ><a class="page-link" href="${mycontext}/manager/roomservice/detail?servicenum=${detail.servicenum }&cPage=${nowPage-pagePerBlock}">Previous</a></li>
         </c:otherwise>        
         </c:choose>
   
      <c:forEach begin="${startPage}" end="${endPage}" varStatus="i">
      <c:choose>
      <c:when test="${nowPage eq (startPage+i.count-1)}">
        <li class="page-item active" ><a class="page-link" href="${mycontext}/manager/roomservice/detail?servicenum=${detail.servicenum }&cPage=${nowPage}">${nowPage}</a></li>
      </c:when>
      <c:otherwise>
        <li class="page-item" ><a class="page-link" href="${mycontext}/manager/roomservice/detail?servicenum=${detail.servicenum }&cPage=${startPage+i.count-1}">${startPage+i.count-1}</a></li>
      </c:otherwise>   
   
      </c:choose>

      </c:forEach>
         <c:choose>
         <c:when test="${(endPage)/5*6>(totalPage-1)/5*6}">
           <li class="page-item disabled" ><a class="page-link" href="${mycontext}/manager/roomservice/detail?servicenum=${detail.servicenum }&cPage=${endPage}">Next</a></li>
         </c:when>
         <c:otherwise>
         <c:choose>
         <c:when test="${nowPage+pagePerBlock>totalPage }">
           <li class="page-item" ><a class="page-link" href="${mycontext}/manager/roomservice/detail?servicenum=${detail.servicenum }&cPage=${totalPage}">Next</a></li>
         </c:when>
         <c:otherwise>
           <li class="page-item" ><a class="page-link" href="${mycontext}/manager/roomservice/detail?servicenum=${detail.servicenum }&cPage=${nowPage+pagePerBlock}">Next</a></li>
      </c:otherwise>
      </c:choose>
         </c:otherwise>        
         </c:choose>
      
      </ul>
 </th>
      </tr>
      <tr><td colspan="5" style="text-align: right;">
         <input type="hidden" id="servicenum" name="servicenum" value="${detail.servicenum }" >
         <input type="hidden" id="rnum" name="rnum"  value="${detail.reserve.rnum }" >
         <input type="hidden"  id="rvnum" name="rvnum" value="${detail.rvnum }" >
      <button type="button" class="btn btn-outline-secondary" onclick="location.href='list'" >업무리스트로</button>
      <button type="button" class="btn btn-outline-secondary" onclick="statechk(2)" >업무해제</button>
      <button type="button" class="btn btn-outline-secondary" onclick="statechk(1)" >인원추가</button></td></tr>
  
    </tbody>

  </table>

        </div>
              

</div>
</div>
      </form>
</c:if>
</article>

<script>

$(function() {
   $('div input').each(function(i, element) {
      if(i==4){
      let a = element.getAttribute("value");
      console.log(element.getAttribute("value"));
      if(a==3){
         element.setAttribute("value", "대기중");
      }else if(a==4){
         element.setAttribute("value", "완료");
         
      }else if(a==1){
         element.setAttribute("value", "예약");
      }else{
         
      }
      }
   });   
   $('tr').each(function(i, element) {
      if(i!=0){
      let a = $(this).children('td').eq(3).text();
      console.log(a);
      if(a==1){
         $(this).children().eq(3).text("업무가능");
      }else if(a==2){
         $(this).children().eq(3).text("확인중");
      }else if(a==3){
         $(this).children().eq(3).text("대기중/업무중");
      }else{
         $(this).children().eq(3).text("완료");
      }
      }
   });   
   $('div input').each(function(i, element) {
      if(i==2){
      let a = element.getAttribute("value");
      console.log(element.getAttribute("value"));
      if(a=='서비스 1'){
         imgx.setAttribute("src", "../resources/image/mio.png")
      }else if(a=='서비스 2'){
         imgx.setAttribute("src", "../resources/image/mio.png")
      }else{
         imgx.setAttribute("src", "../resources/image/mio.png")
      }
      }
   });   
});

function statechk(a) {
   console.log(a);
   if(a==1){
      $('#form2').attr("action", "update");
   }else if(a==2){
      $('#form2').attr("action", "delupdate");   
   }
   if($('tr td').eq(3).text()==2 ){
      
   }else{
      $('#form2').submit();
   }

}
</script>