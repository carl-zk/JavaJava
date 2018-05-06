<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.apache.shiro.subject.Subject" %>
<%@ page import="user.User" %>
<%@ page import="java.util.LinkedHashSet" %>
<html>
<%
    Subject subject = SecurityUtils.getSubject();
    User user = (User) subject.getPrincipal();
    Set<String> roles = user.getRoles();
    if(roles == null)
        roles = new LinkedHashSet<>(1);
%>
<body>
<h3>用户信息</h3>
user: <%=user.getName()%> <br/>
sid: <%=session.getId()%>
<br/>
角色列表
<ol>
    <% for (String role : roles) { %>
    <li><%=role%></li>
    <%}%>
</ol>
<a href="/pages/houses/cowshed.jsp">cowshed</a>
<a href="/pages/houses/palace.jsp">palace</a>
</body>
</html>