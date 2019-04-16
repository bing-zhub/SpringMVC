<%@page import="cart.ShoppingCartItem"%>
<%@page import="cart.ShoppingCart"%>
<%@ page import="database.BookDetails" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<title>Cart</title>
</head>
<body>
	<%@include file="banner.jsp" %>
	<div class="container">

		<div class="starter-template">
			<%
				ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
				if(cart.getNumberOfItems()>0){
			%>
			<font size="+2">Your shopping cart contains <%=cart.getNumberOfItems() %> items</font><br>&nbsp;

			<div class="table-responsive">
				<table class="table tablle-hover">
					<thead>
						<tr>
							<th>Title</th>
							<th>Quantity</th>
							<th>#</th>
						</tr>
					</thead>
					<tbody>
						<%
							for(ShoppingCartItem item:cart.getItems()){
						%>
						<tr>
							<td>
								<a href="<%=request.getContextPath()%>/bookdetails?bookId=<%=item.getItem().getBookId() %>"><%=item.getItem().getTitle()%></a>
							</td>
							<td><%=item.getQuantity() %></td>
							<td>
								<a class="btn btn-primary" href="<%=request.getContextPath()%>/showcart?Remove=<%=item.getItem().getBookId() %>">Remove Item</a>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
			<%
				}else{
				    %>
					<div class="jumbotron">
						<h1>Oops!</h1>
						<p>Your cart is empty</p>
					</div>
					<%
				}
			%>
			<%if(cart.getNumberOfItems()>0){%>
			<a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/cashier">Buy Your Books</a>&nbsp;&nbsp;&nbsp;
			<%}%>
			<a class="btn btn-primary btn-lg" href="<%=request.getContextPath() %>/catalog"> Continue Shopping</a>
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