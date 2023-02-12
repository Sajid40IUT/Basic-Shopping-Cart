package com.example.lab2;

import javax.servlet.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class addingToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * in this method the database manipulation is done
     * mainly the updating of the quantity value i.e. adding the quantity from the form
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();

            int res = 0;

            /**
             * updating the  Items
             * here first we take the current quantity from the Database into res and then
             * change the values res by adding i.e. adding the desired quantity
             */

            //updating the first Item
            ResultSet resultSet = statement.executeQuery("SELECT quantity FROM ShopCart.new_table WHERE Name = 'Sajid' and itemName = 'Good Food'");

            while (resultSet.next()){
                res = resultSet.getInt(3);
            }
            res += item1Quantity;

            resultSet = statement.executeQuery("UPDATE ShopCart.new_table SET quantity = res WHERE Name = 'Sajid' and itemName = 'Good Food'");


            //updating the Second Item
            resultSet = statement.executeQuery("SELECT quantity FROM ShopCart.new_table WHERE Name = 'Sajid' and itemName = 'Good Grades'");

            while (resultSet.next()){
                res = resultSet.getInt(3);
            }
            res += item2Quantity;

            resultSet = statement.executeQuery("UPDATE ShopCart.new_table SET quantity = res WHERE Name = 'Sajid' and itemName = 'Good Grades'");

            //updating the Third Item
            resultSet = statement.executeQuery("SELECT quantity FROM ShopCart.new_table WHERE Name = 'Sajid' and itemName = 'Good Mood'");

            while (resultSet.next()){
                res = resultSet.getInt(3);
            }
            res += item1Quantity;

            resultSet = statement.executeQuery("UPDATE ShopCart.new_table SET quantity = res WHERE Name = 'Sajid' and itemName = 'Good Mood'");

            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        /**
         * Finally if all the updates i.e. the addition are valid then we can
         * go back to our loginSuccessfulServlet
         */

        RequestDispatcher rd = request.getRequestDispatcher("loginSuccessfulServlet");
        rd.forward(request, response);

    }
}
