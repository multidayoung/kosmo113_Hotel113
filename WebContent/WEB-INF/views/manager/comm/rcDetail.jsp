<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(document).ready(function() {
		$('#delbtn').click(function() {

			if (confirm("����� �ı⸦ ���� �Ͻðڽ��ϱ�?"))
				document.fo1.action = "${path}/review/redelete";
			document.fo1.submit();
		});
	});
</script>

	<article class="container">
		<header>
			<h1>�ı�ۿ� ���� ���</h1>
		</header>
		<ul class="list-unstyled">
			<li class="border-top my-3"></li>
		</ul>
		
		<h2>�Խñ� ����</h2>

		<div>
				���� ��ȣ : 	<label>${vo.rvnum}</label><br>
				�ı� ��ȣ :		<label>${vo.renum }</label>
						
		</div>

		<div>�ۼ����� : ${vo.redate}</div>

		<div>
		<input type="hidden" name="renum" value="${vo.renum }">
			���� <input name="retitle" id="retitle" size="77"
				value="${vo.retitle}" readonly="readonly">
		</div>
		<div>
			����
			<textarea name="recontent" id="recontent" rows="6" cols="99"
			readonly="readonly">${vo.recontent}</textarea>
		</div>
		<div>
			�̸� <input name="rewriter" id="rewriter" value="${vo.rewriter}"
				readonly="readonly">
		</div>

		<div style="width: 650px; text-align: center;"></div>
		
		
		
		
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
        
        <h3>���</h3>
        
        <div class="container">
        
		 <input type="hidden" name="renum" value="${vo.renum }">
		 <div class="form-group">
			 ������ : <input type="text" name="aname" value="${vo.comm.admin.aname }" readonly="readonly">
		 </div>
	    <div class="form-group">
	      <label for="rccontent">����</label>
	      <textarea class="form-control" rows="5" id="rccontent" name="rccontent" 
	      readonly="readonly">${vo.comm.rccontent }</textarea>
	    </div>
	    <div class="form-group">
	     	�ۼ���¥ : <input type="text" name="rcdate" value="${vo.comm.rcdate }" readonly="readonly">
	    </div>
	    <div class="form-group" style="text-align: right; margin-top: 10px;">
	      
	      <button type="button" onclick="location='${pageContext.request.contextPath}/manager/comm/rcommList'" class="btn btn-primary">���</button>&nbsp;
	      <c:choose>
		    <c:when test="${vo.comm.rccontent != '���� �亯�� �ۼ����� �ʾҽ��ϴ�.'}">
		      <button type="button" onclick="location='${pageContext.request.contextPath}/manager/comm/commUpForm?renum=${vo.renum }'" class="btn btn-primary">����</button>&nbsp;
		      <button type="button" onclick="location='${pageContext.request.contextPath}/manager/comm/commDel?renum=${vo.renum }'" class="btn btn-primary">����</button>&nbsp;
		  	</c:when>
		  	<c:otherwise>
		      <button type="button" onclick="location='${pageContext.request.contextPath}/manager/comm/commForm1?renum=${vo.renum }'" class="btn btn-primary">�ۼ�</button>&nbsp;
		    </c:otherwise>
		  </c:choose>
	      
	    </div>


</div>
</article>