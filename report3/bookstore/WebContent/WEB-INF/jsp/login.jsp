<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <title>login</title>
</head>
<body bgcolor="#FFFFFF">
<%@include file="banner.jsp" %>
<%--<form action="<%=request.getContextPath()%>/login" method="post">--%>
  <%--用户名:<input type="text" name="username"></input><br>--%>
  <%--密码:<input type="password" name="pwd"></input><br>--%>
  <%--<input type="submit" value="登陆"></input><br>  --%>
<%--</form>--%>
<div class="container">

    <form class="form-signin" style="max-width:370px;margin: auto auto;" action="<%=request.getContextPath()%>/login" method="post">
        <h2 class="form-signin-heading">Please log in</h2>
        <label class="sr-only">User</label>
        <input type="text" name="username" class="form-control" placeholder="Username" required autofocus>
        <spacer></spacer>
        <label class="sr-only">Password</label>
        <input type="password" name="pwd" class="form-control" style="margin-top: 10px;" placeholder="Password" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <%
            String errmsg=(String)session.getAttribute("errmsg");
            if(errmsg!=null){
                session.removeAttribute("errmsg");
                %>
                <div class="alert alert-danger" role="alert">Oops! <%=errmsg%></div>
                <%
            }
        %>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
    </form>

</div> <!-- /container -->

</body>
<style>
    body {
        padding-top: 50px;
    }
</style>
</html>