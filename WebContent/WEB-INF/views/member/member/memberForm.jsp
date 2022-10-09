<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<article class="container">
<div>
        <header>
            <h1>회원가입 Demo</h1>
        </header>
  
        <div class="container">
 <%--memberForm 들어 갈 자리. --%>
  <form action="memberIn" method="post">
 <div>
    <div class="form-group">
      <label for="mid">아이디 </label>
      <input type="text" class="form-control" id="mid"
       placeholder="아이디 입력(3-10)" name="mid"
       maxlength="10" required="required"
       pattern=".{3,10}">
       <button type="button" class="btn btn-primary" id="chkbtn2">중복확인</button>
    </div>
    <div id="target"></div>
    <div class="form-group">
      <label for="mpwd">비밀번호</label>
      <input type="password" class="form-control" id="mpwd"
        name="mpwd" required="required">
    </div> 
    <div class="form-group">
      <label for="mname">이름</label>
      <input type="text" class="form-control" id="mname"
       placeholder="이름을 입력하세요." name="mname" required="required">
    </div>
    <div class="form-group">
      <label for="mphone">폰번호</label>
      <input type="text" class="form-control" id="mphone"
       placeholder="-을 사용해서 폰번호을 입력하세요." name="mphone" required="required">
    </div>
	<div class="form-group">
      <label for="mbirth">생년월일 </label>
      <input type="text" class="form-control" id="mbirth"
       placeholder="생년월일 8자리를 입력하세요. 예시)19950101" name="mbirth"
       maxlength="8" required="required">
    </div>
     <div class="form-group" style="text-align: right; margin-top: 10px;">
      <button type="submit" class="btn btn-primary">등록</button>
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
					$('#target').css('background-color','gray').css('color','white').html('사용중인 아이디 입니다.');
				}else{
					$('#target').css('background-color','yellow').css('color','black').html('사용 가능한 아이디 입니다.');
				}
				
			}
		});
	});
});
</script>
