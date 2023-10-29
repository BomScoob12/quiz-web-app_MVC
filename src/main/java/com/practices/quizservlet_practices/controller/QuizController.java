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
        HttpSession session = req.getSession();
        if (session == null) {
            // Handle the case where there's no session (e.g., session expired)
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }
        List<Question> questionList = CSVLoader.getQuestions();
        QuizSession quizSession = new QuizSession(questionList);

        session.setAttribute("quiz", quizSession);
        session.setAttribute("currentQuestionIndex", quizSession.getCurrentQuestionIndex());
        session.setAttribute("currentQuestion", quizSession.getCurrentQuestion());

        req.getRequestDispatcher("quiz.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            // Handle the case where there's no session (e.g., session expired)
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }

        QuizSession quizSession = (QuizSession) session.getAttribute("quiz");
        String action = req.getParameter("action");

        if (req.getParameter("nextButton") != null && !quizSession.isQuizOver()) {
            int selectedOption = Integer.parseInt(req.getParameter("selectedOption"));
            quizSession.checkAnswer(selectedOption);
            quizSession.moveToNextQuestions();
        } else if (req.getParameter("submitButton") != null) {
            session.setAttribute("getAnswerCount", quizSession.getCorrectAnswerCount());
            Cookie cookie = new Cookie("scoreHistory", String.valueOf(quizSession.getCorrectAnswerCount()));
            cookie.setMaxAge(6400);
            resp.addCookie(cookie);
            req.getRequestDispatcher("result.jsp").forward(req, resp);
        } else if (req.getParameter("resetButton") != null) {
            session.invalidate();
            resp.sendRedirect("index.jsp");
            return;
        } else {
            resp.sendRedirect("index.jsp");
            return;
        }

        // Update session attributes
        session.setAttribute("quiz", quizSession);
        session.setAttribute("currentQuestionIndex", quizSession.getCurrentQuestionIndex());
        session.setAttribute("currentQuestion", quizSession.getCurrentQuestion());

        req.getRequestDispatcher("quiz.jsp").forward(req, resp);
    }

}
