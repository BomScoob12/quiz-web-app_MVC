<%--
  Created by IntelliJ IDEA.
  User: soraw
  Date: 10/27/2023
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>QUIZZES</title>
</head>
<body>
<h1>Quiz</h1>
<c:choose>
    <c:when test="${sessionScope.quiz == null}">
        Questions not found.
    </c:when>
    <c:otherwise>
        <form action="quiz-controller" method="post">
            <c:out value="${sessionScope.currentQuestionIndex}" default="0"/>
            <h1>${sessionScope.currentQuestion.getQuestionText()}</h1>
            <c:forEach items="${sessionScope.currentQuestion.getOptions()}" var="option" varStatus="ct">
                <input type="radio" value="${ct.index}" name="selectedOption"> ${option} <br>
            </c:forEach>
            <br>
            <input type="submit" name="resetButton" value="Reset">
            <c:choose>
                <c:when test="${sessionScope.currentQuestionIndex < sessionScope.quiz.getQuestions().size() - 1}">
                    <input type="submit" name="nextButton" value="Next">
                </c:when>
                <c:otherwise>
                    <!-- No "Next" button on the last question and index = length - 1 -->
                    <input type="submit" name="submitButton" value="Submit">
                </c:otherwise>
            </c:choose>
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>
