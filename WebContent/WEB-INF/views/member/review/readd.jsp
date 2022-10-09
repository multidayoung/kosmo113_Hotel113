<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


    <article class="container">
        <header>
            <h1>�ı� ���</h1>
        </header>
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
	<div>
		<h2>�ı� ���</h2>
		<form action="write" method="post">
			<input type="hidden" name="rvnum" id="rvnum" value="${rvnum }">

			<div class="form-group">
				<label for="retitle">����</label> <input type="text"
					class="form-control" id="retitle" placeholder="���� �Է�"
					name="retitle" maxlength="100">
			</div>

			<div class="form-group">
				<label for="recontent">����</label>
				<textarea class="form-control" rows="5" id="recontent"
					name="recontent" placeholder="���� �ۼ�"></textarea>
			</div>
			
			<div class="form-group">
				<label for="rewriter">�ۼ���</label>
				<input type="text" class="form-control" id="rewriter" 
				name="rewriter" value="${sessionScope.sessionname }" readonly="readonly">
			</div>
			<div class="form-group">

				<button type="submit" class="btn btn-default">���</button>
			</div>
		</form>
	</div>
</article>
