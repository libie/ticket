<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.lt.ticket.util.Tools"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
    <title>售票统计查询系统</title>
  </head>
  <c:if test="${logger==null}">
  	<script type="text/javascript">
  	//window.location="login.jsp";
  		parent.location="login.jsp";
  	</script>
  </c:if>
  
  <script type="text/javascript">
  var renshu;
	function $(id){
		return document.getElementById(id);	
	}
  
  	function queding(){
  	  	if($("khsx").value!="赠票"&&!($("yhjg").value>0)){
			alert("输入有误，请检查！");
			return;
  	  	}
  	  	if($("yhjg").value==NaN){
			alert("输入有误，请检查！");
			return;
  	  	}
		$("khsx").disabled="disabled";
		$("jg").disabled="disabled";
		$("rs").disabled="disabled";
		$("zk").disabled="disabled";
		$("fkfs").disabled="disabled";
		$("gpdd").disabled="disabled";
		$("xm").disabled="disabled";
		$("dw").disabled="disabled";
		$("cz").disabled="disabled";
		$("qd").disabled="disabled";
		$("pjxx").style.display="block";

		
//		$("khid0").value=$("khid").value;
//		$("jg0").value=$("jg").value;
//		$("zk0").value=$("zk").value;
//		$("yhjg0").value=($("jg").value*$("zk").value/10).toFixed(2);
//		if($("khsx").value=="赠票"){
//			$("zk0").value=0;
//			$("sfzp0").value="是";
//		}
		renshu=$("rs").value;
		showxinxi();
		$("subb").disabled=false;
  	}

  	function zongjiage(){
  	  	if($("jg").value!=""&&$("zk").value!=""){
  	  		$("yhjg").value=($("jg").value*$("zk").value/10*$("rs").value).toFixed(2);
  	  	}
  	}

  	function kehushuxing(){
		if($("khsx").value=="赠票"){
			$("jg").value="0.00";
			$("zk").value="0.0";
			$("yhjg").value="0.00";
			$("jg").disabled=true;
			$("zk").disabled=true;
		}else{
			$("zk").value="10";
			$("jg").disabled=false;
			$("zk").disabled=false;
		}
  	}

 <%--
  	function qingkong(){
  		$("khsx").value="散户";
  		$("jg2").value="0.00";
  		$("rs").value="1";
		$("zk2").value="10";
		$("yhjg2").value="0.00";
		$("fkfs").value="现金";
		$("gpdd").value="蛤蜊岛度假村";
		$("xm").value="无";
		$("dw").value="无";
  	}
 --%>

 function sub(){
	 var formlist1 = $("myform").getElementsByTagName("input");
     for(var i=0;i<formlist1.length;i++)
     {
    	 formlist1[i].disabled=false;
     }
     var formlist2 = $("myform").getElementsByTagName("select");
     for(var i=0;i<formlist2.length;i++)
     {
    	 formlist2[i].disabled=false;
     }
 }

 </script>
 
  <body style="width: 1024px; height: 768px;">
  <div style="width: 760px;">
  
  
  	<%--<h2 style="text-align: center;">售票统计查询系统</h2>
  	<h4><a href="Search">查询服务</a></h4><br/>
--%><form action="InsertTicket" method="post" onsubmit="sub()" id="myform">
  	<table style="background-color:#FFFFFF; text-align: center;width: 800px; border: none ;" border="1" bordercolor="black">
  		<tr>
  			<td colspan="11">客户信息</td>
  		</tr>
  		<tr bgcolor="#C1CDCD">
  			<td style="display: none;">客户ID</td>
  			<td>客户属性</td>
  			<td>价格</td>
  			<td>人数</td>
  			<td>折扣</td>
  			<td>优惠价格</td>
  			<td>付款方式</td>
  			<td>购票地点</td>
  			<td>姓名</td>
  			<td>单位</td>
  			<td>&nbsp;</td>
  		</tr>
  
  		<tr >
  			<td style="display: none;"><input type="text" value="客户ID" style="width: 95px; " disabled="disabled" id="khid" name="khid"/></td>
  			<td>
  				<select id="khsx" onchange="kehushuxing()" name="khsx" style="border: none;">
  					<option value="散户">散户</option>
  					<option value="团队票">团队票</option>
  					<option value="赠票">赠票</option>
  				</select>  			
  			</td>
  			<td><input type="text" value="0.00" style="width: 70px;border: none;" id="jg" onchange="zongjiage()" name="jg"/></td>
  			<td><input type="text" value="1" style="width: 70px;border: none;" id="rs" onchange="zongjiage()" name="rs"/></td>
  			<td>
				<select id="zk" onchange="zongjiage()" name="zk">
					<option value="10">10</option>
					<option value="9.5">9.5</option>
					<option value="9">9</option>
					<option value="8.5">8.5</option>
					<option value="8">8</option>
					<option value="7.5">7.5</option>
					<option value="7">7</option>
					<option value="6.5">6.5</option>
					<option value="6">6</option>
					<option value="5.5">5.5</option>
					<option value="5">5</option>
					<option value="4.5">4.5</option>
					<option value="4">4</option>
					<option value="3.5">3.5</option>
					<option value="3">3</option>
					<option value="2.5">2.5</option>
					<option value="2">2</option>
					<option value="1.5">1.5</option>
					<option value="1">1</option>
				</select>
			</td>
  			<td><input type="text" value="0.00" style="width: 70px;border: none;" disabled="disabled" id="yhjg"/></td>
  			<td>
				<select id="fkfs" name="fkfs" style="width: 70px;border: none;">
					<option value="现金">现金</option>
					<option value="刷卡">刷卡</option>
					<option value="挂账">挂账</option>
				</select>
			</td>
  			<td>
  				<select id="gpdd" name="gpdd" style="border: none;">
					<option value="蛤蜊岛度假村">蛤蜊岛度假村</option>
					<option value="黄海岸酒店">黄海岸酒店</option>
					<option value="售票点三">售票点三</option>
					<option value="售票点四">售票点四</option>
					<option value="售票点五">售票点五</option>
				</select>
  			</td>
  			<td><input type="text" value="无" style="width: 70px;border: none;" id="xm" name="xm"/></td>
  			<td><input type="text" value="无" style="width: 70px;border: none;" id="dw" name="dw"/></td>
  			<td><input type="button" value="确定" id="qd" onclick="queding()"/>
  				<input type="button" style="display: none;" value="清空" id="cz" onclick="javascript:document.location.reload();"/></td>
  		</tr>
  		
  		<tr>
  			<td colspan="11">&nbsp;</td>
  		</tr>
  		<tr>
  			<td colspan="11">票据信息</td>
  		</tr>
  		<tr style="display: none;" id="pjxx">
  			<td colspan="11">
	  			<div id="piaoju">无</div>
  			</td>
  		</tr>
  	</table>
  	<input type="submit" value="保存" disabled="disabled" id="subb"/>&nbsp;
  	<input type="button" value="清空所有" onclick="javascript:document.location.reload();"/>
