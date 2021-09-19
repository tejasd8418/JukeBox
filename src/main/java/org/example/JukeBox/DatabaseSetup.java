package org.example.JukeBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSetup {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","tejas");
        }
        catch(SQLException e){
            System.out.println(e);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }
}

