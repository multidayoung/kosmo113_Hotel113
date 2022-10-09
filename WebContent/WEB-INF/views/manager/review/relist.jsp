<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style>
	a:link{text-decoration: none;}
	table img { width: 80px;}
		/* paging */
	
	table tfoot ol.paging {
		margin-left:30%;
	    list-style:none;
	}
	
	table tfoot ol.paging li {
	    float:left;
	    margin-right:8px;
	}
	
	table tfoot ol.paging li a {
	    display:block;
	    padding:3px 7px;
	    border:1px solid #00B3DC;
	    color:#2f313e;
	    font-weight:bold;
	}
	
	table tfoot ol.paging li a:hover {
	    background:#00B3DC;
	    color:white;
	    font-weight:bold;
	}
	
	.disable {
	    padding:3px 7px;
	    border:1px solid silver;
	    color:silver;
	}
	
	.now {
	   padding:3px 7px;
	    border:1px solid #ff4aa5;
	    background:#ff4aa5;
	    color:white;
	    font-weight:bold;
	}
		
</style>

<article class="container">
	<header>
		<h1>�Խ���</h1>
	</header>
	<p>������ ��¥�� �����? ${today }<p>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>��ȣ</th>
					<th>����</th>
					<th>�ۼ���</th>
					<th>����</th>
					<th>�ۼ� ����</th> 
				</tr>
			</thead>
			<tbody>
				<c:forEach var="e" items="${list}">
					<tr>
						<td>${e.renum }</td>
						<td>${e.retitle }</td>
						<td>${e.rewriter }</td>
						<td><a href ="view?renum=${e.renum }">${e.recontent }</a></td>
						<td>${e.redate }</td>					
					</tr>
				</c:forEach>
			</tbody>

    <tfoot>
            <tr>
               <td colspan="5">
                  <ol class="paging">
                     <c:choose>
                        <c:when test="${startPage < 6 }">
                           <li class="page-item disable">��������</li>
                        </c:when>
                        <c:otherwise>
                           <li><a
                              href="reviewlist?cPage=${startPage - 1 }">��������</a></li>
                        </c:otherwise>
                     </c:choose>
                     <!-- i.index ����ؼ� ����¡�� �ε����� ���� -->
                     <c:forEach varStatus="i" begin="${startPage }" end="${endPage }"
                        step="1">
                        <c:choose>
                           <c:when test="${i.index == nowPage }">
                              <li class="now">${i.index }</li>
                           </c:when>
                           <c:otherwise>
                              <li><a href="reviewlist?cPage=${i.index }">${i.index }</a></li>
                           </c:otherwise>
                        </c:choose>
                     </c:forEach>
                      <c:choose>
                        <c:when test="${endPage >= totalPage }">
                           <li class="page-item disable">��������</li>
                        </c:when>
                        <c:otherwise>
                           <li class="page-item"><a class="page-link" href="reviewlist?cPage=${endPage + 1}">
                           ��������</a></li>
                        </c:otherwise>      
                     </c:choose>
                  </ol>              
               </td>
            </tr>
         </tfoot>  
    
		</table>
	</div>
	
	
	<button type="button" id="wbtn1" class="btn btn-outline-secondary" onclick="location.href='wr'">�ı��ۼ�</button>
	
	


</article>




