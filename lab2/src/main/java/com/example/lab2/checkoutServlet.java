package com.example.lab2;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

public class checkoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * in this method
     * we clear all the data i.e. reset the quantity value to 0 for
     * all the items in the database
     * and then redirect to the loginSuccessfulServlet
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "jdbc:mysql://localhost:3306/ShopCart";
        String username = "root";
        String password = "****";


        try {
            PrintWriter out = response.getWriter();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("UPDATE ShopCart.new_table SET quantity = 0 WHERE Name = 'Sajid' and itemName = 'Good Food'");
            resultSet = statement.executeQuery("UPDATE ShopCart.new_table SET quantity = 0 WHERE Name = 'Sajid' and itemName = 'Good Grades'");
            resultSet = statement.executeQuery("UPDATE ShopCart.new_table SET quantity = 0 WHERE Name = 'Sajid' and itemName = 'Good Mood'");

            out.println("<h1>You have successfully Checked out");

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        RequestDispatcher rd = request.getRequestDispatcher("loginSuccessfulServlet");
        rd.include(request, response);
    }
}
