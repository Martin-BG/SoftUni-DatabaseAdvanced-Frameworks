package bg.softuni;


import java.sql.*;
import java.util.Scanner;

public class P09_IncreaseAgeStoredProcedure {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/minionsdb";

    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {
        Connection conn = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();

            String getOlderSQL = "{CALL usp_get_older (?)}";
            CallableStatement getOlderStoredProcedure = conn.prepareCall(getOlderSQL);
            getOlderStoredProcedure.setInt(1, id);
            getOlderStoredProcedure.execute();

            String minionSQL = "SELECT name, age FROM minions WHERE id = ?";
            PreparedStatement minionsStatement = conn.prepareStatement(minionSQL);
            minionsStatement.setInt(1, id);
            ResultSet minions = minionsStatement.executeQuery();
            minions.first();
            System.out.println(minions.getString("name")+" "+minions.getInt("age"));

        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
