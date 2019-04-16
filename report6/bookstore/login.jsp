<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>login</title>
</head>
<body bgcolor="#FFFFFF">

<form action="<%=request.getContextPath()%>/checklogin" method="post">
  用户名<input type="text" name="username"></input><br>  
  密码<input type="password" name="pwd"></input><br>  
  <input type="submit" value="登陆"></input><br>  
</form>
<%
   String errmsg=(String)session.getAttribute("errmsg");
   if(errmsg!=null){
     session.removeAttribute("errmsg");
     out.println(errmsg);
   }
%>
</body>
</html>