<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>
<script>
	
</script>
<article>
        <header>
            <h1>예약 리스트</h1>
        </header>
        <div>
        	<input type="button" value="돌아가기" id="backbtn" onclick="history.go(-1)">
        </div>
        <div>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>예약번호</th>        
        <th>객실번호</th> 
        <th>예약자 아이디</th>               
        <th>사용 시작일</th>
        <th>사용 종료일</th>
        <th>총 가격</th>
        <th>예약 상태</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="e" items="${rlist }">          
      <tr>      
        <td>${e.rvnum }</td>
        <td>${e.rnum }</td>
        <td>${e.mid }</td>
        <td>${e.rvstart }</td>
        <td>${e.rvend }</td>
        <td>${e.rvprice }</td>
        <%-- 
        	rvstate는 0~3으로 구분
        	0 : 예약한 상태
        	1 : 예약을 취소한 상태
        	2 : 예약을 정상 이용하고 종료한 상태
        	3 : 예약을 이용중인 상태
         --%>
        <c:choose>        	
        	<c:when test="${e.rvstate eq 1 }">
        		<td style="background-color: red; color: white;">예약 취소됨</td>
        	</c:when>
        	<c:when test="${e.rvstate eq 2 }">
        		<td style="background-color: yellow;">정상 이용 후 종료됨</td>
        	</c:when>
        	<c:when test="${e.rvstate eq 3 }">
        		<td style="background: blue; color: white;">객실 이용 중</td>
        	</c:when>
        	<c:otherwise>
      			<td style="background-color: green; color: white;">예약됨</td>  	
        	</c:otherwise>
        </c:choose>        
       </tr>      
      </c:forEach>
    </tbody>
    <tfoot>
	<tr>
	<td colspan="7">
	<ol class="paging">
	<c:choose>
		<c:when test="${startPage < 6}">
			<li class="disable">이전으로</li>
		</c:when>
		<c:otherwise>
			<li><a href="${mycontext}/reserve/reservelist?cPage=${startPage-pagePerBlock}">이전으로</a></li>
		</c:otherwise>
	</c:choose>
	<c:forEach varStatus="i" begin="${startPage}" end="${endPage}" step="1">
		<c:choose>
			<c:when test="${i.index == nowPage}">
				<li class="now">${i.index}</li>
			</c:when>
			<c:otherwise>
				<li><a href="${mycontext}/reserve/reservelist?cPage=${i.index}">${i.index}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${endPage >= totalPage}">
			<li class="disable">다음으로</li>
		</c:when>
		<c:otherwise>
			<li><a href="${mycontext}/reserve/reservelist?cPage=${startPage+pagePerBlock}">다음으로</a></li>
		</c:otherwise>		
	</c:choose>
	</ol>
	</td>
	</tr>
	</tfoot>  
  </table>
        </div>
 
    </article>