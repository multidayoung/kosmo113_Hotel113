<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(document).ready(function() {
		$('#delbtn').click(function() {

			if (confirm("당신의 후기를 삭제 하시겠습니까?"))
				document.fo1.action = "${path}/review/redelete";
			document.fo1.submit();
		});
	});
</script>

	<article class="container">
		<header>
			<h1>후기글에 대한 답글</h1>
		</header>
		<ul class="list-unstyled">
			<li class="border-top my-3"></li>
		</ul>
		
		<h2>게시글 보기</h2>

		<div>
				예약 번호 : 	<label>${vo.rvnum}</label><br>
				후기 번호 :		<label>${vo.renum }</label>
						
		</div>

		<div>작성일자 : ${vo.redate}</div>

		<div>
		<input type="hidden" name="renum" value="${vo.renum }">
			제목 <input name="retitle" id="retitle" size="77"
				value="${vo.retitle}" readonly="readonly">
		</div>
		<div>
			내용
			<textarea name="recontent" id="recontent" rows="6" cols="99"
			readonly="readonly">${vo.recontent}</textarea>
		</div>
		<div>
			이름 <input name="rewriter" id="rewriter" value="${vo.rewriter}"
				readonly="readonly">
		</div>

		<div style="width: 650px; text-align: center;"></div>
		
		
		
		
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
        
        <h3>답글</h3>
        
        <div class="container">
        
		 <input type="hidden" name="renum" value="${vo.renum }">
		 <div class="form-group">
			 관리자 : <input type="text" name="aname" value="${vo.comm.admin.aname }" readonly="readonly">
		 </div>
	    <div class="form-group">
	      <label for="rccontent">내용</label>
	      <textarea class="form-control" rows="5" id="rccontent" name="rccontent" 
	      readonly="readonly">${vo.comm.rccontent }</textarea>
	    </div>
	    <div class="form-group">
	     	작성날짜 : <input type="text" name="rcdate" value="${vo.comm.rcdate }" readonly="readonly">
	    </div>
	    <div class="form-group" style="text-align: right; margin-top: 10px;">
	      
	      <button type="button" onclick="location='${pageContext.request.contextPath}/manager/comm/rcommList'" class="btn btn-primary">목록</button>&nbsp;
	      <c:choose>
		    <c:when test="${vo.comm.rccontent != '아직 답변이 작성되지 않았습니다.'}">
		      <button type="button" onclick="location='${pageContext.request.contextPath}/manager/comm/commUpForm?renum=${vo.renum }'" class="btn btn-primary">수정</button>&nbsp;
		      <button type="button" onclick="location='${pageContext.request.contextPath}/manager/comm/commDel?renum=${vo.renum }'" class="btn btn-primary">삭제</button>&nbsp;
		  	</c:when>
		  	<c:otherwise>
		      <button type="button" onclick="location='${pageContext.request.contextPath}/manager/comm/commForm1?renum=${vo.renum }'" class="btn btn-primary">작성</button>&nbsp;
		    </c:otherwise>
		  </c:choose>
	      
	    </div>


</div>
</article>