<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<html>
<body>
<h2>Hello World!</h2>
<%

%>
<fieldset>
    <legend>我是登录框</legend>
    <form action="controller/identity/user/get?id=2" target="_blank">
        <input type="text" name="id" value="2" /> <br/>
            用户名: <input type="text" name="loginName" /> <br>
        密&nbsp;码: <input type="password" name="password" /> <br>
        <input type="submit" value="登录">
    </form>
</fieldset>
</body>
</html>
