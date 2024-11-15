package com.tfyy.Senior;

import com.tfyy.Senior.Util.JDBCUtilsV2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    将共性的数据库的操作代码封装在BaseDAO中
 */
public class BaseDao {

    /*
        通用的增删改的方法
        SQL : 要执行的SQL语句
        params : 参数信息
     */
    public int executeUpdate(String sql,Object... params) {
        //1.通过JDBCV2获取数据库连接
        Connection dataSource = JDBCUtilsV2.getDataSource();
        PreparedStatement preparedStatement = null;
        int row = 0;
        //2.预编译SQL语句
        try {
            preparedStatement = dataSource.prepareStatement(sql);
            //4.为占位符赋值，执行SQL，接受返回结果
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    //占位符从1开始，参数时从0开始
                    preparedStatement.setObject(i+1,params[i]);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                //释放资源
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            JDBCUtilsV2.Relase();
        }

        return row;
    }


}
