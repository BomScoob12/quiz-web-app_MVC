<%--
  Created by IntelliJ IDEA.
  User: soraw
  Date: 10/27/2023
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<h1>Congratulations</h1>
<div>You got : ${sessionScope.getAnswerCount}</div>
<a href="./index.jsp">Back to index</a>
</body>
</html>
