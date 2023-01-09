package edu.ncucst.fruitmarket.web.service;

import edu.ncucst.fruitmarket.web.beans.Fruit;
import edu.ncucst.fruitmarket.web.beans.User;
import edu.ncucst.fruitmarket.web.dao.UserDao;

import java.util.ArrayList;

public class UserService {
    private UserDao dao=new UserDao();
    public String login(String name,String password){
        UserDao dao=new UserDao();
        if (name!=null && name.length()>0){
            return dao.login(name, password);
        }else{
            return "5";
        }
    }
    public ArrayList<User> queryAllUser(){
        ArrayList<User> users=dao.queryAllUser();
        return users;
    }
    public boolean updateUser(User user){
        ArrayList<User> users=queryAllUser();
        for(User data:users){
            if(user.getUsername().equals(data.getUsername())){
                dao.updateUser(user);
                return true;
            }
        }
        return false;
    }
    public boolean addUser(User user){
        ArrayList<User> users=queryAllUser();
        for(User data:users){
            if(user.getUsername().equals(data.getUsername()))
                return false;
        }
        dao.addUser(user);
        return true;
    }
}
