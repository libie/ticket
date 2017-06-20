<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>售票系统登录页面</title>

  </head>
  <c:if test="${logger!=null}">
  	<script type="text/javascript">
  		location='index.html';
  	</script>
  </c:if>
  
  <c:if test="${npas!=null}">
  		<script type="text/javascript">
			alert("${npas}");
		</script>
		<%session.removeAttribute("npas"); %>
  	</c:if>
  
  <body>
  <center>
	<div style="width: 800px; "><br/><br/>
	  	<h1 align="center">欢迎使用卓信售票系统&nbsp;V1.5</h1>
	    <img alt="蛤蜊岛风景" src="images/113.jpg" style="float: left;"/>
	    <form action="Login" method="post" style="float: left;">
	    <div align="center" style=" width: 200px;height: 389px; border: 0px solid red; float: left; border: solid 1px; background-color: #AEEEEE;">
	    	<br/>
	    	<h3>请登录：</h3>
	    	
	    	<b style="color: red;">${err }</b><br/><br/>
	    	<b>用户名：</b>
	    		<select name="userName">
	    			<option value="admin">admin</option>
	    			<option value="user1">user1</option>
	    			<option value="user2">user2</option>
	    		</select>
	    	<br/><br/><br/>
	    	<b>密&nbsp;&nbsp;码：</b><input type="password" name="passWord" style="width: 80px;"/><br/><br/><br/>
	    	<input type="submit" value="登录" />
    	</div>
    	</form>
	</div>
	</center>
  </body>
</html>
