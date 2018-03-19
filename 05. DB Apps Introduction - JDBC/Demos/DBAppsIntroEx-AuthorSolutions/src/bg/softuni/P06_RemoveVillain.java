package bg.softuni;

import java.sql.*;
import java.util.Scanner;

public class P06_RemoveVillain {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/minionsdb";

    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(false);
            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();

            String villainNameSQL = "SELECT name FROM villains WHERE id = "+id;
            String deleteVillainSQL = "DELETE FROM villains where id = "+id;
            String countMinionsSQL = "SELECT COUNT(minion_id) AS c FROM villains v\n" +
                    "JOIN minions_villains mv ON v.id = mv.villain_id\n" +
                    "WHERE v.id = "+id;
            String releaseMinionsSQL = "DELETE FROM minions_villains WHERE villain_id = "+id;

            Statement statement = conn.createStatement();
            ResultSet villain = statement.executeQuery(villainNameSQL);
            if(!villain.isBeforeFirst()){
                System.out.println("No such villain was found");
                return;
            }
            villain.first();
            String villainName = villain.getString("name");
            int minionsFound = 0;
            ResultSet minionsForVillain = statement.executeQuery(countMinionsSQL);
            if(minionsForVillain.isBeforeFirst()){
                minionsForVillain.first();
                minionsFound = minionsForVillain.getInt("c");
            }

            statement.executeUpdate(releaseMinionsSQL);


            statement.executeUpdate(deleteVillainSQL);
            System.out.println(villainName+" was deleted");

            System.out.println(minionsFound +" minions released");

        }catch(SQLException se){
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            se.printStackTrace();
        }catch(Exception e){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
