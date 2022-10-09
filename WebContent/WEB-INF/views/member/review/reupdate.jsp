<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
			<fieldset>
				<legend>후기 수정</legend>
				
				<form method="post" action="upde">
					<p>
						<label>제 목</label> <input type="text" name="retitle" id="retitle" value="${rvo.retitle}"/>
					</p>
					
					<p>
						<label>내용</label>
						<textarea name="recontent" id="recontent" rows="5" cols="13">${rvo.recontent}</textarea>
					</p>
			
			<p>
			
		<input type="hidden" id="rewriter" name="rewriter"/>	
		<input type="hidden" value="${rvo.renum }" name="renum"/> 
		<input type="hidden" id="rvnum" name="rvnum"/>
		<input type="hidden" id="redate" name="redate"/>

					</p>		
	
					<p>
						<input type="submit" value="수정" /> 
						<input type="reset"	value="다시 작성하기">

					</p>
				</form>
			</fieldset>
</body>
</html>