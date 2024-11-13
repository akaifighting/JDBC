package com.tfyy;

import java.sql.*;
import java.util.Scanner;

/**
 * @title: com.tfyy.JDBCQuick
 * @Author WangKaiPeng
 * @Date: 2024/11/13 22:01
 * @Version 1.0
 */
public class JDBCQuick {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/atguigudb?useSSL=false";
        String user = "root";
        String password = "admin";
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
        //执行SQL语句的对象
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        String SQL = "select * from employees where first_name = '" + s +"'";
        System.out.println(SQL);
        ResultSet resultSet = statement.executeQuery(SQL);
        while (resultSet.next())
        {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            double salary = resultSet.getDouble("salary");
            System.out.println(firstName + "  " + lastName + " " + email + " " + salary);
        }

        //先开后关原则
        connection.close();
        statement.close();
        resultSet.close();

    }
}
