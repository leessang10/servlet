<%--
  Created by IntelliJ IDEA.
  User: SANGMOO
  Date: 2022-05-03
  Time: 오전 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>회원 가입</h1>
<form action="/jsp/members/save.jsp" method="post">
    username: <input type="text" name="username"/>
    age:      <input type="text" name="age"/>
    <button type="submit">요청</button>
</form>
<a href="/index.html">메인으로</a>
</body>
</html>
