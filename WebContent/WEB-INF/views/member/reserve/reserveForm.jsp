<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.js" charset = "UTF-8"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function () {
		$(".date").datepicker('setDate', 'today');
	});
	$.datepicker.setDefaults({
	  dateFormat: 'yy-mm-dd',
	  prevText: '���� ��',
	  nextText: '���� ��',
	  monthNames: ['1��', '2��', '3��', '4��', '5��', '6��', '7��', '8��', '9��', '10��', '11��', '12��'],
	  monthNamesShort: ['1��', '2��', '3��', '4��', '5��', '6��', '7��', '8��', '9��', '10��', '11��', '12��'],
	  dayNames: ['��', '��', 'ȭ', '��', '��', '��', '��'],
	  dayNamesShort: ['��', '��', 'ȭ', '��', '��', '��', '��'],
	  dayNamesMin: ['��', '��', 'ȭ', '��', '��', '��', '��'],
	  showMonthAfterYear: true,
	  yearSuffix: '��',
	  minDate: "+1D",
	  maxDate: "+3M",
	  beforeShowDay: cannotreserve
	});	
	$(function () {
		$(".date").datepicker();
	});
	$(function () {
		$("#rvend").change(function () {
			var before = new Date($("#rvstart").val());
			var after = new Date($("#rvend").val());
			console.log($("#rvstart").val());
			console.log($("#rvend").val());
			var timeval = Math.ceil(after.getTime() - before.getTime());
			var timeval2 = (timeval/1000/60/60/24);	
			var rprice = "<c:out value = '30000'/>";
			console.log(rprice);
			console.log(timeval2);
			if(timeval < 0){
				$("#target").html("�ùٸ� ��¥�� �Է��ϼ���");
				$("#rebtn").attr("disabled", true);
			}else if(timeval > 0){
				$("#target").html("��¥�� ���������� �ԷµǾ����ϴ�.");
				let totalprice = timeval2 * rprice;
				$("#rvprice").attr("value", totalprice);
				$("#rebtn").attr("disabled", false);
			}else{
				$("#target").html("���� ��¥�� ������ �� �����ϴ�.");
				$("#rebtn").attr("disabled", true);
			}
		});
	});
	
	function check() {
		var chk = confirm("�����Ͻðڽ��ϱ�?");
		if(chk){
			alert("����Ǿ����ϴ�.");
			return true;
		}else{
			alert("������ ��ҵǾ����ϴ�.");
			return false;
		}
	}	
	function cannotreserve() {	
		var dateRange = [];
		var dateString = jQuery.datepicker.formatDate('yy-mm-dd', date);
		<c:forEach var="day" items="${cannot }">			
				var start = "${day.rvstart}";
				var end = "${day.rvend}";
				for(var d = new Date(start); d<= new Date(end); d.setDate(d.getDate()+1)){
					dateRange.push($.datepicker.formatDate('yy-mm-dd',d));
				}
				return [dateRange.indexOf(dateString) == -1];
		</c:forEach>
	}
</script>
<article class="container">
        <header>
            <h2>���� ������</h2>
        </header>        
        <form action="addreserve" method="post" onsubmit="return check();">
        <%--
        var rprice = "<c:out value = ${room.rprice}/>";
        
        <div>
        	<h5>���� ����</h5>    
        	ȸ�� ���̵� : <input type="text" name="mid" id="mid" value="${sessionScope.sessionID }" readonly="readonly"><br>   	       	
        	���ǹ�ȣ : <input type="number" name="rnum" id="rnum" value="${room.rnum }" readonly="readonly"><br>
         	1�� �� ���� : <input type="number" name="rpirce" id="rprice" value="${room.rprice }" readonly="readonly">
        </div>
         --%>
        <div>
        	<h5>���� ����</h5>    
        	ȸ�� ���̵� : <input type="text" name="mid" id="mid" value="test3" readonly="readonly"><br>   	       	
        	���ǹ�ȣ : <input type="number" name="rnum" id="rnum" value="203" readonly="readonly"><br>
         	1�� �� ���� : <input type="number" name="rpirce" id="rprice" value="30000" readonly="readonly">
        </div>
        <div>
        
  			<h5>���� �Ⱓ ����</h5>
  			<label>��� ������</label>
  			<input type="text" class="date" name="rvstart" id="rvstart" placeholder="��� ������">
  			<label>��� ������</label>  			
  			<input type="text" class="date" name="rvend" id="rvend" placeholder="��� ������">
  			<div id="target"></div>  			
        </div>
        <div>
        	<h5>�� �ݾ�</h5>
        	<input type="number" name="rvprice" id="rvprice" placeholder="�� �ݾ�" readonly="readonly">        	
        </div>
        <div>
        	<input type="submit" value="�����ϱ�" id="rebtn" disabled="disabled">
        </div>
        </form>
    </article>
