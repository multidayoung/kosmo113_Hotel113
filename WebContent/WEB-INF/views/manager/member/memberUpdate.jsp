<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

<article class="container">
        <header>
            <h1>ȸ�� ����</h1>
        </header>
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
        <div class="container">
 <form action="memberUpdate" method="post">
	<input type="hidden" value="${vo.mnum }" name="mnum">
    <div class="form-group">
      <label for="mid">���̵� </label>
      <input type="text" class="form-control" id="mid"
       placeholder="���̵� �Է�(3-10)" name="mid"
       maxlength="10" required="required"
       pattern=".{3,10}" value="${vo.mid }">
    </div>
    <div class="form-group">
      <label for="mpwd">��й�ȣ</label>
      <input type="password" class="form-control" id="mpwd"
        name="mpwd" required="required" value="${vo.mpwd }">
    </div> 
    <div class="form-group">
      <label for="mname">�̸�</label>
      <input type="text" class="form-control" id="mname"
       placeholder="�̸��� �Է��ϼ���." name="mname" required="required" value="${vo.mname }">
    </div>
    <div class="form-group">
      <label for="mphone">����ȣ</label>
      <input type="text" class="form-control" id="mphone"
       placeholder="-�� ����ؼ� ����ȣ�� �Է��ϼ���." name="mphone" required="required"  value="${vo.mphone }">
    </div>
   <div class="form-group">
      <label for="mbirth">������� </label>
      <input type="text" class="form-control" id="mbirth"
       placeholder="������� 8�ڸ��� �Է��ϼ���." name="mbirth"
       maxlength="8" required="required" value="${vo.mbirth }">
    </div>
     <div class="form-group" style="text-align: right; margin-top: 10px;">
      <button type="submit" class="btn btn-primary">����</button>
      <button class="btn btn-warning text-white" type="button"
    onclick="location='${pageContext.request.contextPath}/manager/member/memberDetail?num=${vo.mnum}'">���</button>
    </div>
  </form>
   </div>
</article>