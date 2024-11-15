package com.tfyy.Pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariCPPoolTest {

    /*
        1.创建HikariDataSource连接池配置
        2.设置连接池的配置信息
        3.通过连接池获取连接对象
        4.回收连接
     */
    public void getHikariCP(){
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/xxl_job_demo");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("admin");
        hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        //非必须设置
        hikariDataSource.setMinimumIdle(10);
        hikariDataSource.setMaximumPoolSize(20);

        //通过连接池获取对象
        try {
            Connection connection = hikariDataSource.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

        /*
        通过动态方式获取连接对象:将配置文件写到propeties中进行加载
        1.创建Properties集合，用于存储外部配置文件的key和values
        2.读取外部配置文件，获取输入流，加载到Properties
        3.创建HikariCP连接池配置对象，将Properties集合传进去
        4.基于HikariConfig连接池配置对象，构建HikariDataSource
        5.获取连接
        6.回收连接
     */
    public void getHikariCP2(){
        // 1.创建Properties集合，用于存储外部配置文件的key和values
        Properties properties = new Properties();

        //2.读取外部配置文件，获取输入流，加载到Properties
        InputStream resourceAsStream = HikariCPPoolTest.class.getClassLoader().getResourceAsStream("HikariCp.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //3.创建HikariCP连接池配置对象，将Properties集合传进去
        HikariConfig hikariConfig = new HikariConfig(properties);
        //4.基于HikariConfig连接池配置对象，构建HikariDataSource
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        Connection connection = null;

        //关闭连接
        try {
            //5.创建连接
            connection = hikariDataSource.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
