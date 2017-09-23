<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="moc.oreh.domain.*" %>
<%@ page import="java.util.LinkedList" %>
<html>
<head>
    <title>结果展示</title>
</head>
<script type="application/javascript">
    var res = 123;
</script>
<body>
<h1>天王盖地虎</h1>
<%! LinkedList<PhisicalPayConfig> list = null; %>
<%
     list = (LinkedList<PhisicalPayConfig>) request.getAttribute("list");
%>
<% for(int i = 0; i < list.size(); i++) {%>
<%= list.get(i).getFieldCode()%><br/>
<%= list.get(i).getFieldName()%><br/>
<%}%>
<%= request.getAttribute("username")%>
</body>
</html>
