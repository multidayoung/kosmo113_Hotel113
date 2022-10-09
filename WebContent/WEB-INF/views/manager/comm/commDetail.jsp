<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <article class="container">
        <header>
            <h1>Comm Detail</h1>
        </header>
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
        <div>
	        <%--board Form 들어갈 자리--%>
		    <div class="form-group">
	     		<label for="renum">후기게시글번호</label>
				<!-- placeholder 속성 입력한 데이터가 없는 경우 배경으로 나타난다. 실제적으로 입력을 100자까지로 지정 -->
				<!-- required 속성을 설정하면 필수입력 사항이된다. -->
				<!-- pattern 속성을 이용한 정규표현식으로 데이터의 유효성 검사를 할 수 있다. -->
	      		<input type="text" class="form-control" id="renum" value="${vo.renum }" readonly="readonly">
	    	</div>
	    	<div class="form-group">
	   			<label for="rccontent">내용</label>
				<!--  여러줄의 데이터를 입력하고 하고자 할때 textarea 태그를 사용한다. -->
				<!--  textarea 안에 있는 모든 글자는 그대로 나타난다. 공백문자, tag, enter -->
			   <textarea class="form-control" rows="5" id="rccontent" name="rccontent" readonly="readonly">${vo.rccontent }</textarea>
			</div>
		    <div class="form-group">
		      <label for="anum">작성관리자번호</label>
		      <input type="text" class="form-control" id="anum" name="anum" value="${vo.anum }" readonly="readonly">
		    </div>
		    <div class="form-group">
		      <label for="rcdate">작성날짜</label>
		      <input type="text" class="form-control" id="rcdate" name="rcdate" value="${vo.rcdate }" readonly="readonly">
		    </div>
		    <div class="form-group" style="text-align: right; margin-top: 10px;">
		    	<input type="button" value="목록" onclick="location='${pageContext.request.contextPath}/manager/commList'">&nbsp;
		    	<input type="button" value="수정" onclick="location='${pageContext.request.contextPath}/manager/commUpForm?renum=${vo.renum }'">&nbsp;
      			<input type="button" value="삭제" onclick="location='${pageContext.request.contextPath}/manager/commDel?renum=${vo.renum }'">&nbsp;
		  	</div>
  	 	</div>
 
    </article>