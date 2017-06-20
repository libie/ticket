<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.lt.ticket.dao.impl.CustomDaoImpl"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ticket.jsp' starting page</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
function HS_DateAdd(interval,number,date){
	number = parseInt(number);
	if (typeof(date)=="string"){var date = new Date(date.split("-")[0],date.split("-")[1],date.split("-")[2])}
	if (typeof(date)=="object"){var date = date}
	switch(interval){
	case "y":return new Date(date.getFullYear()+number,date.getMonth(),date.getDate()); break;
	case "m":return new Date(date.getFullYear(),date.getMonth()+number,checkDate(date.getFullYear(),date.getMonth()+number,date.getDate())); break;
	case "d":return new Date(date.getFullYear(),date.getMonth(),date.getDate()+number); break;
	case "w":return new Date(date.getFullYear(),date.getMonth(),7*number+date.getDate()); break;
	}
}
function checkDate(year,month,date){
	var enddate = ["31","28","31","30","31","30","31","31","30","31","30","31"];
	var returnDate = "";
	if (year%4==0){enddate[1]="29"}
	if (date>enddate[month]){returnDate = enddate[month]}else{returnDate = date}
	return returnDate;
}

function WeekDay(date){
	var theDate;
	if (typeof(date)=="string"){theDate = new Date(date.split("-")[0],date.split("-")[1],date.split("-")[2]);}
	if (typeof(date)=="object"){theDate = date}
	return theDate.getDay();
}
function HS_calender(){
	var lis = "";
	var style = "";
	style +="<style type='text/css'>";
	style +=".calender { width:170px; height:auto; font-size:12px; margin-right:14px; background:url(calenderbg.gif) no-repeat right center #fff; border:1px solid #397EAE; padding:1px}";
	style +=".calender ul {list-style-type:none; margin:0; padding:0;}";
	style +=".calender .day { background-color:#EDF5FF; height:20px;}";
	style +=".calender .day li,.calender .date li{ float:left; width:14%; height:20px; line-height:20px; text-align:center}";
	style +=".calender li a { text-decoration:none; font-family:Tahoma; font-size:11px; color:#333}";
	style +=".calender li a:hover { color:#f30; text-decoration:underline}";
	style +=".calender li a.hasArticle {font-weight:bold; color:#f60 !important}";
	style +=".lastMonthDate, .nextMonthDate {color:#bbb;font-size:11px}";
	style +=".selectThisYear a, .selectThisMonth a{text-decoration:none; margin:0 2px; color:#000; font-weight:bold}";
	style +=".calender .LastMonth, .calender .NextMonth{ text-decoration:none; color:#000; font-size:18px; font-weight:bold; line-height:16px;}";
	style +=".calender .LastMonth { float:left;}";
	style +=".calender .NextMonth { float:right;}";
	style +=".calenderBody {clear:both}";
	style +=".calenderTitle {text-align:center;height:20px; line-height:20px; clear:both}";
	style +=".today { background-color:#ffffaa;border:1px solid #f60; padding:2px}";
	style +=".today a { color:#f30; }";
	style +=".calenderBottom {clear:both; border-top:1px solid #ddd; padding: 3px 0; text-align:left}";
	style +=".calenderBottom a {text-decoration:none; margin:2px !important; font-weight:bold; color:#000}";
	style +=".calenderBottom a.closeCalender{float:right}";
	style +=".closeCalenderBox {float:right; border:1px solid #000; background:#fff; font-size:9px; width:11px; height:11px; line-height:11px; text-align:center;overflow:hidden; font-weight:normal !important}";
	style +="</style>";

	var now;
	if (typeof(arguments[0])=="string"){
		selectDate = arguments[0].split("-");
		var year = selectDate[0];
		var month = parseInt(selectDate[1])-1+"";
		var date = selectDate[2];
		now = new Date(year,month,date);
	}else if (typeof(arguments[0])=="object"){
		now = arguments[0];
	}
	var lastMonthEndDate = HS_DateAdd("d","-1",now.getFullYear()+"-"+now.getMonth()+"-01").getDate();
	var lastMonthDate = WeekDay(now.getFullYear()+"-"+now.getMonth()+"-01");
	var thisMonthLastDate = HS_DateAdd("d","-1",now.getFullYear()+"-"+(parseInt(now.getMonth())+1).toString()+"-01");
	var thisMonthEndDate = thisMonthLastDate.getDate();
	var thisMonthEndDay = thisMonthLastDate.getDay();
	var todayObj = new Date();
	today = todayObj.getFullYear()+"-"+todayObj.getMonth()+"-"+todayObj.getDate();
	
	for (i=0; i<lastMonthDate; i++){  // Last Month's Date
		lis = "<li class='lastMonthDate'>"+lastMonthEndDate+"</li>" + lis;
		lastMonthEndDate--;
	}
	for (i=1; i<=thisMonthEndDate; i++){ // Current Month's Date

		if(today == now.getFullYear()+"-"+now.getMonth()+"-"+i){
			var todayString = now.getFullYear()+"-"+(parseInt(now.getMonth())+1).toString()+"-"+i;
			lis += "<li><a href=javascript:void(0) class='today' onclick='_selectThisDay(this)' title='"+now.getFullYear()+"-"+(parseInt(now.getMonth())+1)+"-"+i+"'>"+i+"</a></li>";
		}else{
			lis += "<li><a href=javascript:void(0) onclick='_selectThisDay(this)' title='"+now.getFullYear()+"-"+(parseInt(now.getMonth())+1)+"-"+i+"'>"+i+"</a></li>";
		}
		
	}
	var j=1;
	for (i=thisMonthEndDay; i<6; i++){  // Next Month's Date
		lis += "<li class='nextMonthDate'>"+j+"</li>";
		j++;
	}
	lis += style;

	var CalenderTitle = "<a href='javascript:void(0)' class='NextMonth' onclick=HS_calender(HS_DateAdd('m',1,'"+now.getFullYear()+"-"+now.getMonth()+"-"+now.getDate()+"'),this) title='Next Month'>&raquo;</a>";
	CalenderTitle += "<a href='javascript:void(0)' class='LastMonth' onclick=HS_calender(HS_DateAdd('m',-1,'"+now.getFullYear()+"-"+now.getMonth()+"-"+now.getDate()+"'),this) title='Previous Month'>&laquo;</a>";
	CalenderTitle += "<span class='selectThisYear'><a href='javascript:void(0)' onclick='CalenderselectYear(this)' title='Click here to select other year' >"+now.getFullYear()+"</a></span>年<span class='selectThisMonth'><a href='javascript:void(0)' onclick='CalenderselectMonth(this)' title='Click here to select other month'>"+(parseInt(now.getMonth())+1).toString()+"</a></span>月"; 

	if (arguments.length>1){
		arguments[1].parentNode.parentNode.getElementsByTagName("ul")[1].innerHTML = lis;
		arguments[1].parentNode.innerHTML = CalenderTitle;

	}else{
		var CalenderBox = style+"<div class='calender'><div class='calenderTitle'>"+CalenderTitle+"</div><div class='calenderBody'><ul class='day'><li>日</li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li>六</li></ul><ul class='date' id='thisMonthDate'>"+lis+"</ul></div><div class='calenderBottom'><a href='javascript:void(0)' class='closeCalender' onclick='closeCalender(this)'>×</a><span><span><a href=javascript:void(0) onclick='_selectThisDay(this)' title='"+todayString+"'>Today</a></span></span></div></div>";
		return CalenderBox;
	}
}
function _selectThisDay(d){
	var boxObj = d.parentNode.parentNode.parentNode.parentNode.parentNode;
		boxObj.targetObj.value = d.title;
		boxObj.parentNode.removeChild(boxObj);
}
function closeCalender(d){
	var boxObj = d.parentNode.parentNode.parentNode;
		boxObj.parentNode.removeChild(boxObj);
}

