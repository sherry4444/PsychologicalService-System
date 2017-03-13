<%--
  Created by IntelliJ IDEA.
  User: wgt
  Date: 2017/3/10
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>test</title>
</head>

<body>
    <form action="<c:url value='/userInfo/add'/> " method="post">
        用户名：<input type="text" name="userName">
        密码：<input type="password" name="Password">
        <input type="submit" value="测试提交">
    </form>
</body>
</html>
