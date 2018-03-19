package bg.softuni;

import java.sql.*;
import java.util.Scanner;

public class P04_InsertMinion {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/minionsdb";

    static final String USER = "root";
    static final String PASS = "1234";

    //TODO add transactions
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement townStatement = null;
        PreparedStatement villainStatement = null;
        PreparedStatement addTownStatement = null;
        PreparedStatement addVillainStatement = null;
        PreparedStatement addMinionStatement = null;
        PreparedStatement addMinionToVillainStatement = null;
        PreparedStatement getNewMinionIdStatement = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(false);
            Scanner sc = new Scanner(System.in);

            String[] minionTokens = sc.nextLine().split("\\s");
            String minionName = minionTokens[1];
            int minionAge = Integer.parseInt(minionTokens[2]);
            String minionTown = minionTokens[3];
            String villainName = sc.nextLine().split("\\s")[1];

            String townSQL = "SELECT id FROM towns WHERE name = ?";
            townStatement = conn.prepareStatement(townSQL);
            townStatement.setString(1, minionTown);

            ResultSet towns = townStatement.executeQuery();
            if(!towns.isBeforeFirst()){
                //add town to db
                String addTownSQL = "INSERT INTO towns (name, country) VALUES (?, NULL)";
                addTownStatement = conn.prepareStatement(addTownSQL);
                addTownStatement.setString(1, minionTown);
                addTownStatement.executeUpdate();

                System.out.printf("Town %s was added to the database.%n", minionTown);
            }

            towns = townStatement.executeQuery();
            towns.first();
            int townID = towns.getInt("id");

            String villainSQL = "SELECT * FROM villains WHERE name = ?";
            villainStatement = conn.prepareStatement(villainSQL);
            villainStatement.setString(1, villainName);

            ResultSet villain = villainStatement.executeQuery();
            if(!villain.isBeforeFirst()) {
                //add villain to db
                String  addVillainSQL = "INSERT INTO villains (name, evilness_factor) VALUES (?, 'evil')";
                addVillainStatement = conn.prepareStatement(addVillainSQL);
                addVillainStatement.setString(1, villainName);
                addVillainStatement.executeUpdate();
                System.out.printf("Villain %s was added to the database.%n", villainName);
            }
            villain = villainStatement.executeQuery();
            villain.first();
            int villainID = villain.getInt("id");

            //add minion
            String addMinionSQL = "INSERT INTO minions (name, age, town_id) VALUES (?, ?, ?)";
            addMinionStatement = conn.prepareStatement(addMinionSQL);
            addMinionStatement.setString(1, minionName);
            addMinionStatement.setInt(2, minionAge);
            addMinionStatement.setInt(3, townID);
            addMinionStatement.executeUpdate();

            //get minion ID
            String getMinionIdSQL = "SELECT id FROM minions where name = ?";
            getNewMinionIdStatement = conn.prepareStatement(getMinionIdSQL);
            getNewMinionIdStatement.setString(1, minionName);
            ResultSet minions = getNewMinionIdStatement.executeQuery();
            minions.first();
            int minionID = minions.getInt("id");

            //add record in villains_minions table
            String addMinionToVillainSQL = "INSERT INTO minions_villains (minion_id, villain_id) VALUES (?, ?)";
            addMinionToVillainStatement = conn.prepareStatement(addMinionToVillainSQL);
            addMinionToVillainStatement.setInt(1, minionID);
            addMinionToVillainStatement.setInt(2, villainID);
            addMinionToVillainStatement.executeUpdate();
            System.out.printf("Successfully added %s to be minion of %s", minionName, villainName);
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
