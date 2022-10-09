<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<article class="container">
	<header>
		<h1>������LoginProcess Demo</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
		<%-- View�ܿ��� javascript : form �±� ���� ��ȿ��  
		onsubmit : submit() �̺�Ʈ�� �߻� �� �� checkValue()�� ȣ�� �Ѵ�.
		login.LoginCheckController -> /loginForm 
		--%>
		<form action="loginProcess" method="post" id="loginInfo"
			onsubmit="return checkValue()">
			<input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">
			<div class="form-group">
				<label for="aid">ID</label> <input type="text" class="form-control"
					id="aid" placeholder="���̵� �Է�" name="aid" maxlength="10"
					required="required" pattern=".{2,10}">
			</div>
			<div class="form-group">
				<label for="apwd">PWD</label> <input type="password"
					class="form-control" id="apwd" placeholder="******" name="apwd"
					maxlength="10" required="required" pattern=".{2,10}">
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary">���</button>
			</div>
		</form>

	</div>

</article>