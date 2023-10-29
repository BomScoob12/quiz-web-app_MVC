package com.practices.quizservlet_practices.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "QuizHistory", value="/quiz-history")
public class QuizHistoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("scoreHistory")) {
                String name = cookie.getName();
                String value = cookie.getValue();
                req.setAttribute("scoreHistoryName", name);
                req.setAttribute("scoreHistory", value);
            }
        }
        req.getRequestDispatcher("scoreHistory.jsp").forward(req, resp);
    }
}
