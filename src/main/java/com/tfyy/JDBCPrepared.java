package com.tfyy;

import java.sql.*;
import java.util.Scanner;

/**
 * @title: JDBCPrepared
 * @Author WangKaiPeng
 * @Date: 2024/11/13 22:32
 * @Version 1.0
 */
public class JDBCPrepared {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/atguigudb?useSSL=false";
        String user = "root";
        String password = "admin";
        Connection connection = DriverManager.getConnection(url, user, password);
//        System.out.println(connection);
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        //执行SQL语句的对象
        String SQL = "select * from employees where first_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        //填充占位符
        preparedStatement.setString(1,s);
        //执行SQL语句并接受返回结果
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            double salary = resultSet.getDouble("salary");
            System.out.println(firstName + "  " + lastName + " " + email + " " + salary);
        }

        //先开后关原则
        connection.close();
        preparedStatement.close();
        resultSet.close();

    }
}
