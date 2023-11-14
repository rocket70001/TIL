<%--
  Created by IntelliJ IDEA.
  User: a42
  Date: 11/13/23
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>List Page</h1>
    <c:forEach var="dto" items="${list}">
        <li>${dto}</li>
    </c:forEach>
</body>
</html>
