package com.tfyy.Pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidPoolTest {
        /*
            硬编码:将连接池的配置信息和Java耦合在一起
            1.创建DruidDataSource连接池对象
            2.设置连接池的配置信息【必须|非必需】
            3.通过连接池获取连接对象
            4.回收连接【不是释放连接，而是将连接归还给连接池，给其他线程复用】
         */
    public void DruidPoolConfig(){
        //1.创建DruidDataSource连接池对象
        DruidDataSource druidDataSource = new DruidDataSource();

        //2.设置连接池的配置信息【必须|非必需】
        //2.1 必须配置
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/xxl_job_demo");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("admin");
        //2.2 非必须配置
        druidDataSource.setInitialSize(10);
        druidDataSource.setMaxActive(20);

        //3.通过连接池获取连接对象
        try {
            DruidPooledConnection connection = druidDataSource.getConnection();
            System.out.println(connection);
            //基于connection进行CRUD
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //回收连接
        druidDataSource.clone();
    }

    /*
        软编码:将配置文件写到propeties中进行加载
        1.创建Properties集合，用于存储外部配置文件的key和values
        2.读取外部配置文件，获取输入流，加载到Properties
        3.基于Properties集合构建DruidDataSource连接池
        4.通过连接池获取连接对象
        5.CRUD
        6.回收连接
     */
    public void getDruidConnection() {

        DataSource dataSource = null;
        Connection connection = null;
        //1.创建Properties集合，用于存储外部配置文件的key和values
        Properties properties = new Properties();

        //2.读取外部配置文件，获取输入流，加载到Properties
        InputStream resourceAsStream = DruidPoolTest.class.getClassLoader().getResourceAsStream("src/main/resources/db.properties");
        //3.基于Properties集合构建DruidDataSource连接池
        try {
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            //4.通过连接池获取连接对象
            connection = dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        System.out.println(connection);

    }

}
