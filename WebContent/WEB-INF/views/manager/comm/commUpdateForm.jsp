<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<article class="container">
        <header>
            <h1>ReviewComm ���� Demo</h1>
        </header>
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
        <div class="container">
<%-- View�ܿ��� javascript : form �±� ���� ��ȿ��  
onsubmit : submit() �̺�Ʈ�� �߻� �� �� checkValue()�� ȣ�� �Ѵ�.
login.LoginCheckController -> /loginForm 
--%>
 <form action="commUpdate" method="post">
 <input type="hidden" name="renum" value="${vo.renum }">
 <input type="hidden" name="anum" value="${sessionScope.sessionAnum}">
    <div class="form-group">
      <label for="rccontent">����</label>
      <textarea class="form-control" rows="5" id="rccontent" name="rccontent" 
      placeholder="${vo.rccontent }" required="required"></textarea>
    </div>
     <div class="form-group" style="text-align: right; margin-top: 10px;">
      <button type="submit" class="btn btn-primary">����</button>&nbsp;
    </div>
  </form>

</div>
</article>