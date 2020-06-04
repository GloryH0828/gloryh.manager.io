package dao;

import domain.Consult;
import jdbc.JDBCUtils;

import java.util.List;
import java.util.Map;

public class ConsultDao {
    public void add(Consult consult) {
        String sql="insert into consult(question,answer,customername,phone) values(?,?,?,?)";
        Object params[]={
                consult.getQuestion(),consult.getAnswer(),consult.getCustomername(),consult.getPhone()
        };
        JDBCUtils.insert(sql, params);
    }

    public int repairCount() {
        String sql="select count(*) from consult ";
        Object params[]={};

        return JDBCUtils.count(sql,params);
    }


    public List<Map<String, Object>> find(int startIndex, int pageSize) {
        String sql="select * from consult limit ?,?";
        Object params[]={
                startIndex,pageSize
        };
        return JDBCUtils.select(sql,params);
    }
}
