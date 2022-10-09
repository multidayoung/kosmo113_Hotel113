<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
      	<img src="${mycontext}/resources/carouselimg/carousel1.jpg" alt="비주얼1" class="d-block" style="width:100%">
        <div class="container">
          <div class="carousel-caption text-start" style="bottom:5rem;">
            <h1>WELLCOM HOTEL113</h1>
            <p>방문을 환영합니다. 즐겁고 편안한 휴식을 즐기다 가시길 바랍니다.</p>
          </div>
        </div>
      </div>
      <div class="carousel-item">
      	<img src="${mycontext}/resources/carouselimg/carousel2.jpg" alt="비주얼2" class="d-block" style="width:100%">
        <div class="container">
          <div class="carousel-caption" style="bottom:5rem;">
            <h1>WELLCOM HOTEL113</h1>
            <p>방문을 환영합니다. 즐겁고 편안한 휴식을 즐기다 가시길 바랍니다.</p>
          </div>
        </div>
      </div>
      <div class="carousel-item">
      	<img src="${mycontext}/resources/carouselimg/carousel3.jpg" alt="비주얼3" class="d-block" style="width:100%">
        <div class="container">
          <div class="carousel-caption text-end" style="bottom:5rem;">
            <h1>WELLCOM HOTEL113</h1>
            <p>방문을 환영합니다. 즐겁고 편안한 휴식을 즐기다 가시길 바랍니다.</p>
          </div>
        </div>
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>