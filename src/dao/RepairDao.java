package dao;

import domain.Complaint;
import domain.Fixlog;
import jdbc.JDBCUtils;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class RepairDao {
    public void add(Complaint complaint) {
        String sql="insert into complaint(productname,reason,customername,phone,address,state,type) values(?,?,?,?,?,?,?)";
        Object params[]={
                complaint.getProductname(),complaint.getReason(),complaint.getCustomername(),complaint.getPhone(),
                complaint.getAddress(), complaint.getState(),complaint.getType()
        };
        JDBCUtils.insert(sql, params);
    }

    public int repairCount() {

        String sql="select count(*) from complaint where type = 0 ";
        Object params[]={};

        return JDBCUtils.count(sql,params);
    }

    public List<Map<String, Object>> find(int startIndex, int pageSize) {
        String sql="select * from complaint  where  type = 0 limit ?,?";
        Object params[]={
                startIndex,pageSize
        };
        return JDBCUtils.select(sql,params);
    }

    public Complaint findByID(int id) {
        Complaint complaint=new Complaint();
        String sql="select * from complaint where id=?";
        Object params[]={
                id
        };
        //连接数据库；
        List<Map<String, Object>> list =JDBCUtils.select(sql,params);
        if(!list.isEmpty()){
            complaint.setId((Integer)list.get(0).get("id"));
            //user.setUsername((String)list.get(0).get("username"));
            complaint.setProductname((String)list.get(0).get("productname"));
            complaint.setReason((String)list.get(0).get("reason"));
            complaint.setState((Integer) list.get(0).get("state"));
            complaint.setCustomername((String)list.get(0).get("customername"));
            complaint.setPhone((String)list.get(0).get("phone"));
            complaint.setAddress((String)list.get(0).get("address"));
            complaint.setType((Integer) list.get(0).get("type"));
        }else{
            complaint.setId(0);
        }
        System.out.println("complaint"+complaint);
        return complaint;
    }

    public List<Map<String, Object>> findFreeWorker() {
        String sql="select * from worker  where  state= 0 ";
        Object params[]={
        };
        return JDBCUtils.select(sql,params);
    }

    public void creatFixLog(Fixlog fixlog) {
        String sql="insert into fixlog(workername,username,state,customername,customerphone,customeraddress," +
                "productname,borrowtime,reason,toolname,toolcount,partsname,partscount) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object params[]={
                fixlog.getWorkername(),fixlog.getUsername(),fixlog.getState(),fixlog.getCustomername(),
                fixlog.getCustomerphone(),fixlog.getCustomeraddress(),
                fixlog.getProductname(),fixlog.getBorrowtime(),fixlog.getReason(),
                fixlog.getToolname(),fixlog.getToolcount(),
                fixlog.getPartsname(),fixlog.getPartscount()
        };
        JDBCUtils.insert(sql,params);
    }

    public void updateRepair(int id) {
        String sql="update complaint set state=3 where id=?";
        Object params[]={
                id
        };
        JDBCUtils.update(sql,params);
    }

    public void upadteWorkerState(String username) {
        String sql="update worker set state=1 where username=?";
        Object params[]={
                username
        };
        JDBCUtils.update(sql,params);
    }
}
