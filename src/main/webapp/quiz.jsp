<%--
  Created by IntelliJ IDEA.
  User: soraw
  Date: 10/27/2023
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
>
<html>
<head>
    <title>QUIZZES</title>
</head>
<body>
<h1>Quiz</h1>
<%--${questions is scope of request}--%>
<jsp:useBean id="questions" scope="request" type="java.util.List"/>
<c:choose>
    <c:when test="${questions == null}">
        Questions not found.
    </c:when>
    <c:otherwise>
        <c:forEach items="${questions}" var="question" varStatus="count">
            ${count.index+1}. ${question.getQuestionText()} <br>
            <form action="quiz-controller" method="post">
                <c:forEach items="${question.getOptions()}" var="option">
                    <label>
                        <input type="radio" name="option" value="${option}">
                    </label> ${option} <br>
                </c:forEach>
                <br>
                <input type="submit" value="Save">
            </form>
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
