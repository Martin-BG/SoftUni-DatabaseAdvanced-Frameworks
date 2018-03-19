package bg.softuni;

import java.sql.*;

public class P07_PrintAllMinionNames {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/minionsdb";

    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {
        Connection conn = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            String minionNames = "SELECT name FROM minions";
            PreparedStatement minionsStatement = conn.prepareStatement(minionNames, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet minions = minionsStatement.executeQuery();

            int minionsCount = 0;
            while(minions.next()){
                minionsCount++;
            }

            minions.beforeFirst();

            int firstIndex = 1;
            int lastIndex = minionsCount;

            for (int i = 1; i < minionsCount+1; i++) {
                if(i%2 == 1){
                    minions.absolute(firstIndex);
                    firstIndex++;
                }else{
                    minions.absolute(lastIndex);
                    lastIndex--;
                }

                System.out.println(minions.getString("name"));
                minions.next();
            }

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
