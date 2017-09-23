<html>
<%
    String msg = (String) request.getAttribute("hello");
    Cookie[] cookies = request.getCookies();
    String name = "", password = "";
    if(null != cookies){
        for(Cookie cookie : cookies){
            if("name".equals(cookie.getName()))
                name = cookie.getValue();
            if("password".equals(cookie.getName()))
                password = cookie.getValue();
        }
    }
%>
<body>
<h2>test cookie</h2>
</body>
msg=<%= msg%>

<form name="login" method="POST" action="/cookie" content="text/html;charset=utf-8">
    loginName: <input name="name" type="text" value="<%= name%>"/><br/>
    password: <input name="password" type="text" value="<%= password%>"><br/>
    <input type="submit" value="login">
</form>

<h3>cookie list</h3>
<table>
    <tr>
        <th>Name</th>
        <th>Value</th>
        <th>Path</th>
    </tr>
    <% for(Cookie cookie : cookies){%>
    <tr>
        <td><%= cookie.getName()%></td>
        <td><%= cookie.getValue()%></td>
        <td><%= cookie.getPath()%></td>
    </tr>
    <%}%>
</table>
</html>
