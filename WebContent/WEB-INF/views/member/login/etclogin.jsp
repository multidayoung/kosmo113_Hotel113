<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<style>
    a {color:#fff; text-decoration:none; outline:none;}
   a:hover, a:active {text-decoration:none; color:#fff;}
   .loginselect {max-width:850px;}
   .loginselect:after {display:block; content:''; clear:both;}
   .loginselect div {float:left; width:50%; text-align:center;}
   .loginselect div a {display:block; width:100%; height:100%; font-size:25px; font-weight:bold; padding:45px 0 25px;}
   .loginselect div.memberlogin {background:#0d6efd; border-top:5px solid #0d6efd; border-bottom:5px solid #0d6efd;}
   .loginselect div.adminlogin {border-top:5px solid #0d6efd; border-bottom:5px solid #0d6efd; border-right:5px solid #0d6efd;}
   .loginselect div.adminlogin a {color:#000;}
   .loginselect div a img {width:80px;}
   .loginselect div a p {margin-top:10px;}
</style>

<article class="container loginselect">
   <div class="memberlogin">
      <a href="${mycontext}/member/loginform">
         <img src="${mycontext}/resources/iconimg/login_white.png">
         <p>회원 로그인</p>
      </a>
   </div>
   <div class="adminlogin">
      <a href="${mycontext}/manager/loginform">
         <img src="${mycontext}/resources/iconimg/login_black.png">
         <p>관리자 로그인</p>
      </a>
   </div>
</article>