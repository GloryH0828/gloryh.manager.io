package dao;

import domain.*;
import jdbc.JDBCUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class StorageDao {
    public List<Map<String, Object>> find(String type) {
        String sql="";
        if(type.equals("tools1")){
            sql="select * from stock where type =0";
        }else if(type.equals("tools3")){
            sql="select * from stock where type =3";
        }else if(type.equals("tools2")){
            sql="select * from stock where type = 0 and count < 5";
        }else if(type.equals("parts1")){
            sql="select * from stock where type =1";
        }else if(type.equals("parts3")){
            sql="select * from stock where type =2";
        }else{
            sql="select * from stock where type = 1 and count < 10";
        }
        Object params[]={

        };
        return JDBCUtils.select(sql,params);
    }

    public List<Map<String, Object>> findWorker(String flag) {
        String sql="";
        if(flag.equals("ed")){
             sql="select * from fixlog where toolname = ?";

        }else {
             sql="select * from fixlog where returntime is null and toolname != ?";

        }
        Object params[]={
                "无"
        };
        return JDBCUtils.select(sql,params);

    }

    public Fixlog findFixLogByUsername(String username) {
        Fixlog fixlog=new Fixlog();
        String sql="select * from fixlog where username = ?";
        Object params[]={
                username
        };
        //连接数据库；
        List<Map<String, Object>> list =JDBCUtils.select(sql,params);
        if(!list.isEmpty()){
            fixlog.setId((Integer)list.get(0).get("id"));
            //user.setUsername((String)list.get(0).get("username"));
            fixlog.setProductname((String)list.get(0).get("productname"));
            fixlog.setUsername((String)list.get(0).get("username"));
            fixlog.setReason((String)list.get(0).get("reason"));
            fixlog.setCustomername((String)list.get(0).get("customername"));
            fixlog.setCustomerphone((String)list.get(0).get("customerphone"));
            fixlog.setCustomeraddress((String)list.get(0).get("customeraddress"));

        }
        System.out.println("fixlog"+fixlog);

        return fixlog;
    }

    public int fingPartsCount(String partName) {
        String sql="select * from stock where name = ? and type = 1 ";
        Object params[]={
                partName
        };
        List<Map<String, Object>> list =JDBCUtils.select(sql,params);
        if (!list.isEmpty()){
            return (Integer)list.get(0).get("count");
        }
        return 0;
    }

    public void updateFixLog(Fixlog fixlog) {
        System.out.println("fixlog="+fixlog);
        String sql ="UPDATE fixlog SET toolname = ? , toolcount = ?, partsname = ? , partscount =?  WHERE id = ?";
        Object params[]={
                fixlog.getToolname(),fixlog.getToolcount(),fixlog.getPartsname(),
                fixlog.getPartscount(),fixlog.getId()
        };
        JDBCUtils.update(sql,params);
    }

    public void updateStock(Fixlog fixlog) {
        String sql1 ="UPDATE stock SET count = count-1  WHERE name = ? and type = 0";
        String sql2 ="UPDATE stock SET count = count-?  WHERE name = ? and type = 1";
        Object params1[]={
                fixlog.getToolname()
        };
        Object params2[]={
                fixlog.getPartscount(),fixlog.getPartsname()
        };
        JDBCUtils.update(sql1,params1);
        JDBCUtils.update(sql2,params2);
    }

    public void insideNew(Putin putin) {
        String sql ="insert into putin(name,count,useful,price,time,state,type) values(?,?,?,?,?,?,?)";
        Object params[]={
                putin.getName(),putin.getCount(),putin.getUse(),putin.getPrice(),
                putin.getTime(),putin.getState(),putin.getType()
        };
        JDBCUtils.insert(sql,params);
    }

    public int isExist(Putin putin) {
        String sql ="select count(*) from stock where name = ? ";
        Object params[]={
                putin.getName()
        };
        return JDBCUtils.count(sql,params);
    }

    public void addNew(Putin putin) {
        int type =2;
        if(putin.getType()==0){
            type =3;
        }
        String sql1="insert into stock(name,count,type) values(?,?,?)";
        Object params1[]={
                putin.getName(),putin.getCount(),
                putin.getType()
        };
        String sql2="insert into stock(name,count,type) values(?,?,?)";
        Object params2[]={
                putin.getName(),0,type
        };
        JDBCUtils.insert(sql1,params1);
        JDBCUtils.insert(sql2,params2);
    }

    public void putin(Putin putin) {
        String sql ="UPDATE stock SET count = count+?  WHERE name = ? and type = ?";
        Object params[]={
          putin.getCount(),putin.getName(),putin.getType()
        };
        JDBCUtils.update(sql,params);
    }

    public Fixlog findFixLogByID(int id) {
        Fixlog fixlog=new Fixlog();
        String sql="select * from fixlog where id = ?";
        Object params[]={

                id
        };
        //连接数据库；
        List<Map<String, Object>> list =JDBCUtils.select(sql,params);
        if(!list.isEmpty()){
            fixlog.setId((Integer)list.get(0).get("id"));
            //user.setUsername((String)list.get(0).get("username"));
            fixlog.setProductname((String)list.get(0).get("productname"));
            fixlog.setUsername((String)list.get(0).get("username"));
            fixlog.setToolname((String)list.get(0).get("toolname"));
            fixlog.setPartsname((String)list.get(0).get("partsname"));
            fixlog.setPartscount((Integer) list.get(0).get("partscount"));
            fixlog.setReason((String)list.get(0).get("reason"));
            fixlog.setCustomername((String)list.get(0).get("customername"));
            fixlog.setCustomerphone((String)list.get(0).get("customerphone"));
            fixlog.setCustomeraddress((String)list.get(0).get("customeraddress"));
            fixlog.setWorkername((String)list.get(0).get("workername"));
            fixlog.setToolcount((Integer) list.get(0).get("toolcount"));
            fixlog.setBorrowtime((Date)list.get(0).get("borrowtime"));
            fixlog.setReturntime((Date)list.get(0).get("returntime"));
            fixlog.setOldparts((String)list.get(0).get("oldparts"));
            fixlog.setOpcount((Integer) list.get(0).get("opcount"));
            fixlog.setBonus((Integer) list.get(0).get("bonus"));
            fixlog.setState((Integer) list.get(0).get("state"));
            fixlog.setCost((Integer) list.get(0).get("cost"));
        }
        System.out.println("fixlog"+fixlog);

        return fixlog;
    }
    public Fixlog findFixLogByID2(int id) {
        Fixlog fixlog=new Fixlog();
        String sql="select * from fixlog where id = ?";
        Object params[]={

                id
        };
        //连接数据库；
        List<Map<String, Object>> list =JDBCUtils.select(sql,params);
        if(!list.isEmpty()){
            fixlog.setId((Integer)list.get(0).get("id"));
            //user.setUsername((String)list.get(0).get("username"));
            fixlog.setProductname((String)list.get(0).get("productname"));
            fixlog.setUsername((String)list.get(0).get("username"));
            fixlog.setToolname((String)list.get(0).get("toolname"));
            fixlog.setPartsname((String)list.get(0).get("partsname"));
            fixlog.setPartscount((Integer) list.get(0).get("partscount"));
            fixlog.setReason((String)list.get(0).get("reason"));
            fixlog.setCustomername((String)list.get(0).get("customername"));
            fixlog.setCustomerphone((String)list.get(0).get("customerphone"));
            fixlog.setCustomeraddress((String)list.get(0).get("customeraddress"));
            fixlog.setWorkername((String)list.get(0).get("workername"));
            fixlog.setToolcount((Integer) list.get(0).get("toolcount"));
            fixlog.setBorrowtime((Date)list.get(0).get("borrowtime"));


        }
        System.out.println("fixlog"+fixlog);

        return fixlog;
    }
    public void returnFixLog(Fixlog fixlog) {
        String sql ="UPDATE fixlog SET oldparts = ? , opcount = ?,returntime=?  WHERE id = ?";
        Object params[]={
                fixlog.getOldparts(),fixlog.getOpcount(),fixlog.getReturntime(),fixlog.getId()
        };
        JDBCUtils.update(sql,params);
    }

    public void returnStock(Fixlog fixlog) {
        String sql1 ="UPDATE stock SET count = count+1  WHERE name = ? and type = 0";
        String sql2 ="UPDATE stock SET count = count + ?  WHERE name = ? and type = 1";
        String sql3 ="UPDATE stock SET count = count + ?  WHERE name = ? and type = 2";
        Object params1[]={
                fixlog.getToolname()
        };
        Object params2[]={
                fixlog.getPartscount(),fixlog.getOldparts()
        };
        Object params3[]={
                fixlog.getOpcount(),fixlog.getOldparts()
        };
        JDBCUtils.update(sql1,params1);
        JDBCUtils.update(sql2,params2);
        JDBCUtils.update(sql3,params3);
    }
}
