<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <article class="container">
        <header>
            <h1>Comm Detail</h1>
        </header>
        <ul class="list-unstyled"><li class="border-top my-3"></li></ul>
        <div>
	        <%--board Form �� �ڸ�--%>
		    <div class="form-group">
	     		<label for="renum">�ı�Խñ۹�ȣ</label>
				<!-- placeholder �Ӽ� �Է��� �����Ͱ� ���� ��� ������� ��Ÿ����. ���������� �Է��� 100�ڱ����� ���� -->
				<!-- required �Ӽ��� �����ϸ� �ʼ��Է� �����̵ȴ�. -->
				<!-- pattern �Ӽ��� �̿��� ����ǥ�������� �������� ��ȿ�� �˻縦 �� �� �ִ�. -->
	      		<input type="text" class="form-control" id="renum" value="${vo.renum }" readonly="readonly">
	    	</div>
	    	<div class="form-group">
	   			<label for="rccontent">����</label>
				<!--  �������� �����͸� �Է��ϰ� �ϰ��� �Ҷ� textarea �±׸� ����Ѵ�. -->
				<!--  textarea �ȿ� �ִ� ��� ���ڴ� �״�� ��Ÿ����. ���鹮��, tag, enter -->
			   <textarea class="form-control" rows="5" id="rccontent" name="rccontent" readonly="readonly">${vo.rccontent }</textarea>
			</div>
		    <div class="form-group">
		      <label for="anum">�ۼ������ڹ�ȣ</label>
		      <input type="text" class="form-control" id="anum" name="anum" value="${vo.anum }" readonly="readonly">
		    </div>
		    <div class="form-group">
		      <label for="rcdate">�ۼ���¥</label>
		      <input type="text" class="form-control" id="rcdate" name="rcdate" value="${vo.rcdate }" readonly="readonly">
		    </div>
		    <div class="form-group" style="text-align: right; margin-top: 10px;">
		    	<input type="button" value="���" onclick="location='${pageContext.request.contextPath}/manager/commList'">&nbsp;
		    	<input type="button" value="����" onclick="location='${pageContext.request.contextPath}/manager/commUpForm?renum=${vo.renum }'">&nbsp;
      			<input type="button" value="����" onclick="location='${pageContext.request.contextPath}/manager/commDel?renum=${vo.renum }'">&nbsp;
		  	</div>
  	 	</div>
 
    </article>