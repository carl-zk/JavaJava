<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="moc.oreh.domain.*" %>
<%@ page import="java.util.LinkedList" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>fuck jsp</title>
</head>

<script>
    function fun() {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/controller/hello", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send("param=王二狗");
        xhr.onreadystatechange=function () {
            if(xhr.readyState==4 && xhr.status==200){
                //alert(xhr.responseText);
            }
        }
    }
</script>

<body>
<h1>同步调用,刷新页面</h1>
<%! LinkedList<PhisicalPayConfig> list = null; %>
<%
    list = (LinkedList<PhisicalPayConfig>) request.getAttribute("list");
    if(list != null)out.println(list.size());
    else out.println("list is null");
%>

<%= request.getAttribute("username")%>
<form method="post" action="/controller/hello">
    <input type="text" id="param" name="param" value="222"/><br/>
    <button type="submit" value="提交" name="sub">提交</button>
</form>
<% if(list != null) { %>
<% for(int i = 0; i < list.size(); i++) {%>
<%= list.get(i).getFieldCode()%><br/>
<%= list.get(i).getFieldName()%><br/>
<%}}%>


<br/>
<h1>异步调用</h1>
<input type="button" name="bu" value="点击" onclick="fun()"/>
</body>
</html>