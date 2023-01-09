package edu.ncucst.fruitmarket.web.dao;

import edu.ncucst.fruitmarket.web.beans.Fruit;
import edu.ncucst.fruitmarket.web.beans.User;
import edu.ncucst.fruitmarket.web.tools.JDBCTools;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    //添加数据
    public void addUser(User user){
        Statement stmt = null;
        Connection conn = null;
        try {
            conn= JDBCTools.getConnection();
            stmt=conn.createStatement();
            String sql="insert into user (username,password) values('"+user.getUsername()+"','"+user.getPassword()+"')";
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
    //查询所有数据
    public ArrayList<User> queryAllUser(){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        ArrayList<User> users=new ArrayList<User>();
        try {
            conn= JDBCTools.getConnection();
            stmt=conn.createStatement();
            String sql="select * from user";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                User user =new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            return users;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(conn,stmt,rs);
        }
        return null;
    }

    //修改数据
    public void updateUser(User user){
        Statement stmt = null;
        Connection conn = null;
        try {
            conn= JDBCTools.getConnection();
            stmt=conn.createStatement();
            String sql="update user set password='"+user.getPassword()+"' where username='"+user.getUsername()+"'";
            // System.out.println(user.getUsername());
            // System.out.println(user.getPassword());
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
    public String login(String name,String password){
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pstmt=null;
        String sql="select * from user where username=? and password=?";

        try {
            conn= JDBCTools.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,password);
            rs=pstmt.executeQuery();
            if (rs.next()){
                return "1";
            }else{
                return "2";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "3";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "4";
        }finally {
            JDBCTools.release(conn,pstmt,rs);
        }
    }
}
