<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


    <article class="container">
        <header>
            <h1>후기 등록</h1>
        </header>
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
	<div>
		<h2>후기 등록</h2>
		<form action="write" method="post">
			<input type="hidden" name="rvnum" id="rvnum" value="${rvnum }">

			<div class="form-group">
				<label for="retitle">제목</label> <input type="text"
					class="form-control" id="retitle" placeholder="제목 입력"
					name="retitle" maxlength="100">
			</div>

			<div class="form-group">
				<label for="recontent">내용</label>
				<textarea class="form-control" rows="5" id="recontent"
					name="recontent" placeholder="내용 작성"></textarea>
			</div>
			
			<div class="form-group">
				<label for="rewriter">작성자</label>
				<input type="text" class="form-control" id="rewriter" 
				name="rewriter" value="${sessionScope.sessionname }" readonly="readonly">
			</div>
			<div class="form-group">

				<button type="submit" class="btn btn-default">등록</button>
			</div>
		</form>
	</div>
</article>
