<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户票据详情</title>

  </head>
  <c:if test="${logger==null}">
  	<script type="text/javascript">
  	parent.location="login.jsp";
  	</script>
  </c:if>
  
  <body>
    <div>
    <br/><br/>
    	<form action="UpdateTicket" method="post">
    		<table  bordercolor="black"  style="text-align: center; width: 800px; border: none ;"  border="1">
    			<tr bgcolor="#C1CDCD">
		  			<td>客户ID</td>
		  			<td>票号</td>
		  			<td>车牌号</td>
		  			<td>价格</td>
		  			<td>折扣</td>
		  			<td>优惠价格</td>
		  			<td>是否赠票</td>
		  			<td>记录时间</td>
		  			<td>&nbsp;</td>
		  		</tr>
		  		<c:forEach items="${tickets}" var="ticket" varStatus="status">
		  		<tr <c:if test="${status.index%2==0 }"> bgcolor="#d1eeee"</c:if>>
		  			<td><input style="width: 110px;" type="text" value="${ticket.c_id }" disabled="disabled" name="cid"/>&nbsp;</td>
		  			<td><input style="width: 100px;" type="text" value="${ticket.t_ph }" id="${ticket.t_ph }" disabled="disabled" name="ph" readonly="readonly"/>&nbsp;</td>
		  			<td><input style="width: 70px;" type="text" value="${ticket.t_cph }" disabled="disabled" name="cph"/>&nbsp;</td>
		  			<td><input style="width: 50px;" type="text" value="${ticket.t_jg }" disabled="disabled" name="jg" id="jg${status.index }" onchange="zongjiage(${status.index })"/>&nbsp;</td>
		  			<%--<td><input style="width: 40px;" type="text" value="${ticket.t_zk }" disabled="disabled" name="zk" id="zk" onchange="zongjiage()"/>&nbsp;</td>--%>
		  			<td>
						<select id="zk${status.index }" onchange="zongjiage(${status.index })" name="zk" disabled="disabled">
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
						<script type="text/javascript">
							var options1=document.getElementById("zk${status.index }").childNodes;
							for(var i=0;i<options1.length;i++){
								//alert(options1[i].value);
								//alert(options1[i]);
								//alert(${ticket.t_zk });
								if(${ticket.t_zk }==options1[i].value){
									//alert(options1[i].value);
									options1[i].selected="selected";
								}
							}
						</script>
					</td>
		  			<td><input style="width: 60px;" type="text" value="${ticket.t_yhjg }" disabled="disabled" name="yhjg" id="yhjg${status.index }"/>&nbsp;</td>
		  			<%--<td><input style="width: 60px;" type="text" value="${ticket.t_sfzp }" disabled="disabled" name="sfzp"/>&nbsp;</td>--%>
		  			<td>
		  			<select disabled='disabled' id='sfzp' name="sfzp">
						<option value='否'>否</option>
						<option value='是'>是</option>
					</select>
		  			</td>
		  			<td><input style="width: 70px;" type="text" value="${ticket.t_lrsj }" disabled="disabled" name="lrsj"/>&nbsp;</td>
		  			<td>
		  			<c:if test="${logger.u_id=='admin'}">
		  				<input type="button" value="修改" onclick='enable("${ticket.t_ph }")'/>
		  				<input type="button" value="删除" onclick='deletet("${ticket.t_ph }")'/>
		  			</c:if>
		  			&nbsp;
		  			</td>
		  		</tr>
		  		
		  		</c:forEach>
    		</table>
    		<br/>
    		<input type="submit" value="提交" id="tijiao" disabled="disabled"/>
    	</form>
    </div>
    <script type="text/javascript">
    function $(id){
		return document.getElementById(id);	
	}
    function zongjiage(index){
  	  	if($("jg"+index).value!=""&&$("zk"+index).value!=""){
  	  		$("yhjg"+index).value=($("jg"+index).value*$("zk"+index).value/10).toFixed(2);
  	  	}
  	  	if($("jg"+index).value==NaN){
			alert("输入有误，请检查！");
			$("jg"+index).value=0;
	  	}
  	}
   
    </script>
    <script type="text/javascript">
		function deletet(tph){
			if(confirm("确定删除？")){
				location="Deleteticket?tph="+tph;
				}
		}
	
		function enable(id){
			var inputs1=document.getElementById(id).parentNode.parentNode.parentNode.getElementsByTagName("input");
			for(i=0;i<inputs1.length;i++){
				inputs1[i].disabled=true;
			}
			var inputs2 = document.getElementById(id).parentNode.parentNode.getElementsByTagName("input");
			for(i=0;i<inputs2.length;i++){
				inputs2[i].disabled=false;
			}
			var select1 = document.getElementById(id).parentNode.parentNode.getElementsByTagName("select");
			for(i=0;i<select1.length;i++){
				select1[i].disabled=false;
			}
			document.getElementById("tijiao").disabled=false;
		}


	</script>
  </body>
</html>
