package dao;

import domain.Complaint;
import jdbc.JDBCUtils;

import java.util.List;
import java.util.Map;

public class ComplaintDao {
    public void add(Complaint complaint) {
        String sql="insert into complaint(productname,reason,customername,phone,address,state,type) values(?,?,?,?,?,?,?)";
        Object params[]={
                complaint.getProductname(),complaint.getReason(),complaint.getCustomername(),complaint.getPhone(),
                complaint.getAddress(), complaint.getState(),complaint.getType()
        };
        JDBCUtils.insert(sql, params);
    }

    public int complaintCount() {
        String sql="select count(*) from complaint where type = 1 ";
        Object params[]={};

        return JDBCUtils.count(sql,params);
    }

    public List<Map<String, Object>> find(int startIndex, int pageSize) {
        String sql="select * from complaint  where  type = 1 limit ?,?";
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

    public void updateState(int id) {
        String sql="update complaint set state = 1 where id = ?";
        Object params[]={
                id
        };
        JDBCUtils.update(sql,params);
    }
}
