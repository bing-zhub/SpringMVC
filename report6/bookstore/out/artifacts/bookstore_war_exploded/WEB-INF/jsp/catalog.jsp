<%@page import="java.util.Collection"%>
<%@page import="cart.ShoppingCart"%>
<%@page import="bean.BookDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<title>Book Catalog</title>
</head>
<body>
	<%@include file="banner.jsp" %>
	<div class="container">
		<div class="starter-template">


			<h3>Please choose from our selections:</h3>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Inventory</th>
                            <th>Author</th>
                            <th>#</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            Collection<BookDetails> books=(Collection<BookDetails>) request.getAttribute("books");
                            for(BookDetails book:books){
                                String titleHref = request.getContextPath()+ "/bookdetails?bookId=" +book.getBookId();
                                String addHref = request.getContextPath()+ "/showcart?Add=" +book.getBookId();

                        %>
                        <tr>
                            <td >
                                <a href="<%=titleHref%>"> <%=book.getTitle()%> </a>
                            </td>
                            <td>
                                <%=book.getInventory() %>
                            </td>
                            <td> <em> <%=book.getFirstName() %> <%=book.getSurname() %></em></td>
                            <td>
                                <a class="btn btn-primary" href="<%=addHref%>"> Add to Cart</a>
                            </td>
                        </tr>
                        <tr>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <%
                    BookDetails bd=(BookDetails)request.getAttribute("bd");
                    if(bd!=null){
                %>
                <p><h3><font color="#ff0000">ou added <i><%=bd.getTitle() %></i> to your shopping cart.</font></h3>
                <%
                    }
                    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                    if(cart!=null && cart.getNumberOfItems()>0){
                %>
                <a class="btn btn-primary" href="<%=request.getContextPath()%>/showcart">Check Shopping Cart</a>
                <%
                    }
                %>
            </div>
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