</form>

<script type="text/javascript">
	function showxinxi(){
		var StrXinxi;
		StrXinxi="<table style='background-color:#FFFFFF; text-align: center; width: 100%;border:none;' border='1' align='center'>"+
			"<thead>"+
			"<tr bgcolor='#C1CDCD'>"+
			"<th style='display: none;'>客户ID</th>"+
			"<th>票号</th>"+
			"<th>车牌号</th>"+
			"<th>价格</th>"+
			"<th>折扣</th>"+
			"<th>优惠价格</th>"+
			"<th>是否赠票</th>"+
  			"<th>记录时间</th>"+
			"</tr>"+
			"</thead>"+
			"<tbody>";
		for(var i=0;i<renshu;i++){
			StrXinxi+="<tr";
				if(i%2==0){
					StrXinxi+=" bgcolor='#d1eeee'";
				}
				StrXinxi+=">"+
				"<td style='display: none;'><input type='text' value='' style='width: 50px' disabled='disabled' id='khid"+i+"'/></td>"+
				"<td><input type='text' value='' style='width: 80px' id='ph"+i+"' name='ph"+i+"'/></td>"+
				"<td><input type='text' value='' style='width: 80px' id='cph"+i+"' name='cph"+i+"'/></td>"+
				"<td><input type='text' value='0.00' style='width: 50px' disabled='disabled' id='jg"+i+"'/></td>"+
				"<td><input type='text' value='0.0' style='width: 50px' disabled='disabled' id='zk"+i+"' name='zk"+i+"'/></td>"+
				"<td><input type='text' value='0.00' style='width: 70px' disabled='disabled' id='yhjg"+i+"' name='yhjg"+i+"'/></td>"+
				"<td>"+
				"<select disabled='disabled' id='sfzp"+i+"'>"+
				"<option value='否'>否</option>"+
				"<option value='是'>是</option>"+
				"</select>"+
				"</td>"+
				"<td><input type='text' value='' style='width: 70px' id='jlsj"+i+"' name='jlsj"+i+"' onfocus='HS_setDate(this)'/></td>"+
				"</tr>";
		}
		StrXinxi+="</tbody></table>";
		$('piaoju').innerHTML=StrXinxi;
		jisuanjiage();
	}
</script>


  </div>
<script type="text/javascript">
function jisuanjiage(){
	for(var i=0;i<renshu;i++){
		$("khid"+i).value=$("khid").value;
		$("jg"+i).value=$("jg").value;
		$("zk"+i).value=$("zk").value;
		$("yhjg"+i).value=($("jg"+i).value*$("zk"+i).value/10).toFixed(2);
		if($("khsx").value=="赠票"){
			$("zk"+i).value="0.0";
			$("sfzp"+i).value="是";
		}
		var date1 = new Date();
		var yyyy=date1.getFullYear()+"";
		var mm = (date1.getMonth()+1)+"";
		var dd = date1.getDate()+"";
		$("jlsj"+i).value=yyyy+"-"+mm+"-"+dd;
	}
}

	//设置客户ID
 	var myDate = new Date();
 	//var time = ""+myDate.getFullYear()+myDate.getMonth()+myDate.getDate()+myDate.getHours()+myDate.getMinutes()+myDate.getSeconds();
	//alert(time);
	$("khid").value=myDate.getTime();
	//alert(myDate.getTime());
	
</script>

  </body>
</html>
