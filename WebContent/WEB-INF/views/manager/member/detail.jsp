<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<article class="container">
<div class="fixed-top">
</div>

<div>
<div class="mb-3">
  <label for="aname" class="form-label">
  	�̸�  
  </label>
  <input type="text" class="form-control" id="aname" name="aname" value="${admin.aname }" readonly="readonly">
    <input type="hidden" name="servicenum" value="${e.servicenum }">
  <input type="hidden" name="aid" value="${admin.aid }">
</div>

<div class="mb-3">
  <label for="id" class="form-label">
  	ID 
  </label>
  <input type="text" class="form-control" name="aid" value="${admin.aid }"  readonly="readonly">
</div>

<div class="mb-3">
  <label for="ajob" class="form-label">
  	��å 
  </label>
  <input type="text" class="form-control"   value="${admin.ajob }" readonly="readonly">
</div>
<div>
<label>
��������Ʈ
</label>
<div class="container border">
<form action="" id="form3" method="post">
 <input type="hidden" name="aid" value="${admin.aid }">
 <input type="hidden" id="servicenum" name="servicenum" value="">
 
<c:if test="${fn:length(admin.worklist)!=0 }">
<c:forEach var="e" items="${admin.worklist }">

<div class="mb-3">
 
  <label for="name" class="form-label">
	${e.servicenum }

  </label>
  <div class="container border"  id="${e.servicenum }"  >
 	  <p>��ȣ :${e.servicenum }</p>
  	<p>  ȣ��:${e.roomservice.reserve.rnum }</p>
	<p>  ����:<span >${e.roomservice.servicestate}</span></p>
	<p>  ��¥ :${e.roomservice.servicedate }</p>
	<p>  ���񽺸�:${e.roomservice.service }</p>
	<p> ���</p>
	<p>  <c:forEach var="j" items="${e.roomservice.worklist }">${j.admin.ajob }: ${j.admin.aname }  </c:forEach></p>

  </div>
  
  <div >
  <c:choose >
  <c:when test="${e.roomservice.servicestate!=4 }">
  <c:choose>
  <c:when test="${e.wstate==1 }">
  <input type="button" value="Ȯ��" id="btn113">
  <input type="button" value="����" id="btn2">
  </c:when>
  <c:otherwise>
  <input type="button" value="���" id="btn3">
  <input type="button" value="�Ϸ�" id="btn4">  
  </c:otherwise>
  </c:choose>
 
  </c:when>

  <c:otherwise>
  <input type="button" value="�Ϸ��" disabled="disabled">
  </c:otherwise>
  </c:choose>

</div>
</div>

</c:forEach>
</c:if>
</form>
</div>
</div>
</div>
</article>

<script>
let c = "";
$(function() {
	let a= $('tr').eq(5).children('td').text();
	console.log(a);
	$('div span').each(function(i, element) {
		let b =$(this).text();
		
		if(b==1){
			c="����";
			$(this).attr("style", "color:blue");
		}else if(b==3){
			c="�����";
			$(this).attr("style", "color:yellow");
		}else if(b==4){
			c="�Ϸ�";
			$(this).attr("style", "color:red");
		}else{
		
		}
		$(this).text(c);
//		console.log(b);
//		console.log(c);
	})
	
	$('#btn113').click(function() {
		$('#servicenum').val($(this).parent('div').parent('div').children('label').text().trim());
		$('#form3').attr("action", "confirm");
		$('#form3').submit();
	})
	
	$('#btn2').click(function() {
		$('#servicenum').val($(this).parent('div').parent('div').children('label').text().trim());
		$('#form3').attr("action", "deny");
		$('#form3').submit();
	})
	
	$('#btn3').click(function() {
		$('#servicenum').val($(this).parent('div').parent('div').children('label').text().trim());
		$('#form3').attr("action", "cancel");
		$('#form3').submit();
	})
	
	$('#btn4').click(function() {
		$('#servicenum').val($(this).parent('div').parent('div').children('label').text().trim());
		$('#form3').attr("action", "complete");
		$('#form3').submit();
	})
});

function confirm1(a) {
	$('#servicenum').val();
	console.log($(this).parent('div').parent('div').children('label').text().trim());
	
/*	if(a==1){
		$('#form3').attr("action", "confirm");
		$('#form3').submit();
	}else if(a==2){
		$('#form3').attr("action", "deny");
		$('#form3').submit();
	}else if(a==3){
		$('#form3').attr("action", "cancel");
		$('#form3').submit();
	}else if(a==4){
		$('#form3').attr("action", "complete");
		$('#form3').submit();
	}
	*/
}

</script>