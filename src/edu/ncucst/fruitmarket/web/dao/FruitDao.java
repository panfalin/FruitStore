package edu.ncucst.fruitmarket.web.dao;





import edu.ncucst.fruitmarket.web.beans.Fruit;
import edu.ncucst.fruitmarket.web.tools.JDBCTools;

import java.sql.*;
import java.util.ArrayList;

public class FruitDao {
    private edu.ncucst.fruitmarket.web.tools.JDBCTools JDBCTools;

    //查询所有数据
    public ArrayList<Fruit> queryAllFruit(){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<Fruit> fruits=new ArrayList<Fruit>();
        try {
            conn= JDBCTools.getConnection();
            stmt=conn.createStatement();
            String sql="select * from fruit";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                Fruit fruit=new Fruit();
                fruit.setNumber(rs.getString("number"));
                fruit.setName(rs.getString("name"));
                fruit.setPrice(rs.getDouble("price"));
                fruit.setUnit(rs.getString("unit"));
                fruits.add(fruit);
            }
            return fruits;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,stmt,rs);
        }
        return null;
    }

    //条件查询
    public ArrayList<Fruit> queryFruitByCond(Fruit fruit){
        //select * from fruit
        //select * from fruit where number='001'
        //select * from fruit where name like '%果%' and price='5'
        //select * from fruit where 1=1 and name like '%果%' and price='5'
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<Fruit> fruits=new ArrayList<Fruit>();
        try {
            conn= JDBCTools.getConnection();
            stmt=conn.createStatement();

            String sql="select * from fruit where 1=1";
            if(fruit.getNumber()!=null&&fruit.getNumber().length()>0)
                sql=sql+" and number='"+fruit.getNumber()+"'";
            if(fruit.getName()!=null&&fruit.getName().length()>0)
                sql=sql+" and name like '%"+fruit.getName()+"%'";
            if(fruit.getPrice()!=0)
                sql=sql+" and price='"+fruit.getPrice()+"'";
            if(fruit.getUnit()!=null&&fruit.getUnit().length()>0)
                sql=sql+" and unit like '%"+fruit.getUnit()+"%'";

            rs=stmt.executeQuery(sql);
            while(rs.next()){
                Fruit date=new Fruit();
                date.setNumber(rs.getString("number"));
                date.setName(rs.getString("name"));
                date.setPrice(rs.getDouble("price"));
                date.setUnit(rs.getString("unit"));
                fruits.add(date);
            }
            return fruits;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,stmt,rs);
        }
        return null;
    }

    //按条件排序查询所有数据
    public ArrayList<Fruit> sortFruits(String sort){
        //Statement stmt = null;
        PreparedStatement pstmt=null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<Fruit> fruits=new ArrayList<Fruit>();
        try {
            conn= JDBCTools.getConnection();
          //  stmt=conn.createStatement();
          //  String sql="select * from fruit";
            String sql="select * from fruit order by "+sort;
            pstmt=conn.prepareStatement(sql);
            //rs=stmt.executeQuery(sql);
            //pstmt.setString(1,sort);
            rs=pstmt.executeQuery();
            while(rs.next()){
                Fruit fruit=new Fruit();
                fruit.setNumber(rs.getString("number"));
                fruit.setName(rs.getString("name"));
                fruit.setPrice(rs.getDouble("price"));
                fruit.setUnit(rs.getString("unit"));
                fruits.add(fruit);
            }
            return fruits;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
           JDBCTools.release(conn,pstmt,rs);

        }
        return null;
    }


    //添加数据
    public void addFruit(Fruit fruit){
        Statement stmt = null;
        Connection conn = null;
        try {
            conn= JDBCTools.getConnection();
            stmt=conn.createStatement();
            String sql="insert into fruit(number,name,price,unit) values('"+fruit.getNumber()+"','"+fruit.getName()
                    +"','"+fruit.getPrice()+"','"+fruit.getUnit()+"')";
            int num=stmt.executeUpdate(sql);
            if(num>0)
                System.out.println("添加数据成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,stmt);
        }
    }

    //修改数据
    public void updateFruit(Fruit fruit){
        Statement stmt = null;
        Connection conn = null;
        try {
            conn= JDBCTools.getConnection();
            stmt=conn.createStatement();
            String sql="update fruit set name='"+fruit.getName()+"',price='"+fruit.getPrice()
                    +"',unit='"+fruit.getUnit()+"' where number='"+fruit.getNumber()+"'";
            int num=stmt.executeUpdate(sql);
            if(num>0)
                System.out.println("修改数据成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,stmt);
        }
    }

    //删除数据
    public void delFruit(String delNumber){
        Statement stmt = null;
        Connection conn = null;
        try {
            conn= JDBCTools.getConnection();
            stmt=conn.createStatement();
            String sql="delete from fruit where number='"+delNumber+"'";
            int num=stmt.executeUpdate(sql);
            if(num>0)
                System.out.println("删除数据成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,stmt);
        }
    }
}
