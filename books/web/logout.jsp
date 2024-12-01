<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta HTTP-EQUIV="refresh" CONTENT="3;URL=login.jsp">
    <title>退出</title>
</head>
<body>
<%
    if (session != null)
    {
        session.invalidate();
    }
%>
<p style="font-size:18px">退出成功，页面将于3s后自动跳转
    <a style="font-size:12px" href="/books/login.jsp">点我跳转</a></p>
</body>
</html>