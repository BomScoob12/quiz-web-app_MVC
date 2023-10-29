<%--
  Created by IntelliJ IDEA.
  User: soraw
  Date: 10/29/2023
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ScoreHistory</title>
</head>
<body>
<div>${requestScope.scoreHistoryName}</div>
<div>Score is : ${requestScope.scoreHistory}</div>
<a href="index.jsp">Back to index</a>
</body>
</html>
