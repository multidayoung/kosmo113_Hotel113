<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

<article class="container">
        <header>
            <h1>회원 수정</h1>
        </header>
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
        <div class="container">
 <form action="memberUpdate" method="post">
	<input type="hidden" value="${vo.mnum }" name="mnum">
    <div class="form-group">
      <label for="mid">아이디 </label>
      <input type="text" class="form-control" id="mid"
       placeholder="아이디 입력(3-10)" name="mid"
       maxlength="10" required="required"
       pattern=".{3,10}" value="${vo.mid }">
    </div>
    <div class="form-group">
      <label for="mpwd">비밀번호</label>
      <input type="password" class="form-control" id="mpwd"
        name="mpwd" required="required" value="${vo.mpwd }">
    </div> 
    <div class="form-group">
      <label for="mname">이름</label>
      <input type="text" class="form-control" id="mname"
       placeholder="이름을 입력하세요." name="mname" required="required" value="${vo.mname }">
    </div>
    <div class="form-group">
      <label for="mphone">폰번호</label>
      <input type="text" class="form-control" id="mphone"
       placeholder="-을 사용해서 폰번호을 입력하세요." name="mphone" required="required"  value="${vo.mphone }">
    </div>
   <div class="form-group">
      <label for="mbirth">생년월일 </label>
      <input type="text" class="form-control" id="mbirth"
       placeholder="생년월일 8자리를 입력하세요." name="mbirth"
       maxlength="8" required="required" value="${vo.mbirth }">
    </div>
     <div class="form-group" style="text-align: right; margin-top: 10px;">
      <button type="submit" class="btn btn-primary">수정</button>
      <button class="btn btn-warning text-white" type="button"
    onclick="location='${pageContext.request.contextPath}/manager/member/memberDetail?num=${vo.mnum}'">취소</button>
    </div>
  </form>
   </div>
</article>