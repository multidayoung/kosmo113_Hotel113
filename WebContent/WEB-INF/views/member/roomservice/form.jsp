<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article class="container">
<div>
  <form class="row g-3" action="reserve" method="post" id="form">
<div>
  <div class="col-md-6">
    <label class="form-label">Reserve Num</label>
    
    <input type="text" class="form-control" value="${vo.rvnum }" readonly>
  </div>

  <div class="col-md-6">
    <label  class="form-label">Room Num</label>
    <input type="text" class="form-control" value="${vo.rnum }" readonly>
  </div>

  <div class="col-md-4">
    <label for="service" class="form-label">Service</label>
    <select id="service" name="service" class="form-select">
      <option selected>���� 1</option>
      <option>����2</option>
      <option>����3</option>
      <option>����4</option>
    </select>
  </div>

  <div class="col-md-4">
    <label for="date" class="form-label">Date</label>
    <select id="date" name="date" class="form-select">

    <c:forEach var="e" items="${dates }" varStatus="i">
      <c:choose>
       <c:when test="${i.count==1}">
       <option selected>${e}</option>
       </c:when>
       <c:otherwise>
        <option>${e} </option>
            </c:otherwise>
            </c:choose>
      </c:forEach>
    </select>
  </div>



  <div class="col-md-4">
    <label for="servicetime" class="form-label">Time</label>
    <select id="servicetime" name="servicetime" class="form-select">
      <option selected>����</option>
      <option>�ְ� </option>
      <option>����</option>
    </select>
  </div>  
  <div class="col-md-6">
     <input type="hidden" value="${vo.rvnum }" id="rvnum" name="rvnum">
  </div>
  <div class="col-md-6">
     <input type="hidden" value="" id="servicedate" name="servicedate">
  </div>
  
  <div class="col-12">
    <button type="button" class="btn btn-primary" onclick="checkBtn1()" id="">confirm</button>
  </div>
  </div>
</form>
<div id="liveAlertPlaceholder">

</div>
</div>
</article>
<script>
function checkBtn1() {
   let today = new Date();
   let ordertime = $('#servicetime').val();

   if(ordertime=='����'){
      ordertime = '07:00:00';
   }else if(ordertime=='�ְ�'){
      ordertime = '12:00:00';
   }else if(ordertime=='����'){
      ordertime = '18:00:00';
   }
   

   $('#servicedate').val($('#date').val()+" "+ordertime);
//   console.log('orderday:'+today.getFullYear()+"-"+(today.getMonth()+1)+"-"+today.getDate()+" "+ordertime)
//   console.log(Date.parse(today.getFullYear()+"-"+today.getMonth()+"-"+today.getDate()+" "+ordertime));
//   console.log('today:'+today);
//   console.log(today.getTime());

   console.log($('#date').val()+" "+ordertime);
   console.log(today.getTime());

   console.log($('#rvnum').val()+"&servicedate="+$('#date').val()+" "+ordertime);

   $.ajax({type:"post",
         url:"chk",
         data:{rvnum:$('#rvnum').val(),servicedate:$('#servicedate').val()},
               success: function(data) {
            console.log(data);
            if(data.trim()==0){
               if(Date.parse($('#date').val()+" "+ordertime)>today.getTime()){
                  console.log('possible');
                  alert("���༺��");
                  $('#form').submit();
                  
               }else{
                  console.log('impossile');
                  let cons = "<div class='alert alert-danger' role='alert'>"+$('#servicetime').val()+"���񽺸� ������ �� �ִ� �ð��� �ƴմϴ�<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button></div>";       
                  $('#liveAlertPlaceholder').html(cons);
               }
            }else{
               let cons = "<div class='alert alert-danger' role='alert'>"+"�ش������Ͻÿ� �̹� ���� �ϼ̽��ϴ�<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button></div>";       
               $('#liveAlertPlaceholder').html(cons);
            }
         }});

}

</script>