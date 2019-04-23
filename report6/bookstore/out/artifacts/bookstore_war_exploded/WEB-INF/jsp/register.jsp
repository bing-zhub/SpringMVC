<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <title>Register</title>
</head>
<body bgcolor="#FFFFFF">
<%@include file="banner.jsp" %>
<div class="container">

    <form class="form-signin" style="max-width:370px;margin: auto auto;" action="<%=request.getContextPath()%>/register" method="post">
        <h2 class="form-signin-heading" style="text-align:center;">Register</h2>
        <label class="sr-only">UserId</label>
        <input type="text" name="userid" class="form-control" placeholder="UserId" required autofocus>
        <spacer></spacer>
        <label class="sr-only">Username</label>
        <input type="text" name="username" class="form-control" style="margin-top: 10px;" placeholder="Username" required autofocus>
        <spacer></spacer>
        <label class="sr-only">Password</label>
        <input type="password" name="pwd" class="form-control" style="margin-top: 10px;" placeholder="Password" required>
        <label class="sr-only">Repeat Password</label>
        <input type="password" name="rpwd" class="form-control" style="margin-top: 10px;" placeholder="Repeat Password" required>
        <%
            String errmsg=(String)session.getAttribute("errmsg");
            if(errmsg!=null){
                session.removeAttribute("errmsg");
        %>
        <div class="alert alert-danger" role="alert" style="margin-top: 20px;">Oops! <%=errmsg%></div>
        <%
            }
        %>

        <button class="btn btn-lg btn-primary col-md-4" style="margin-top: 25px;display: inline-block;margin-left: 25px;" type="submit">Register</button>
        <a class="btn btn-lg btn-primary col-md-4" style="margin-top: 25px;display: inline-block;margin-left: 45px;" href="/login">Log in</a>
    </form>

</div>

</body>
<style>
    body {
        padding-top: 50px;
    }
</style>
</html>