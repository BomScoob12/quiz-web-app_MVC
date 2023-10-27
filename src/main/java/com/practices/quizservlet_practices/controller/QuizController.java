package com.practices.quizservlet_practices.controller;


import com.practices.quizservlet_practices.model.Question;
import com.practices.quizservlet_practices.model.QuizSession;
import com.practices.quizservlet_practices.utils.CSVLoader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuizController", value = "/quiz-controller")
public class QuizController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Question> questionList = CSVLoader.getQuestions();
        QuizSession quizSession = new QuizSession(questionList);
        System.out.println(quizSession);
        req.setAttribute("questions", quizSession.getQuestions());
        req.setAttribute("currentQuestions", quizSession.getCurrentQuestion());
        req.getRequestDispatcher("quiz.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie reqCookies[] = req.getCookies();
    }
}
