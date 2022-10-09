<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<article class="container">
<div>
        <header>
            <h1>ȸ������ Demo</h1>
        </header>
  
        <div class="container">
 <%--memberForm ��� �� �ڸ�. --%>
  <form action="memberIn" method="post">
 <div>
    <div class="form-group">
      <label for="mid">���̵� </label>
      <input type="text" class="form-control" id="mid"
       placeholder="���̵� �Է�(3-10)" name="mid"
       maxlength="10" required="required"
       pattern=".{3,10}">
       <button type="button" class="btn btn-primary" id="chkbtn2">�ߺ�Ȯ��</button>
    </div>
    <div id="target"></div>
    <div class="form-group">
      <label for="mpwd">��й�ȣ</label>
      <input type="password" class="form-control" id="mpwd"
        name="mpwd" required="required">
    </div> 
    <div class="form-group">
      <label for="mname">�̸�</label>
      <input type="text" class="form-control" id="mname"
       placeholder="�̸��� �Է��ϼ���." name="mname" required="required">
    </div>
    <div class="form-group">
      <label for="mphone">����ȣ</label>
      <input type="text" class="form-control" id="mphone"
       placeholder="-�� ����ؼ� ����ȣ�� �Է��ϼ���." name="mphone" required="required">
    </div>
	<div class="form-group">
      <label for="mbirth">������� </label>
      <input type="text" class="form-control" id="mbirth"
       placeholder="������� 8�ڸ��� �Է��ϼ���. ����)19950101" name="mbirth"
       maxlength="8" required="required">
    </div>
     <div class="form-group" style="text-align: right; margin-top: 10px;">
      <button type="submit" class="btn btn-primary">���</button>
    </div>
   </div>
  </form> 

	</div>
</div>
</article>
<script>
$(function() {
	$('#chkbtn2').click(function() {
		let cnt = $('#mid').val();
		console.log(cnt);
		$.ajax({
			url:"idChk?mid="+cnt,
			success:function(data){
				console.log(data);
				if(data == 1){
					$('#target').css('background-color','gray').css('color','white').html('������� ���̵� �Դϴ�.');
				}else{
					$('#target').css('background-color','yellow').css('color','black').html('��� ������ ���̵� �Դϴ�.');
				}
				
			}
		});
	});
});
</script>
