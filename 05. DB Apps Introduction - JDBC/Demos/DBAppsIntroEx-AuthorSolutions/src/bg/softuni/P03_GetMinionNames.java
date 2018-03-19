package bg.softuni;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by Valio on 28/09.
 */
public class P03_GetMinionNames {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/minionsdb";

    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement villainStatement = null;
        PreparedStatement minionsNamesStatement = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            Scanner sc = new Scanner(System.in);
            int id = Integer.parseInt(sc.nextLine());
            String villainNameSQL = "SELECT Name FROM villains WHERE id = ?";
            villainStatement = conn.prepareStatement(villainNameSQL);
            villainStatement.setInt(1, id);

            ResultSet villainNameResultset = villainStatement.executeQuery();
            if(!villainNameResultset.isBeforeFirst()) {
                System.out.println("No villain with ID "+id+" exists in the database.");
                return;
            }
            villainNameResultset.first();
            String villainName = villainNameResultset.getString("name");
            System.out.println("Villain: "+villainName);

            String minionsSQL = "select m.name, age\n" +
                    "FROM villains v\n" +
                    "JOIN minions_villains mv ON v.id = mv.villain_id\n" +
                    "JOIN minions m ON m.id = mv.minion_id\n" +
                    "WHERE v.id = ?";

            minionsNamesStatement = conn.prepareStatement(minionsSQL);
            minionsNamesStatement.setInt(1, id);
            ResultSet minions = minionsNamesStatement.executeQuery();
            if(!minions.isBeforeFirst()) {
                System.out.println("<no minions>");
                return;
            }
            int counter = 1;
            while(minions.next()){
                System.out.println(counter+". "+minions.getString("name")+" "+minions.getString("age"));
                counter++;
            }

            minionsNamesStatement.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(minionsNamesStatement!=null)
                    minionsNamesStatement.close();
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
