package com.example.lab2;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Name and password is being hardcoded
        if (username.equals("Sajid") && password.equals("1234"))
        {
            Cookie usernameCookie = new Cookie("usernameKey", username);
            response.addCookie(usernameCookie);

            PrintWriter out = response.getWriter();
            out.println("<h1> Successfully logged in </h1>");

            //including the loginSuccessfulServlet to get access to all the features available
            RequestDispatcher rd = request.getRequestDispatcher(
                    "loginSuccessfulServlet");
            rd.include(request, response);
        }
        else
        {
            PrintWriter out = response.getWriter();
            out.println("<h1> Wrong credentials </h1>");

            RequestDispatcher rd = request.getRequestDispatcher(
                    "index.html");
            rd.include(request, response);
        }
    }
}
