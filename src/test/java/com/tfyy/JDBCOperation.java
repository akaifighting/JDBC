package com.tfyy;

import org.junit.Test;

import java.sql.*;
import java.time.Instant;

/**
 * @title: JDBCTest
 * @Author WangKaiPeng
 * @Date: 2024/11/13 22:41
 * @Version 1.0
 */
public class JDBCOperation {



    @Test
    public void querySingleRowAndColumn() throws SQLException {
        //1.注册驱动
//        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigudb?useSSL=false", "root","admin");

        //3.创建PreparedStatement对象，并预编译SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement("select count(*) as count from employees");

        //4.执行SQL语句，获取结果
        ResultSet resultSet = preparedStatement.executeQuery();

        //5.处理结果
        while (resultSet.next()){
            int count = resultSet.getInt("count");
            System.out.println("count = " + count);
        }

        //6.释放资源(先开后关原则)
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }


    @Test
    public void querySingleRow() throws SQLException {

        //1.注册驱动
//        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigudb?useSSL=false", "root","admin");

        //3.创建PreparedStatement对象，并预编译SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement("select * from employees");

        //4.执行SQL语句，获取结果
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt("employee_id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            double salary = resultSet.getDouble("salary");
            System.out.println("employee_id = " + id + "first_name = " + first_name + " last_name = " + last_name + " email = " + email + " salary = " + salary);
        }

    }


    @Test
    public void insert() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigudb?useSSL=false", "root","admin");
        //3.创建Statement对象
        //新增
//        PreparedStatement preparedStatement = connection.prepareStatement("insert into course(course_id,course_name) values (?,?)");
        //修改
//        PreparedStatement preparedStatement = connection.prepareStatement("update course set course_name = ? where course_id = ?");

        //删除
        PreparedStatement preparedStatement = connection.prepareStatement("delete from course where course_id = ?");
//        preparedStatement.setString(1,"zhangsan888");
        preparedStatement.setInt(1,999);

        int result = preparedStatement.executeUpdate();
        if (result > 0){
            System.out.println("执行 success");
        }
        else {
            System.out.println("执行 failed");
        }

        connection.close();
        preparedStatement.close();
    }
}
