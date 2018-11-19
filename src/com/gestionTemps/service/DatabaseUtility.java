package com.gestionTemps.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtility {
	
    public static Connection loadDatabase() {
    	Connection connexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        	
        }

        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_temps", "adam", "Local MariaDB Password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return connexion;
    }
}
