package com.example.lab2;

import javax.print.DocFlavor;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class loginSuccessfulServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie [] cookieArray = request.getCookies();
        PrintWriter out = response.getWriter();

        // flag is initialized in case the session is invalid
        int flag = 0;

        for (Cookie c:cookieArray)
        {
            if (c.getName().equals("usernameKey"))
            {
                //here the session is valid

                // Here the items currently present in the Cart is shown From the database using the showCartServlet
                RequestDispatcher rd = request.getRequestDispatcher("showCartServlet");
                rd.include(request, response);

                flag = 1;

                out.println("<h1> Welcome! </h1>");

                // Button to access the adding to cart Feature
                out.println("<h2> To Add Items to the Cart, please press the following button.</h2>");
                out.println("<br>\n" +
                        "<form method=\"post\" action=\"addToCartServlet\"> <input" +
                        " type=\"submit\" value=\"Add Items\"> ");

                // Button to access the removing From cart Feature
                out.println("<h2> To Remove Items From the Cart, please press the following button.</h2>");
                out.println("<br>\n" +
                        "<form method=\"post\" action=\"removeFromCartServlet\"> <input" +
                        " type=\"submit\" value=\"Remove Items\"> ");

                // Button to checkout
                out.println("<h2> To Check out, please press the following button.</h2>");
                out.println("<br>\n" +
                        "<form method=\"post\" action=\"checkoutServlet\"> <input" +
                        " type=\"submit\" value=\"check out\"> ");

                // Button to logout
                out.println("<h2> To log out, please press the following button.</h2>");
                out.println("<br>\n" +
                        "<form method=\"post\" action=\"logoutServlet\"> <input" +
                        " type=\"submit\" value=\"Log out\"> ");
            }

        }
        if (flag == 0)
        {   // No cookies were matched so the session was deemed invalid
            out.println("<h1> Invalid session. Please log in again. " +
                    "</h1>");
            RequestDispatcher rd = request.getRequestDispatcher(
                    "index.html");
            rd.include(request, response);
        }
    }
}