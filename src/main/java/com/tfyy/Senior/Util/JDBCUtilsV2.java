package com.tfyy.Senior.Util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/*
    Version2.0
    1.维护一个连接池对象，维护了一个线程绑定变量的ThreadLocal对象
    2.对外提供在ThreadLocal中获取连接的方法
    3.对外提供回收连接的方法，回收过程中，将要回收的连接从ThreadLoacl中移除
注意:工具类仅对外提供共性的代码
注意:使用ThreadLoacl就是为了一个线程在多次数据库操作过程中，使用的是同一个连接
 */
public class JDBCUtilsV2 {
    //创建连接池应用，要提供给当前项目的全局使用，创建为静态对象

    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //在项目启动时，创建连接池对象，赋值给dataSource
    static {
        try {
            Properties properties = new Properties();
            InputStream resourceAsStream = JDBCUtilsV2.class.getClassLoader().getResourceAsStream("HikariCp.properties");
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {

        }
    }

    public static Connection getDataSource() {
        Connection connection = null;
        try {
//            connection = dataSource.getConnection();
            Connection conn = threadLocal.get();
            if (conn == null) {
                conn = dataSource.getConnection();
                threadLocal.set(conn);
            }
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void Relase(){
        try {
            //connection.close();
            Connection connection = threadLocal.get();
            if (connection != null) {
                //从ThreadLocal中移除当前已经存储的Connection对象
                threadLocal.remove();
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
