<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<article class="container">
	<h3>답글</h3>
        
        <div class="container">
        
		 <input type="hidden" name="renum" value="${vo.renum }">
		 <div class="form-group">
			 관리자 : <input type="text" name="aname" value="${vo.comm.admin.aname }" readonly="readonly">
		 </div>
	    <div class="form-group">
	      <label for="rccontent">내용</label>
	      <textarea class="form-control" rows="5" id="rccontent" name="rccontent" 
	      readonly="readonly">${vo.comm.rccontent }</textarea>
	    </div>
	    <div class="form-group">
	     	작성날짜 : <input type="text" name="rcdate" value="${vo.comm.rcdate }" readonly="readonly">
	    </div>
	    <div class="form-group" style="text-align: right; margin-top: 10px;">
	      
	      <button type="button" onclick="location='${pageContext.request.contextPath}/member/review/view?renum=${vo.renum }'" class="btn btn-primary">후기글로 돌아가기</button>
	    </div>
		</div>
</article>