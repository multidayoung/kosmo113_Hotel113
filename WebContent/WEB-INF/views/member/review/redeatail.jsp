<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  <script>
	$(document).ready(function() {
		$('#delbtn').click(function() {

			if (confirm("당신의 후기를 삭제 하시겠습니까?"))
				document.fo1.action = "${pageContext.request.contextPath}/review/del?renum='${review.renum }'";
			document.fo1.submit();
		});
	});
</script>-->

	<article class="container">
		<header>
			<h1>후기 게시판</h1>
		</header>
		<ul class="list-unstyled">
			<li class="border-top my-3"></li>
		</ul>
		
		<h2>게시글 보기</h2>

	<form name="fo1" method="post" action="upde">

<div>
		예약 번호 : 	<label>${review.rvnum}</label><br>
		후기 번호 :		<label>${review.renum }</label>
				
</div>

		<div>작성일자 : ${review.redate}</div>
		<div>
			이름 <input name="rewriter" id="rewriter" value="${review.rewriter}"
				placeholder="" readonly="readonly">
		</div>
		
		<div>
		<input type="hidden" name="renum" value="${review.renum}">
			제목 <input name="retitle" id="retitle" size="77"
				value="${review.retitle}" placeholder="">
		</div>
		<div>
			내용
			<textarea name="recontent" id="recontent" rows="6" cols="99"
				placeholder="">${review.recontent}</textarea>
		</div>
	
		<div style="width: 650px; text-align: center;"></div>

		<input type="submit" id="upbtn" value="수정">
		<button type="button" id="delbtn" onclick="location.href='${pageContext.request.contextPath}/member/review/del?renum=${review.renum}'">삭제</button>
		<input type="reset" value="다시 작성하기">
		<button type="button" id="delbtn" onclick="location.href='${pageContext.request.contextPath}/member/comm/comm?renum=${review.renum}'">답변보러가기</button>
	</form>
		
		
	</article>