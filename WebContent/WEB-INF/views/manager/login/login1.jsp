<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<article class="container">
	<header>
		<h1>관리자LoginProcess Demo</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
		<%-- View단에서 javascript : form 태그 관련 유효성  
		onsubmit : submit() 이벤트가 발생 할 때 checkValue()를 호출 한다.
		login.LoginCheckController -> /loginForm 
		--%>
		<form action="loginProcess" method="post" id="loginInfo"
			onsubmit="return checkValue()">
			<input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">
			<div class="form-group">
				<label for="aid">ID</label> <input type="text" class="form-control"
					id="aid" placeholder="아이디 입력" name="aid" maxlength="10"
					required="required" pattern=".{2,10}">
			</div>
			<div class="form-group">
				<label for="apwd">PWD</label> <input type="password"
					class="form-control" id="apwd" placeholder="******" name="apwd"
					maxlength="10" required="required" pattern=".{2,10}">
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary">등록</button>
			</div>
		</form>

	</div>

</article>