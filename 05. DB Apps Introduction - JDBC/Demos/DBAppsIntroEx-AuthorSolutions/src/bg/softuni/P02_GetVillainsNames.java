package bg.softuni;

import java.sql.*;

public class P02_GetVillainsNames {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/minionsdb";

    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "select v.name, COUNT(minion_id) as `count`\n" +
                    "FROM villains v\n" +
                    "JOIN minions_villains mv ON v.id = mv.villain_id\n" +
                    "GROUP BY villain_id\n" +
                    "HAVING COUNT(minion_id) > 3";
            ResultSet villainsNames = stmt.executeQuery(sql);

            while(villainsNames.next()){
                System.out.println(villainsNames.getString("name")+" "+villainsNames.getInt("count"));
            }

            villainsNames.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
