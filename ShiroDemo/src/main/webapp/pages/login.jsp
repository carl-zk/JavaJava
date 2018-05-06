<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<meta http-equiv="Content-Type" content="text/html charset=UTF-8">
<head>
   登陆页
</head>
<body>
<form method="post" action="/login">
    Username: <input type="text" name="username"/> <br/>
    Password: <input type="password" name="password"/> <br/>
    <input type="checkbox" name="rememberMe" value="true"/>Remember Me? <br/>
    <input type="submit" value="登陆"/>
</form>
</body>
</html>