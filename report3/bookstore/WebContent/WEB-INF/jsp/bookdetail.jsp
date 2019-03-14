<%@page import="database.BookDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <title>Book Description</title>
</head>
<body>
	<%@include file="banner.jsp" %>
    <div class="container">

        <div class="starter-template">
            <%
                BookDetails bd=(BookDetails)request.getAttribute("bd");
            %>
            <h2><%=bd.getTitle() %></h2>
            &nbsp; By <em><%=bd.getFirstName() %> <%=bd.getSurname() %></em> &nbsp; &nbsp; (<%=bd.getYear() %>)<br> &nbsp; <br>
            <h4>Critics</h4>
            <blockquote><%=bd.getDescription() %></blockquote>
            <p>
                <a class="btn btn-primary" href="<%=request.getContextPath() %>/showcart?Add=<%=bd.getBookId() %>"> ADD TO Cart</a>&nbsp;&nbsp;&nbsp;
                <a class="btn btn-primary" href="<%=request.getContextPath() %>/catalog"> Continue Shopping</a>
            </p>
        </div>

    </div>

</body>
<style>
    body {
        padding-top: 50px;
    }
    .starter-template {
        text-align: center;
    }
</style>
</html>