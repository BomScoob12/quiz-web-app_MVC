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
        for (int i = 0; i<cookies.length; i++){
            if(cookies[i].getName().equals("scoreHistory")){
                String name = cookies[i].getName();
                String value = cookies[i].getValue();
                req.setAttribute("scoreHistoryName", name);
                req.setAttribute("scoreHistory", value);
            }
        }
        req.getRequestDispatcher("scoreHistory.jsp").forward(req, resp);
    }
}
