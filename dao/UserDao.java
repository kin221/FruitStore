package edu.ncucst.fruitweb.dao;

import edu.ncucst.fruitweb.bean.User;
import edu.ncucst.fruitweb.utils.JDBCTools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDao {
    public ArrayList<User> queryAllData() {
        //return DataBase.data;
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        ArrayList<User> list =new ArrayList<User>();
        try {
            conn= JDBCTools.getConn();
            stmt=conn.createStatement();
            String sql="SELECT * from user ";
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                list.add(user);
            }
            return list;
        }catch (Exception e){e.getStackTrace();}
        finally {
            JDBCTools.release(conn,stmt,rs);
        }
        return null;
    }
    public void updataFruitItem(User user) {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= JDBCTools.getConn();
            stmt=conn.createStatement();
            String sql="update user set username='"+user.getUsername()+
                    "',password='"+user.getPassword()+
                    "'where id='"+user.getId()+ "'";
            int num=stmt.executeUpdate(sql);
            if(num>0){
                System.out.println("修改密码成功！");
            }
        }catch (Exception e){e.getStackTrace();}
        finally {
            JDBCTools.release(conn,stmt,rs);
        }
    }

}
