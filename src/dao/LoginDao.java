package dao;

import domain.User;
import jdbc.JDBCUtils;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class LoginDao {
    public User loginState(String username, String role) {
        User user=null;
        String sql="";
        if(role.equals("superadmin")) {
            sql = "select * from superadmin where username=?";
        }else if(role.equals("admin")) {
            sql = "select * from admin where username=?";
        }else if(role.equals("infoadmin")) {
            sql = "select * from infoadmin where username=?";
        }else if(role.equals("worker")) {
            sql = "select * from worker where username=?";
        }else {
            sql = "select * from waiter where username=?";
        }
        //将身份role和工号username存入params[],以list形式与sql连结起来
        Object params[]={
                username
        };
        //连接数据库；
        List<Map<String, Object>> list =JDBCUtils.select(sql,params);
        user=new User();
        if(!list.isEmpty()){

            user.setId((Integer)list.get(0).get("id"));
            user.setUsername((String)list.get(0).get("username"));
            user.setPassword((String)list.get(0).get("password"));
            user.setName((String)list.get(0).get("name"));
        }else{
            user.setId(0);
        }
        System.out.println("list"+list);
        System.out.println("user"+user);
        return user;
    }
}