function CalenderselectYear(obj){
		var opt = "";
		var thisYear = obj.innerHTML;
		for (i=1970; i<=2020; i++){
			if (i==thisYear){
				opt += "<option value="+i+" selected>"+i+"</option>";
			}else{
				opt += "<option value="+i+">"+i+"</option>";
			}
		}
		opt = "<select onblur='selectThisYear(this)' onchange='selectThisYear(this)' style='font-size:11px'>"+opt+"</select>";
		obj.parentNode.innerHTML = opt;
}

function selectThisYear(obj){
	HS_calender(obj.value+"-"+obj.parentNode.parentNode.getElementsByTagName("span")[1].getElementsByTagName("a")[0].innerHTML+"-1",obj.parentNode);
}

function CalenderselectMonth(obj){
		var opt = "";
		var thisMonth = obj.innerHTML;
		for (i=1; i<=12; i++){
			if (i==thisMonth){
				opt += "<option value="+i+" selected>"+i+"</option>";
			}else{
				opt += "<option value="+i+">"+i+"</option>";
			}
		}
		opt = "<select onblur='selectThisMonth(this)' onchange='selectThisMonth(this)' style='font-size:11px'>"+opt+"</select>";
		obj.parentNode.innerHTML = opt;
}
function selectThisMonth(obj){
	HS_calender(obj.parentNode.parentNode.getElementsByTagName("span")[0].getElementsByTagName("a")[0].innerHTML+"-"+obj.value+"-1",obj.parentNode);
}
function HS_setDate(inputObj){
	var calenderObj = document.createElement("span");
	calenderObj.innerHTML = HS_calender(new Date());
	calenderObj.style.position = "absolute";
	calenderObj.targetObj = inputObj;
	inputObj.parentNode.insertBefore(calenderObj,inputObj.nextSibling);
}
  </script>
  <c:if test="${logger==null}">
  	<script type="text/javascript">
  	parent.location="login.jsp";
  	</script>
  </c:if>
  
  <c:if test="${customsfenye==null}">
  	<script type="text/javascript">
  		location='SearchCustomByDate?page=1';
  	</script>
  </c:if>
  
  <%
  	int pageSize=15;
  	Integer count=0;
  
  	//System.out.println("总数据量"+request.getAttribute("count"));
  	if(request.getAttribute("count")!=null){
  		count=(Integer)request.getAttribute("count");
  	}
  	
  	int totalpages = (count%15==0)?count/15:(count/15+1);
  	//System.out.println(count+"总页数"+totalpages);
  	int nowPage;
  	Object nowpagestr = request.getAttribute("page");
  	if(nowpagestr==null){
  		nowPage=1;
  	}else{
  		String nowpagetemp = (String)request.getAttribute("page");
  		nowPage = Integer.parseInt(nowpagetemp);
  	}
  %>
  
  <body>
    <div>
    	<form action="SearchCustomByDate?nowpage=1" method="post" name="myform">
    		<table  border="1" bordercolor="black" style=" width: 800px; border: none ;">
    			<tr  bgcolor="#C1CDCD">
		  			<td>日期从：</td>
		  			<td>日期到：</td>
		  			<td>客户属性</td>
		  			<td>付款方式</td>
		  			<td>购票地点</td>
		  			<td>姓名</td>
		  			<td>单位</td>
		  			<td>&nbsp;</td>
		  		</tr>
		  		<tr  bgcolor="#C1CDCD">
		  			<td><input type="text" onfocus="HS_setDate(this)" name="sdate" style="width: 90px" value=""/></td>
		  			<td><input type="text" onfocus="HS_setDate(this)" name="edate" style="width: 90px" value=""/></td>
		  			<td>
		  			<select id="khsx" name="khsx" style="border: none;">
		  				<option value="">全部</option>
	  					<option value="散户">散户</option>
	  					<option value="团队票">团队票</option>
	  					<option value="赠票">赠票</option>
  					</select>
		  			</td>
		  			<td>
						<select id="fkfs" name="fkfs" style="width: 70px;border: none;">
							<option value="">全部</option>
							<option value="现金">现金</option>
							<option value="刷卡">刷卡</option>
							<option value="挂账">挂账</option>
						</select>
					</td>
		  			<td>
		  				<select id="gpdd" name="gpdd" style="border: none;">
		  					<option value="">全部</option>
							<option value="蛤蜊岛度假村">蛤蜊岛度假村</option>
							<option value="黄海岸酒店">黄海岸酒店</option>
							<option value="售票点三">售票点三</option>
							<option value="售票点四">售票点四</option>
							<option value="售票点五">售票点五</option>
						</select>
		  			</td>
		  			<td><input type="text" name="xm" style="width: 70px" value=""/></td>
		  			<td><input type="text" name="dw" style="width: 70px" value=""/></td>
		  			<td><input type="submit" value="查询"/></td>
		  		</tr>
    			<tr bgcolor="#C1CDCD">
		  			<td>客户ID</td>
		  			<td>客户属性</td>
		  			<td>人数</td>
		  			<td>付款方式</td>
		  			<td>购票地点</td>
		  			<td>姓名</td>
		  			<td>单位</td>
		  			<td>&nbsp;</td>
		  		</tr>
		  		
		  		
		  		<c:forEach items="${customsfenye}" var="custom" varStatus="status">
		  		<tr <c:if test="${status.index%2==0 }"> bgcolor="#d1eeee"</c:if>>
		  			<td><a href="SearchTicketByCID?cid=${custom.c_id }" >${custom.c_id }&nbsp;</a></td>
		  			<td>${custom.c_khsx }&nbsp;</td>
		  			<td>${custom.c_rs }&nbsp;</td>
		  			<td>${custom.c_fkfs }&nbsp;</td>
		  			<td>${custom.c_gpdd }&nbsp;</td>
		  			<td>${custom.c_xm }&nbsp;</td>
		  			<td>${custom.c_dw }&nbsp;</td>
		  			<td>
		  			<c:if test="${logger.u_id=='admin'}">
		  				<input type="button" value="修改" onclick="javascript:location='Update?cid=${custom.c_id }'"/>
		  				<input type="button" value="删除" onclick="deletec('${custom.c_id }')"/>
		  			</c:if>
		  			&nbsp;</td>
		  		</tr>
		  		</c:forEach>
		  		<tr align="center">
		  			<td colspan="8">
		  			<span>
		  				<input type="button" value="上一页" onclick="bakward()"/>&nbsp;&nbsp;
		  				<b>当前第<%=nowPage %>页&nbsp;&nbsp;共<%=totalpages %>页<%=count %>条</b>&nbsp;&nbsp;
		  				<input type="button" value="下一页" onclick="forward()"/>
		  			</span>
		  			<script type="text/javascript">
		  			function bakward(){
			  			if(<%=nowPage%>==1)
				  			return;
			  			location='CustomFenye?page=<%=nowPage-1 %>';
			  		}
		  			function forward(){
			  			if(<%=nowPage%>==<%=totalpages%>)
				  			return;
			  			location='CustomFenye?page=<%=nowPage+1 %>';
			  		}
		  			</script>
		  			</td>
		  		</tr>
    		</table>
    	</form>
    </div>
    <script type="text/javascript">
    function deletec(cid){
			if(confirm("确定删除？")){
				location='DeleteCustom?cid='+cid;
				}
        }
  	</script>
  </body>
</html>
