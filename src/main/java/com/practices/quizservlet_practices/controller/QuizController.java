package com.practices.quizservlet_practices.controller;


import com.practices.quizservlet_practices.model.Question;
import com.practices.quizservlet_practices.model.QuizSession;
import com.practices.quizservlet_practices.utils.CSVLoader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuizController", value = "/quiz-controller")
public class QuizController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        List<Question> questionList = CSVLoader.getQuestions();
        QuizSession quizSession = new QuizSession(questionList);
        System.out.println(quizSession);
        if (session != null) {
            session.setAttribute("currentQuestionIndex", quizSession.getCurrentQuestionIndex());
            session.setAttribute("quiz", quizSession);
            session.setAttribute("currentQuestion", quizSession.getCurrentQuestion());
        } else {
            return;
        }

        req.getRequestDispatcher("quiz.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] reqCookies = req.getCookies();
    }
}
