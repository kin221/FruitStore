package edu.ncucst.fruitweb.dao;



import edu.ncucst.fruitweb.bean.FruitItem;
import edu.ncucst.fruitweb.utils.JDBCTools;

import java.sql.*;
import java.util.ArrayList;

public class AdminDao {

    public ArrayList<FruitItem> queryAllData(){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        ArrayList<FruitItem> list=new ArrayList<FruitItem>();
        try {
            conn= JDBCTools.getConn();
            stmt=conn.createStatement();
            String sql="select * from fruit";
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                FruitItem fruitItem=new FruitItem();
                fruitItem.setNumber(rs.getString("number"));
                fruitItem.setName(rs.getString("fruitname"));
                fruitItem.setPrice(rs.getDouble("price"));
                fruitItem.setUnit(rs.getString("unit"));
                list.add(fruitItem);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,stmt,rs);
        }
        return list;
    }

    public void addFruitItem(FruitItem fruitItem){
        Connection conn=null;
//        Statement stmt=null;
        PreparedStatement pstmt=null;
        try {
            conn= JDBCTools.getConn();

//            stmt=conn.createStatement();
//            String sql="insert into fruit values('"+fruitItem.getNumber()+"','"+fruitItem.getName()
//                    +"','"+fruitItem.getPrice()+"','"+fruitItem.getUnit()+"')";
//            int num=stmt.executeUpdate(sql);

            String sql="insert into fruit values(?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,fruitItem.getNumber());
            pstmt.setString(2,fruitItem.getName());
            pstmt.setDouble(3,fruitItem.getPrice());
            pstmt.setString(4,fruitItem.getUnit());
            int num = pstmt.executeUpdate();
            if(num>0)
                System.out.println("数据添加成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //JDBCTools.release(conn,stmt);
            JDBCTools.release(conn,pstmt);
        }
    }

    public void delFruitItem(String number){
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn= JDBCTools.getConn();
            String sql="delete from fruit where number =?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,number);
            int num = pstmt.executeUpdate();
            if(num>0)
                System.out.println("数据删除成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //JDBCTools.release(conn,stmt);
            JDBCTools.release(conn,pstmt);
        }

    }

    public void updataFruitItem(FruitItem fruitItem){
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn= JDBCTools.getConn();
            String sql="update fruit set fruitname =? , price=? , unit=? where number =?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,fruitItem.getName());
            pstmt.setDouble(2,fruitItem.getPrice());
            pstmt.setString(3,fruitItem.getUnit());
            pstmt.setString(4,fruitItem.getNumber());
            int num = pstmt.executeUpdate();
            if(num>0)
                System.out.println("数据修改成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //JDBCTools.release(conn,stmt);
            JDBCTools.release(conn,pstmt);
        }
    }

    public ArrayList<FruitItem> queryDataByCondition(FruitItem fruitItem){

        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        ArrayList<FruitItem> list=new ArrayList<FruitItem>();
        String name=fruitItem.getName();
        String number=fruitItem.getNumber();
        double price=fruitItem.getPrice();
        String unit=fruitItem.getUnit();
        try {
            conn= JDBCTools.getConn();
            stmt=conn.createStatement();
            String sql="select * from fruit where 1=1 ";
            if(number!=null&&number.length()!=0)
                sql+=" and number ="+number;
            if(name!=null&&name.length()!=0)
                //name="%"+name+"%";
                sql+=" and fruitname like '%"+name+"%'";
            if(price!=0.0)
                sql+=" and price="+price;
            if(unit!=null&&unit.length()!=0)
                sql+=" and unit like '%"+unit+"%'";


            //select * from fruit
            //select * from fruit where number='1'
            //select * from fruit where number='1' and name like '%果%'

            rs=stmt.executeQuery(sql);
            while (rs.next()){
                FruitItem fruit=new FruitItem();
                fruit.setNumber(rs.getString("number"));
                fruit.setName(rs.getString("fruitname"));
                fruit.setPrice(rs.getDouble("price"));
                fruit.setUnit(rs.getString("unit"));
                list.add(fruit);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,stmt,rs);
        }
        return list;
    }
    public ArrayList<FruitItem> queryAllDataOrderby(String order){
        Connection conn=null;

        PreparedStatement pstmt=null;
        ResultSet rs=null;
        ArrayList<FruitItem> list=new ArrayList<FruitItem>();
        try {
            conn= JDBCTools.getConn();

            String sql="select * from fruit order by ? ";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(order));


            rs=pstmt.executeQuery();
            while (rs.next()){
                FruitItem fruitItem=new FruitItem();
                fruitItem.setNumber(rs.getString("number"));
                fruitItem.setName(rs.getString("fruitname"));
                fruitItem.setPrice(rs.getDouble("price"));
                fruitItem.setUnit(rs.getString("unit"));
                list.add(fruitItem);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,pstmt,rs);
        }
        return list;
    }



}
