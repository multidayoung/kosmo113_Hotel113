<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<article class="container">
        <header>
            <h1>ReviewComm 수정 Demo</h1>
        </header>
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
        <div class="container">
<%-- View단에서 javascript : form 태그 관련 유효성  
onsubmit : submit() 이벤트가 발생 할 때 checkValue()를 호출 한다.
login.LoginCheckController -> /loginForm 
--%>
 <form action="commUpdate" method="post">
 <input type="hidden" name="renum" value="${vo.renum }">
 <input type="hidden" name="anum" value="${sessionScope.sessionAnum}">
    <div class="form-group">
      <label for="rccontent">내용</label>
      <textarea class="form-control" rows="5" id="rccontent" name="rccontent" 
      placeholder="${vo.rccontent }" required="required"></textarea>
    </div>
     <div class="form-group" style="text-align: right; margin-top: 10px;">
      <button type="submit" class="btn btn-primary">수정</button>&nbsp;
    </div>
  </form>

</div>
</article>