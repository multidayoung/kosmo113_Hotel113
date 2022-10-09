<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<footer>
	<div class="container">
		<p class="float-end"><a href="#">TOP</a></p>
		<p>&copy; 2022. KOSMO113 세미2차 C팀 ALL RIGHTS RESERVED.</p>
	</div>
</footer>

<script>
	//이미지 미리보기 기능
    function readURL(input) {
    	if (input.files && input.files[0]) {
    		var reader = new FileReader();
    		reader.onload = function(e) {
    			$('#imgx').attr('src', e.target.result); 
    		}
    		reader.readAsDataURL(input.files[0]);
    	}
    }

   	$(function() {
		$('#mfile').change(function() {
			readURL(this);
		});
	});
</script>