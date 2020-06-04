package jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class JDBCUtils {
    private static String driver;
    private static String url;
    private static String name;
    private static String password;

    private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();

    static{

        InputStream in =JDBCUtils.class.getClassLoader().getResourceAsStream("DBDriverConfig.properties");
        Properties prop =new Properties();
        try {

            driver ="com.mysql.jdbc.Driver";
            url ="jdbc:mysql://localhost:3306/dbmanager?useUnicode=true&characterEncoding=utf8";
            name ="root";
            password ="123";

            Class.forName(driver);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }

    }
    public static Connection getConnection() throws SQLException {
        Connection conn =tl.get();
        if(conn!=null){
            return conn;
        }
        return DriverManager.getConnection(url,name,password);

    }
    public static void release(Connection conn, Statement stmt, ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally{
            try{
                if(stmt!=null){
                    stmt.close();
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }finally{
                Connection tconn=tl.get();
                if(tconn==conn){
                    return;
                }
                try{
                    if(conn!=null){
                        conn.close();
                    }
                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void beginTranscation() throws SQLException{

        Connection conn= tl.get();

        if(conn!=null){
            throw new SQLException("连接失败，conn!=null");
        }
        else{
            conn=getConnection();
            conn.setAutoCommit(false);
            tl.set(conn);
        }
    }

    public static void commitTranscation() throws SQLException{

        Connection conn=tl.get();
        if(conn==null){
            throw new SQLException("连接失败，conn==null");
        }
        else{
            conn.commit();
            conn.close();
            conn=null;
            tl.remove();
        }
    }

    public static void rollbacktTranscation() throws SQLException{

        Connection conn=tl.get();

        if(conn==null){
            throw new SQLException("连接失败，conn==null");
        }
        else{
            conn.rollback();
            conn.close();
            conn=null;
            tl.remove();
        }
    }

    public static List<Map<String,Object>> select(String sql, Object ...params){

        Connection conn=null;
        PreparedStatement pre=null;
        ResultSet rs=null;
        List<Map<String,Object>> list =null;

        try {

            conn=getConnection();

            pre=conn.prepareStatement(sql);
            fillStatement(pre, params);

            rs=pre.executeQuery();
            list=RsToList(rs);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }finally{
            release(conn,pre,rs);
        }

        return list;
    }

    private static List<Map<String,Object>> RsToList(ResultSet rs)throws SQLException {
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();

        ResultSetMetaData	rsmd = rs.getMetaData();

        while(rs.next()){

            Map<String,Object> map=new HashMap<String,Object>();

            for(int i=1;i<=rsmd.getColumnCount();i++){

                map.put(rsmd.getColumnName(i), rs.getObject(i));
            }
            list.add(map);
        }
        return list;

    }

    public static void update(String sql,Object...params){

        Connection conn=null;
        PreparedStatement pre=null;

        try {

            conn=getConnection();

            pre=conn.prepareStatement(sql);

            fillStatement(pre, params);

            pre.executeUpdate();


        } catch (Exception e) {

            throw new RuntimeException(e);
        }finally{

            release(conn,pre,null);
        }
    }
    private static void fillStatement(PreparedStatement pre, Object... params) throws SQLException {
        if(params!=null){
            for(int i=0;i<params.length;i++){
                pre.setObject(i+1, params[i]);
            }
        }
    }
    public static int count(String sql, Object... params){
        ResultSet rs=null;
        Connection conn=null;
        PreparedStatement pre=null;
        Object result = null;
        try {
            conn=getConnection();

            pre=conn.prepareStatement(sql);

            fillStatement(pre,params);
            rs=pre.executeQuery();
            if(rs.next()){
                result= rs.getObject(1);
            }



        } catch (Exception e) {

            throw new RuntimeException(e);
        }finally {
            release(conn,(Statement) pre,rs);
        }
        System.out.println(result);
        Number number=(Number)result;
        return number.intValue();
    }
    public static Object insert(String sql,Object...params){

        Connection conn=null;
        PreparedStatement pre=null;
        ResultSet rs=null;
        try {

            conn=getConnection();

            pre=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            fillStatement(pre, params);

            pre.executeUpdate();

            rs=pre.getGeneratedKeys();
            Object key=null;
            if(rs.next()){
                key=rs.getObject(1);
            }
            return key;
        } catch (Exception e) {

            throw new RuntimeException(e);
        }finally{

            release(conn,pre,rs);
        }
    }

    public static int[] updatebatch(String sql,Object[][]params){
        Connection conn =null;
        PreparedStatement pre=null;
        ResultSet rs=null;
        try {
            conn=getConnection();
            pre=conn.prepareStatement(sql);

            if(params!=null){
                for(int i=0;i<params.length;i++){

                    fillStatement(pre,params[i]);
                    pre.addBatch();
                }
            }
            return pre.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            release(conn,pre,rs);
        }
    }

    public static <T> List<T> insertbatch(String sql,Object[][]params){
        Connection conn =null;
        PreparedStatement pre=null;
        ResultSet rs=null;
        try {
            conn=getConnection();
            pre=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            if(params!=null){
                for(int i=0;i<params.length;i++){

                    fillStatement(pre,params[i]);
                    pre.addBatch();
                }
            }
            pre.executeBatch();
            rs=pre.getGeneratedKeys();
            List<T> list=new ArrayList<T>();
            while(rs.next()){
                list.add((T)rs.getObject(1));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            release(conn,pre,rs);
        }
    }

    public static <T>T selectScalar(String sql,Object ...params){
        Connection conn=null;
        PreparedStatement pre=null;
        ResultSet rs=null;
        T result=null;
        try {
            conn=getConnection();
            pre=conn.prepareStatement(sql);
            fillStatement(pre, params);
            rs=pre.executeQuery();
            if(rs.next()){
                result=(T)rs.getObject(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }finally{
            release(conn, pre, rs);
        }
        return result;
    }
}
