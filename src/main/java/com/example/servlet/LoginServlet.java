package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    //write your code here!

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("user") == null) {
            resp.sendRedirect("/login.jsp");
        } else {
            resp.sendRedirect("/user/hello.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Users.getInstance().getUsers().contains(req.getParameter("login"))
                && !String.valueOf(req.getParameter("password")).isEmpty()) {
            System.out.println("TRUE");
            req.getSession().setAttribute("user", req.getParameter("login"));
            resp.sendRedirect("/user/hello.jsp");
        } else {
            System.out.println("FALSE");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
