package bg.softuni;

import com.sun.xml.internal.ws.util.StringUtils;

import java.sql.*;
import java.util.Scanner;

public class P08_IncreaseMinionsAge {
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
            String[] inputTokens = sc.nextLine().split("\\s");
            
            StringBuilder ids = new StringBuilder();
            ids.append("(");
            for (int i = 0; i < inputTokens.length; i++) {
                ids.append(inputTokens[i]+", ");
            }
            ids.delete(ids.lastIndexOf(", "),ids.length());
            ids.append(")");
            System.out.println(ids.toString());

            String minionsSQL = "SELECT * FROM minions WHERE id IN "+ids.toString();
            Statement minionsStatement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            ResultSet minions = minionsStatement.executeQuery(minionsSQL);

            while(minions.next()){
                String name = toTitleCase(minions.getString("name"));
                int age = minions.getInt("age") + 1;
                minions.updateString("name", name);
                minions.updateInt("age", age);
                minions.updateRow();
            }

            String allMinionsSQL = "SELECT * FROM minions";
            Statement allMinionsStatement = conn.createStatement();

            ResultSet allMinions = allMinionsStatement.executeQuery(allMinionsSQL);

            while(allMinions.next()){
                System.out.println(allMinions.getInt("id")+" "+allMinions.getString("name")+" "+allMinions.getInt("age"));
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

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
