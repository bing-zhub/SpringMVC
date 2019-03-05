<%--
  Created by IntelliJ IDEA.
  User: bing
  Date: 2019/3/5
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>${{msg}}</h1>
<form action="/register" method="post">
    <p>姓名: <input type="text" name="name" placeholder="${name}"></p>
    <p>性别: <input type="radio" name="gender" value="male" ${male}>男</input>
            <input type="radio" name="gender" value="female" ${female}>女</input>
    </p>
    <p>联系方式: <input type="tel" name="tel" placeholder="${tel}"></p>
    <input type="submit" value="${button}" ${btn_attr}/>
</form>

</body>
</html>
