<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ticket.jsp' starting page</title>

  </head>
  <c:if test="${logger==null}">
  	<script type="text/javascript">
  	parent.location="login.jsp";
  	</script>
  </c:if>
  
  <body style="width: 1024px">
    <div>
    	<form action="UpdateCustom" method="post">
    		<table bordercolor="black"  style="text-align: center; width: 800px; border: none ;"  border="1">
		  		<tr>
		  			<td colspan="8">&nbsp;</td>
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
		  		<tr>
		  			<td>${custom.c_id }<input type="hidden" value="${custom.c_id }" name="cid"/>&nbsp;</td>
		  			<%--<td><input type="text" value="${custom.c_khsx }" name="khsx" style="width: 70px"/>&nbsp;</td>
		  			--%>
		  			<td><select onchange="kehushuxing()" name="khsx" style="border: none;" >
	  					<option value="散户">散户</option>
	  					<option value="团队票">团队票</option>
	  					<option value="赠票">赠票</option>
	  				</select></td>
		  			<td><input type="text" value="${custom.c_rs }" name="rs" style="width: 30px" onchange="zongjiage()"/>&nbsp;</td>
		  			<%--<td><input type="text" value="${custom.c_fkfs }" name="fkfs" style="width: 70px"/>&nbsp;</td>
		  			--%>
		  			<td>
						<select name="fkfs" style="width: 70px;border: none;">
							<option value="现金">现金</option>
							<option value="刷卡">刷卡</option>
							<option value="挂账">挂账</option>
						</select>
					</td>
		  			<%--<td><input type="text" value="${custom.c_gpdd }" name="gpdd" style="width: 100px"/>&nbsp;</td>
		  			--%>
		  			<td>
		  				<select name="gpdd" style="border: none;">
							<option value="蛤蜊岛度假村">蛤蜊岛度假村</option>
							<option value="黄海岸酒店">黄海岸酒店</option>
							<option value="售票点三">售票点三</option>
							<option value="售票点四">售票点四</option>
							<option value="售票点五">售票点五</option>
						</select>
		  			</td>
		  			<td><input type="text" value="${custom.c_xm }" name="xm" style="width: 50px" />&nbsp;</td>
		  			<td><input type="text" value="${custom.c_dw }" name="dw" style="width: 50px"/>&nbsp;</td>
		  			<td><input type="submit" value="确定"/>&nbsp;</td>
		  		</tr>
		  		
    		</table>
    	</form>
    </div>
    
  </body>
</html>
