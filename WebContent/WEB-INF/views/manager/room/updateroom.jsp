<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<article style="max-width:767px; margin:0 auto;">
   <form method="post" action="updateform" enctype="multipart/form-data" id="upform">
      <div class="row mb-3">
         <label class="col-sm-2 col-form-label">방번호</label>
         <div class="col-sm-10">
            <p class="form-control mb-0">${vo.rnum}</p>
               <input type="hidden" name="rnum" value="${vo.rnum}"/>
          </div>
      </div>
      
      <div class="row mb-3">
         <label class="col-sm-2 col-form-label">방번호</label>
         <div class="col-sm-10">
            <input type="text" name="rname" id="rname" value="${vo.rname}" class="form-control"/>
          </div>
      </div>
      
      <div class="row mb-3">
         <label class="col-sm-2 col-form-label">인원</label>
         <div class="col-sm-10" style="width:30%;">
            <select class="form-select" name="rmax" id="rmax">
               <c:forEach begin="1" end="10" varStatus="i">
                  <c:choose>
                     <c:when test="${i.count == vo.rmax}">
                        <option selected="selected">${i.count}</option>
                     </c:when>
                     <c:otherwise>
                        <option>${i.count}</option>
                     </c:otherwise>
                  </c:choose>
               </c:forEach>
            </select>
         </div>
      </div>
      
      <div class="row mb-3">
         <label class="col-sm-2 col-form-label">가격</label>
         <div class="col-sm-10">
               <input type="text" name="rprice" id="rprice" value="${vo.rprice}" class="form-control"/>
          </div>
      </div>
      
      <div class="row mb-3">
         <label class="col-sm-2 col-form-label">설명</label>
         <div class="col-sm-10 form-floating">
               <textarea name="rdesc" id="rdesc" class="form-control" style="height:200px; padding-top:1rem;">${vo.rdesc}</textarea>
          </div>
      </div>
      
      <div class="row mb-3">
         <label class="col-sm-2 col-form-label">이미지 업로드</label>
         <div class="col-sm-10 mb-3">
            <input type="file" name="mfile" id="mfile" class="form-control">
            <input type="hidden" name="rimg" value="${vo.rimg}">
         </div>
         <div class="text-center">
            <img src="${mycontext}/resources/roomimg/${vo.rimg}" id="imgx">
         </div>
      </div>
      
      <div class="d-flex justify-content-end">
         <input type="submit" value="수정" class="btn btn-success"/>&nbsp;
         <input type="button" value="취소" onclick="location='${mycontext}/manager/room/detailroom?rnum=${vo.rnum}'" class="btn btn-danger"/>
      </div>
   </form>
</article>