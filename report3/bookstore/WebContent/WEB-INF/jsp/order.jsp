<%@page import="cart.ShoppingCartItem"%>
<%@page import="cart.ShoppingCart"%>
<%@ page import="database.BookDetails" %>
<%@ page import="java.util.List" %>
<%@ page import="order.Order" %>
<%@ page import="order.OrderDetail" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <title>Order</title>
</head>
<body>
<%@include file="banner.jsp" %>
<div class="container">

    <div class="starter-template">
        <%
            List<Order> orders = (List<Order>) session.getAttribute("orders");
            if(orders.size()>0){
                for(Order order : orders) {
                %>
                    <h2 class="sub-header">订单号:<%=order.getOrderId()%> &nbsp; 创建时间: <%=order.getCreateAt()%></h2>
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Title</th>
                                <th>Quantity</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for(OrderDetail detail:order.getDetails()){
                            %>
                            <tr>
                                <td>
                                    <a href="<%=request.getContextPath()%>/bookdetails?bookId=<%=detail.getBookId() %>"><%=detail.getBookTitle()%></a>
                                </td>
                                <td><%=detail.getQuantity() %></td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
        <%

                }
        %>


        <%
        }else{
                // 未创建订单
        %>
        <div class="jumbotron">
            <h1>Oops!</h1>
            <p>Your order box is empty</p>
        </div>
        <%
            }
        %>

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