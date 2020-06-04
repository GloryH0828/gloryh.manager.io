package dao;

import domain.Complaint;
import domain.Fixlog;
import jdbc.JDBCUtils;

import java.util.List;
import java.util.Map;

public class FixLogDao {
    public List<Map<String, Object>> findAll(String ed) {
        String sql="";
        if(ed.equals("ed")){
            sql="select * from fixlog where returntime is NOT NULL and state != 2 ";
            Object params[]={

            };
            return JDBCUtils.select(sql,params);
        }else{
            sql="select * from fixlog where returntime is not null and cost is null ";
            Object params[]={

            };
            return JDBCUtils.select(sql,params);
        }



    }

    public void updateFixLog(Fixlog fixlog) {
        String sql ="UPDATE fixlog SET bonus = ?,state = ?,reason = ?,cost = ?  WHERE id = ?";
        Object params[]={
                fixlog.getBonus(),fixlog.getState(),fixlog.getReason(),fixlog.getCost(),fixlog.getId()
        };
        JDBCUtils.update(sql,params);
    }

    public void updateComplaint(Complaint complaint) {
        String sql ="UPDATE complaint SET state = ?  WHERE phone = ? and productname = ? and type = 0";
        Object params[]={
                complaint.getState(),complaint.getPhone()
                ,complaint.getProductname()
        };
        JDBCUtils.update(sql,params);
    }

    public void updateWorker(String username) {
        String sql ="UPDATE worker SET state = 0  WHERE username = ?";
        Object params[]={
               username
        };
        JDBCUtils.update(sql,params);
    }
}
