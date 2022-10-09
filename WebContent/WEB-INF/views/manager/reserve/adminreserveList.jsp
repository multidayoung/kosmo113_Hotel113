<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="mycontext" value="${pageContext.request.contextPath}"/>

<style>
	/* paging */
	.paging {list-style:none; text-align:center; padding:0; margin:10px 0;}
	.paging li {display:inline-block;}
	.paging li.disable {padding:3px 10px; border:1px solid silver; color:silver;}
	.paging li.now {padding:3px 10px; border:1px solid #000; background:#000; color:white; font-weight:bold;}
	.paging li a {display:block; padding:3px 10px; border:1px solid #000; color:#000; font-weight:bold;}
	.paging li a:hover {background:#000; color:#fff; font-weight:bold;}
</style>

<script>
	
</script>
<article class="container">
        <header>
            <h1>���� ����Ʈ</h1>
        </header>
        <div>
        	<input type="button" value="���ư���" id="backbtn" onclick="history.go(-1)">
        </div>
        <div>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>�����ȣ</th>        
        <th>���ǹ�ȣ</th> 
        <th>������ ���̵�</th>               
        <th>��� ������</th>
        <th>��� ������</th>
        <th>�� ����</th>
        <th>���� ����</th>
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
        	rvstate�� 0~3���� ����
        	0 : ������ ����
        	1 : ������ ����� ����
        	2 : ������ ���� �̿��ϰ� ������ ����
        	3 : ������ �̿����� ����
         --%>
        <c:choose>        	
        	<c:when test="${e.rvstate eq 1 }">
        		<td style="background-color: red; color: white;">���� ��ҵ�</td>
        	</c:when>
        	<c:when test="${e.rvstate eq 2 }">
        		<td style="background-color: yellow;">���� �̿� �� �����</td>
        	</c:when>
        	<c:when test="${e.rvstate eq 3 }">
        		<td style="background: blue; color: white;">���� �̿� ��</td>
        	</c:when>
        	<c:otherwise>
      			<td style="background-color: green; color: white;">�����</td>  	
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
			<li class="disable">��������</li>
		</c:when>
		<c:otherwise>
			<li><a href="${mycontext}/manager/reserve/adminreservelist?cPage=${startPage-pagePerBlock}">��������</a></li>
		</c:otherwise>
	</c:choose>
	<c:forEach varStatus="i" begin="${startPage}" end="${endPage}" step="1">
		<c:choose>
			<c:when test="${i.index == nowPage}">
				<li class="now">${i.index}</li>
			</c:when>
			<c:otherwise>
				<li><a href="${mycontext}/manager/reserve/adminreservelist?cPage=${i.index}">${i.index}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${endPage >= totalPage}">
			<li class="disable">��������</li>
		</c:when>
		<c:otherwise>
			<li><a href="${mycontext}/manager/reserve/adminreservelist?cPage=${startPage+pagePerBlock}">��������</a></li>
		</c:otherwise>		
	</c:choose>
	</ol>
	</td>
	</tr>
	</tfoot>  
  </table>
        </div>
 
    </article>