package bg.softuni;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P05_ChangeTownNamesCasing {
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

            String countryName = sc.nextLine();

            String townsFromCountrySQL = "SELECT id, name FROM towns WHERE country = ?";
            PreparedStatement townsFromCountryStatement = conn.prepareStatement(townsFromCountrySQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            townsFromCountryStatement.setString(1, countryName);
            ResultSet towns = townsFromCountryStatement.executeQuery();
            if(!towns.isBeforeFirst()){
                System.out.println("No town names were affected.");
                return;
            }

            while(towns.next()){
                String town = towns.getString("name");
                towns.updateString("name", town.toUpperCase());
                towns.updateRow();
            }

            List<String> townsUppercase = new ArrayList<>();

            towns.beforeFirst();
            while(towns.next()){
                townsUppercase.add(towns.getString("name"));
            }
            System.out.println(townsUppercase.size()+" town names were affected.");
            System.out.println(townsUppercase);
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
