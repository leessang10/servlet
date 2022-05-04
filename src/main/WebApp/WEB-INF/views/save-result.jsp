<%@ page import="sangmu.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: SANGMOO
  Date: 2022-05-04
  Time: 오전 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>회원 등록 성공</h1>
<ul>
  <li>id=${member.id}</li>
  <li>username=${member.username}</li>
  <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인으로</a>
</body>
</html>
