package bg.softuni;

import java.sql.*;

public class P01_InitialSetup {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Execute a query`
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "DROP DATABASE IF EXISTS minionsdb";
            stmt.execute(sql);
            sql = "CREATE DATABASE minionsdb";
            stmt.execute(sql);
            conn = DriverManager.getConnection(DB_URL + "minionsdb", USER, PASS);
            stmt = conn.createStatement();
            sql = "CREATE TABLE towns (id INT PRIMARY KEY AUTO_INCREMENT, name varchar(50), country varchar(50))";
            stmt.execute(sql);
            sql = "CREATE TABLE minions (id INT PRIMARY KEY AUTO_INCREMENT, name varchar(50), age int, town_id int, CONSTRAINT fk_Towns FOREIGN KEY (town_id) REFERENCES towns(id))";
            stmt.execute(sql);

            sql = "CREATE TABLE villains (id INT PRIMARY KEY AUTO_INCREMENT, name varchar(50), evilness_factor varchar(20))";
            stmt.execute(sql);
            sql = "CREATE TABLE minions_villains(minion_id INT, villain_id INT, CONSTRAINT fk_Minions FOREIGN KEY (minion_id) REFERENCES Minions(id), CONSTRAINT  fk_Villains FOREIGN KEY (villain_id) REFERENCES villains(id))";
            stmt.execute(sql);

            sql = "INSERT INTO towns (name, country) VALUES ('Sofia','Bulgaria'), ('Burgas','Bulgaria'), ('Varna', 'Bulgaria'), ('London','UK'),('Liverpool','UK'),('Ocean City','USA'),('Paris','France')";
            stmt.execute(sql);

            sql = "INSERT INTO minions (name, age, town_id) VALUES ('bob',10,1),('kevin',12,2),('steward',9,3), ('rob',22,3), ('michael',5,2),('pep',3,2)";
            stmt.execute(sql);

            sql = "INSERT INTO villains (name, evilness_factor) VALUES ('Gru','super evil'),('Victor','evil'),('Simon Cat','good'),('Pusheen','super good'),('Mammal','evil')";
            stmt.execute(sql);

            sql = "INSERT INTO minions_villains VALUES (1,2), (3,1),(1,3),(3,3),(4,1),(2,2),(1,1),(3,4), (1, 4), (1,5), (5, 1), (4,1), (3, 1)";
            stmt.execute(sql);

            //Clean-up environment
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
