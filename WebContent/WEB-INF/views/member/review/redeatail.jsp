<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  <script>
	$(document).ready(function() {
		$('#delbtn').click(function() {

			if (confirm("����� �ı⸦ ���� �Ͻðڽ��ϱ�?"))
				document.fo1.action = "${pageContext.request.contextPath}/review/del?renum='${review.renum }'";
			document.fo1.submit();
		});
	});
</script>-->

	<article class="container">
		<header>
			<h1>�ı� �Խ���</h1>
		</header>
		<ul class="list-unstyled">
			<li class="border-top my-3"></li>
		</ul>
		
		<h2>�Խñ� ����</h2>

	<form name="fo1" method="post" action="upde">

<div>
		���� ��ȣ : 	<label>${review.rvnum}</label><br>
		�ı� ��ȣ :		<label>${review.renum }</label>
				
</div>

		<div>�ۼ����� : ${review.redate}</div>
		<div>
			�̸� <input name="rewriter" id="rewriter" value="${review.rewriter}"
				placeholder="" readonly="readonly">
		</div>
		
		<div>
		<input type="hidden" name="renum" value="${review.renum}">
			���� <input name="retitle" id="retitle" size="77"
				value="${review.retitle}" placeholder="">
		</div>
		<div>
			����
			<textarea name="recontent" id="recontent" rows="6" cols="99"
				placeholder="">${review.recontent}</textarea>
		</div>
	
		<div style="width: 650px; text-align: center;"></div>

		<input type="submit" id="upbtn" value="����">
		<button type="button" id="delbtn" onclick="location.href='${pageContext.request.contextPath}/member/review/del?renum=${review.renum}'">����</button>
		<input type="reset" value="�ٽ� �ۼ��ϱ�">
		<button type="button" id="delbtn" onclick="location.href='${pageContext.request.contextPath}/member/comm/comm?renum=${review.renum}'">�亯��������</button>
	</form>
		
		
	</article>