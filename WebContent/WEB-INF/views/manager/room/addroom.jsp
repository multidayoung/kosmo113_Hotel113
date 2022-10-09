<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<article style="max-width:767px; margin:0 auto;">
   <form method="post" action="addform" enctype="multipart/form-data" id="upform">
      <div class="row mb-3">
         <label class="col-sm-2 col-form-label">���ȣ</label>
         <div class="col-sm-8">
               <input type="text" name="rnum" id="rnum" class="form-control">
          </div>
          <button type="button" class="btn btn-secondary" id="check" style="width:15%;">�ߺ�Ȯ��</button>
         <div id="target"></div>
      </div>
      
      <div class="row mb-3">
         <label class="col-sm-2 col-form-label">���̸�</label>
         <div class="col-sm-10">
               <input type="text" name="rname" id="rname" class="form-control">
          </div>
      </div>
  
      <div class="row mb-3">
         <label class="col-sm-2 col-form-label">�ο�</label>
         <div class="col-sm-10" style="width:30%;">
            <select class="form-select" name="rmax" id="rmax">
               <c:forEach begin="1" end="10" varStatus="i">
                  <c:choose>
                     <c:when test="${i.count == 1}">
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
         <label class="col-sm-2 col-form-label">����</label>
         <div class="col-sm-10">
               <input type="text" name="rprice" id="rprice" class="form-control">
          </div>
      </div>
      
      <div class="row mb-3">
         <label class="col-sm-2 col-form-label">����</label>
         <div class="col-sm-10 form-floating">
               <textarea name="rdesc" id="rdesc" class="form-control" style="height:200px; padding-top:1rem;"></textarea>
          </div>
      </div>
      
      <div class="row mb-3">
         <label class="col-sm-2 col-form-label">�̹��� ���ε�</label>
         <div class="col-sm-10 mb-3">
            <input type="file" name="mfile" id="mfile" class="form-control">
         </div>
         <div class="text-center">
            <img src="${mycontext}/resources/roomimg/noimage.jpg" id="imgx">
         </div>
      </div>
      
      <div class="d-flex justify-content-end">
         <input type="submit" value="���" class="btn btn-primary"/>&nbsp;
         <input type="button" value="���" onclick="location='${mycontext}/manager/room/listroom?cPage=${num}'" class="btn btn-danger"/>
      </div>
   </form>
</article>

<script>
   $(function() {
      $('#check').click(function() {
         let param = $('#rnum').val();
         console.log(param);
         $.ajax({
            url:"checkroom?num="+param,
            success:function(data){
               console.log(data);
               if(data == 1) {
                  alert('��� �Ұ����� ���ȣ�Դϴ�.');
               } else {
                  alert('��� ������ ���ȣ�Դϴ�.');
               }
            }
         });
      });
   });
</script>