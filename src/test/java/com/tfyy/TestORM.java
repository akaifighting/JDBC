package com.tfyy;

import com.tfyy.Dao.Employee;
import org.junit.Test;

import java.sql.*;

/**
 * @title: TestORM
 * @Author WangKaiPeng
 * @Date: 2024/11/13 23:16
 * @Version 1.0
 */
public class TestORM {
    @Test
    public void querySingleRow() throws SQLException {

        //1.注册驱动
//        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigudb?useSSL=false", "root","admin");

        //3.创建PreparedStatement对象，并预编译SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement("select * from employees where employee_id = ?");
        preparedStatement.setInt(1, 105);
        //4.执行SQL语句，获取结果
        ResultSet resultSet = preparedStatement.executeQuery();
        Employee employee = null;
        while (resultSet.next()){
            int id = resultSet.getInt("employee_id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            double salary = resultSet.getDouble("salary");
            employee = new Employee(id, first_name, last_name, email, salary);

        }
        System.out.println(employee);
    }
}
