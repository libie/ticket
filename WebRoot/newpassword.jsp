<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'newpassword.jsp' starting page</title>
	<link href="Css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/jquery/themes/icon.css" />
    <script type="text/javascript" src="js/jquery/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/jquery/jQuery.easyui.js"></script>
    
  </head>
  <c:if test="${logger==null}">
  	<script type="text/javascript">
  	parent.location="login.jsp";
  	</script>
  </c:if>		
  	
  	
  	<script type="text/javascript">
  		function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                alert("请输入密码！");
                return false;
            }
            if ($rePass.val() == '') {
                alert("请在一次输入密码！");
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                alert("两次密码不一至！请重新输入");
                return false;
            }

 //           $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
 //               msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
 //               $newpass.val('');
 //               $rePass.val('');
 //               close();
 //           })

 			var npass=document.getElementById('txtNewPass').value;
            location.href='UpdatePassword?npassword='+npass;
        }
  	</script>
  <body>
  <form action="">
  		<div  style="background: url('images/113.jpg') no-repeat;width: 580px; height: 389px;" >
            <div>
                <table cellpadding=3 align="center">
                	<tr>
                        <td colspan="2" align="center"><h1>修改密码</h1></td>
                    </tr>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="password"  name="npassword"/></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="password"  /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="button" value="确定" onclick="serverLogin()"/></td>
                    </tr>
                </table>
            </div>
            
        </div>
  </form>
  </body>
</html>
