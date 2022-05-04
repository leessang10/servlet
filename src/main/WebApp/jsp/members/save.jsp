<%@ page import="sangmu.servlet.domain.member.MemberRepository" %>
<%@ page import="sangmu.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: SANGMOO
  Date: 2022-05-03
  Time: 오전 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // response, resquest 사용 가능
    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>회원 등록 성공</h1>
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인으로</a>
</body>
</html>
