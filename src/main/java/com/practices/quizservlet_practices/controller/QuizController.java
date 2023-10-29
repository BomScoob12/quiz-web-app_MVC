package com.practices.quizservlet_practices.controller;


import com.practices.quizservlet_practices.model.Question;
import com.practices.quizservlet_practices.model.QuizSession;
import com.practices.quizservlet_practices.utils.CSVLoader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
            session.setAttribute("quiz", quizSession);
            session.setAttribute("currentQuestionIndex", quizSession.getCurrentQuestionIndex());
            session.setAttribute("currentQuestion", quizSession.getCurrentQuestion());
        } else {
            return;
        }

        req.getRequestDispatcher("quiz.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("nextButton") != null) {
            HttpSession session = req.getSession(false);
            QuizSession quizSession = (QuizSession) session.getAttribute("quiz");
            int selectedOption = Integer.parseInt(req.getParameter("selectedOption"));
            quizSession.checkAnswer(selectedOption);
            quizSession.moveToNextQuestions();
            //update session
            session.setAttribute("quiz", quizSession);
            session.setAttribute("currentQuestionIndex", quizSession.getCurrentQuestionIndex());
            session.setAttribute("currentQuestion", quizSession.getCurrentQuestion());
            //get back to jsp
            req.getRequestDispatcher("quiz.jsp").forward(req, resp);
        } else if (req.getParameter("submitButton") != null) {
            HttpSession session = req.getSession(false);
            QuizSession quizSession = (QuizSession) session.getAttribute("quiz");
            session.setAttribute("answerCount", quizSession.getCorrectAnswerCount());
            req.getRequestDispatcher("result").forward(req, resp);
        }
    }
}
