package com.example.lab2;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class addToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * This method will first show the current items in the Cart
     * Then we initialized the quantity to be added to 0
     * and then taking the inputs to be added and their quantity
     * we are taking to the addingToCartServlet where the database manipulation is done

     **/

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookieArray = request.getCookies();
        PrintWriter out = response.getWriter();

        int flag = 0;

        for (Cookie c : cookieArray) {
            if (c.getName().equals("usernameKey")) {

                flag = 1;
                out.println("<h1> Welcome! </h1>");

                // showing the current items in the cart
                RequestDispatcher rd = request.getRequestDispatcher("showCartServlet");
                rd.include(request, response);

                // initializing the quantities of the item to 0
                int item1 = 0;
                int item2 = 0;
                int item3 = 0;

                /**
                 * here a form is created with checkbox and corresponding quantity to add the items
                 * which when the submit button is pressed we are taking the addingToCartServlet where
                 * the database manipulation is done.
                 */

                out.println("<br>");
                out.println("Add your Desired Products and Their Quantity");
                out.println("<form action=\"addingToCartServlet\" method=\"post\">\n" +
                        "Good Food<input type=\"checkbox\" name=\"Good Food\">\n" + "<br>\n" +
                        "Quantity<input type=\"number\" min=\"0\" name=\"item1\" placeholder= " + item1 + ">\n" + "<br><br>\n" +

                        "Good Grades<input type=\"checkbox\" name=\"Good Grades\">\n" + "<br>\n" +
                        "Quantity<input type=\"number\" min=\"0\" name=\"item2\" placeholder= " + item2 + ">\n" + "<br><br>\n" +

                        "Good Mood<input type=\"checkbox\" name=\"Good Mood\">\n" + "<br>\n" +
                        "Quantity<input type=\"number\" min=\"0\" name=\"item3\" placeholder= " + item3 + ">\n" + "<br><br>\n" +

                        "  <input type=\"submit\" value=\"Add to Cart\">\n" +
                        "</form>");

                out.println("<h2> To log out, please press the following button.</h2>");
                out.println("<br>\n" +
                        "<form method=\"post\" action=\"logoutServlet\"> <input" +
                        " type=\"submit\" value=\"Log out\"> ");

            }
        }

        if (flag == 0)
        {
            out.println("<h1> Invalid session. Please log in again. " +
                    "</h1>");
            RequestDispatcher rd = request.getRequestDispatcher(
                    "index.html");
            rd.include(request, response);
        }
    }
}