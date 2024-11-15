package com.tfyy.Senior.Util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/*
    1.维护一个连接池对象
    2.对外提供在连接池中获取连接的方法
    3.对外提供回收连接的方法
注意:工具类仅对外提供共性的代码
 */
public class JDBCUtils {
    //创建连接池应用，要提供给当前项目的全局使用，创建为静态对象

    private static DataSource dataSource;

    //在项目启动时，创建连接池对象，赋值给dataSource
    static {
        try {
            Properties properties = new Properties();
            InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("HikariCp.properties");
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
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    public static void Relase(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
