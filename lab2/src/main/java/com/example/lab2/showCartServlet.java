package com.example.lab2;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class showCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * This Method simply prints the current
     * quantity of each of the item from the database
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println("<h3>The Shopping cart has the following Items: ");

        String url="jdbc:mysql://localhost:3306/ShopCart";
        String username="root";
        String password="****";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();

            out.println("Connections is successful");

            ResultSet rs = statement.executeQuery("SELECT * FROM ShopCart.new_table WHERE name = 'Sajid' and itemName = 'Good Food'");
            while (rs.next()){
                out.println("Good Food: " + rs.getInt(3) + "x" + "<br>");
            }


            ResultSet rs2 = statement.executeQuery("SELECT * FROM ShopCart.new_table WHERE name = 'Sajid' and itemName = 'Good Grades'");
            out.println("Good Food: " + rs2.getInt(3) + "x" + "<br>");

            ResultSet rs3 = statement.executeQuery("SELECT * FROM ShopCart.new_table WHERE name = 'Sajid' and itemName = 'Good Mood'");
            out.println("Good Food: " + rs3.getInt(3) + "x" + "<br>");

            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
}
