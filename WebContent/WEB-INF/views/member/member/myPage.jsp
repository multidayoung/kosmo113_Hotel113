<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<article class="container">
        <header>
            <h1>memberDetail Form</h1>
        </header>
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
        <div>
    <div class="form-group">
      <label for="mnum">회원번호</label>
    <input type="text" class="form-control" id="mnum" name="mnum" value="${vo.mnum}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="mid">아이디</label>
      <input type="text" class="form-control" id="mid" name="mid" value="${vo.mid}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="mname">이름</label>
      <input type="text" class="form-control" id="mname" name="mname" value="${vo.mname}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="mphone">전화번호</label>
      <input type="text" class="form-control" id="mphone" name="mphone" value="${vo.mphone}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="mbirth">생년월일</label>
      <input type="text" class="form-control" id="mbirth" name="mbirth" value="${vo.mbirth}" readonly="readonly">
    </div> 
 <div class="form-group">
      <label for="mdate">가입날짜</label>
      <input type="text" class="form-control" id="mdate" name="mdate" value="${vo.mdate}" readonly="readonly">
    </div>

   <button class="btn btn-primary" type="button" onclick="location='${pageContext.request.contextPath}/member/member/memberUpForm?mnum=${vo.mnum}'">수정</button>
   <button class="btn btn-danger" type="button" onclick="location='${pageContext.request.contextPath}/member/member/memberDelete?num=${vo.mnum}'">삭제</button>
    </div>
</article>