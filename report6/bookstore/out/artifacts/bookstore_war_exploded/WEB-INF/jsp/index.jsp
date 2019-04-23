<%@page import="bean.BookDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<title>Duke's Bookstore</title>
</head>
<body style="padding-top: 50px;">
	<%@include file="banner.jsp" %>
	<div class="container">

		<div class="starter-template">
			<%
				BookDetails bd=(BookDetails)request.getAttribute("bd");

			%>
			<b>What We're Reading</b>
			<p>
			<blockquote>
				<em>
					<a href="<%=request.getContextPath() %>/bookdetails?bookId=<%=bd.getBookId() %>"><%=bd.getTitle() %></a>
				</em>
				talks about how web components can transform the way you develop applications for the web. This is a must read for any self respecting web developer!
			</blockquote>
			</p>
			<p>
				<a href="<%=request.getContextPath() %>/catalog"><b>Start Shopping</b></a>
			</p>
		</div>

	</div>
</body>
</html>