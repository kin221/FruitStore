package edu.ncucst.fruitweb.utils;

import java.sql.*;

public class JDBCTools {
    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fruit?useSSL=false"
                +"&allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true";//mysql 8.x版本加载驱动格式
        String username="root";
        String password="123456";

        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public static void release(Connection conn, Statement stmt)
    {
        try {
            if(stmt!=null){
                stmt.close();
            }
            stmt=null;
            if(conn!=null){
                conn.close();
            }
            conn=null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void release(Connection conn, Statement stmt, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();
            }
            rs=null;
            if(stmt!=null){
                stmt.close();
            }
            stmt=null;
            if(conn!=null){
            }
            conn=null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
