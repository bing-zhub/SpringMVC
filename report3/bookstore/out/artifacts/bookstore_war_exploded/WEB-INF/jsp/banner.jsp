<%@ page language="java" pageEncoding="UTF-8" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/enter">Homepage</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/catalog">Catalog</a></li>
                <li><a href="/showcart">Cart</a></li>
            </ul>

            <ul class="nav navbar-nav pull-right">
                <%String msg=(String)session.getAttribute("username");
                    if(msg!=null && !msg.equals("")){
                %>
                <li><a href="/order"><%=msg%></a></li>
                <li><a href="/logout">Logout</a></li>
                <%
                        if(session.getAttribute("logSucHint")==null || !(boolean)session.getAttribute("logSucHint")){
                            // 登录成功提示
                            out.print("<script language='javaScript'> alert('Login Successfully');</script>");
                            session.setAttribute("logSucHint", true);
                        }
                    }else{
                        // 未登录
                %>

                <li><a href="/login">Login</a></li>
                <%
                    }
                %>
            </ul>

        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <div class="starter-template" style="text-align: center;">

        <h1>
            <font size="+3" color="#CC0066">Duke's </font>
            <img src="<%=request.getContextPath() %>/duke.books.gif">
            <font size="+3" color="black">Bookstore</font>
        </h1>
    </div>

</div>
