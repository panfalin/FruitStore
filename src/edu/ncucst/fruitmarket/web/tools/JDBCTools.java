package edu.ncucst.fruitmarket.web.tools;

import java.sql.*;

public class JDBCTools {
    //加载驱动，建立数据库连接
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // 1. 注册数据库的驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2.通过DriverManager获取数据库连接
        String url = "jdbc:mysql://localhost:3306/fruit?serverTimezone=GMT%2B8&useSSL=false";
        String username = "root";    //数据库用户名
        String password = "1234";    //数据库密码
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
    //关闭数据库连接，释放添、删、改操作所使用的资源
    public static void release(Connection conn, Statement stmt){
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
    //关闭数据库连接，释放查询操作所使用的资源
    public static void release(Connection conn, Statement stmt, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        release(conn,stmt);
    }
}
