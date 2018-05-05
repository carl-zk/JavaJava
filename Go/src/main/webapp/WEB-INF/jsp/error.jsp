<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.springframework.util.StringUtils" %>
<html>
<head>
    <title>Error</title>
</head>
<%
    String msg = (String) request.getAttribute("msg");
    msg = StringUtils.isEmpty(msg) ? "oh, please try later." : msg;
%>
<body>
<span style="font-size: medium; color: red; "><%=msg%></span>
</body>
</html>