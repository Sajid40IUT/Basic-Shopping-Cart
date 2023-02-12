package com.example.lab2;

import javax.servlet.*;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class removingFromCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * in this method the database manipulation is done
     * mainly the updating of the quantity value and also checking is done that
     * no invalid removal (quantity less than 0) is done or not
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // the quantity of the corresponding items
        int item1Quantity, item2Quantity, item3Quantity;

        try {
            item1Quantity = Integer.parseInt(request.getParameter("item1"));
        } catch (NumberFormatException nfe) {
            item1Quantity = 0;
        }

        try {
            item2Quantity = Integer.parseInt(request.getParameter("item2"));
        } catch (NumberFormatException nfe) {
            item2Quantity = 0;
        }

        try {
            item3Quantity = Integer.parseInt(request.getParameter("item3"));
        } catch (NumberFormatException nfe) {
            item3Quantity = 0;
        }

        // database connection
        String url="jdbc:mysql://localhost:3306/ShopCart";
        String username="root";
        String password="****";


        try {
            PrintWriter out = response.getWriter();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();

            // here the res variable will contain the final quantity in each case
            int res = 0;

            /**
             * updating the first Item
             * here first we take the current quantity from the Database into res and then
             * change the values res by subtracting i.e. removing the desired quantity
             * here a check is made with error message is res i.e. quantity < 0
             * the same task is done for all three items in our list
             */

            ResultSet resultSet = statement.executeQuery("SELECT quantity FROM ShopCart.new_table WHERE Name = 'Sajid' and itemName = 'Good Food'");

            while (resultSet.next()){
                res = resultSet.getInt(3);
            }
            // res is
            res -= item1Quantity;

            if (res < 0)
            {
                out.println("<h1>Invalid Removal</h1>");
                RequestDispatcher rd = request.getRequestDispatcher("loginSuccessfulServlet");
                rd.include(request, response);
            }
            else
            {
                resultSet = statement.executeQuery("UPDATE ShopCart.new_table SET quantity = res WHERE Name = 'Sajid' and itemName = 'Good Food'");
            }


            //updating the Second Item
            resultSet = statement.executeQuery("SELECT quantity FROM ShopCart.new_table WHERE Name = 'Sajid' and itemName = 'Good Grades'");

            while (resultSet.next()){
                res = resultSet.getInt(3);
            }
            res -= item2Quantity;

            if (res < 0)
            {
                out.println("<h1>Invalid Removal</h1>");
                RequestDispatcher rd = request.getRequestDispatcher("loginSuccessfulServlet");
                rd.include(request, response);
            }
            else
            {
                resultSet = statement.executeQuery("UPDATE ShopCart.new_table SET quantity = res WHERE Name = 'Sajid' and itemName = 'Good Grades'");
            }


            //updating the Third Item
            resultSet = statement.executeQuery("SELECT quantity FROM ShopCart.new_table WHERE Name = 'Sajid' and itemName = 'Good Mood'");

            while (resultSet.next()){
                res = resultSet.getInt(3);
            }
            res -= item1Quantity;

            if (res < 0)
            {
                out.println("<h1>Invalid Removal</h1>");
                RequestDispatcher rd = request.getRequestDispatcher("loginSuccessfulServlet");
                rd.include(request, response);
            }
            else
            {
                resultSet = statement.executeQuery("UPDATE ShopCart.new_table SET quantity = res WHERE Name = 'Sajid' and itemName = 'Good Mood'");
            }

            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        /**
         * Finally if all the updates i.e. the removal are valid then we can
         * go back to our loginSuccessfulServlet
         */

        RequestDispatcher rd = request.getRequestDispatcher("loginSuccessfulServlet");
        rd.forward(request, response);

    }
}
