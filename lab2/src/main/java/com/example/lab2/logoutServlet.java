package com.example.lab2;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class logoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * This invalidates the cookies and ending the session
     * as cookies don't have any invalidate method so, we need to set the
     * lifetime to 0 to invalidate it.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie [] cookieArray = request.getCookies();
        Cookie tempCookie = null;
        for (Cookie c:cookieArray)
        {
            if (c.getName().equals("usernameKey"))
                tempCookie = c;
        }

        if (tempCookie!=null)
        {
            tempCookie.setMaxAge(0); //since cookie does not have any
            // invalidate method like HttpSession object, we need to
            // invalidate it by setting its lifetime to 0.
        }
        PrintWriter out = response.getWriter();
        out.println("<h1> Successfully logged out </h1>");

        RequestDispatcher rd = request.getRequestDispatcher("index.html");
        rd.include(request, response);
    }
}
