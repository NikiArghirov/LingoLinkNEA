/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.LingoLink.dao;

import java.sql.*;

public class UserDAO {
    
    public static boolean authentication(String username, String password) {
        try {
            Connection connect = DatabaseConnection.getConnection();
            System.out.println("Database connection successful");
            
           
            PreparedStatement statement = connect.prepareStatement("SELECT * FROM User WHERE Username = ? AND Password = ?");
            
            statement.setString(1, username);
            statement.setString(2, password);
            
            System.out.println("Querying for Username: '" + username + "', Password: '" + password + "'");
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("UserID");
                String dbUsername = rs.getString("Username");
                System.out.println("Login successful for user: " + dbUsername + " (ID: " + userId + ")");
                return true;
            } else {
                System.out.println("Login failed - no matching record found");
                return false;
            }
        } catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public static int getUserIdByCredentials(String username, String password) {
        try {
            Connection connect = DatabaseConnection.getConnection();
            
            PreparedStatement statement = connect.prepareStatement("SELECT UserID FROM User WHERE Username = ? AND Password = ?");
            
            statement.setString(1, username);
            statement.setString(2, password);
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("UserID");
                System.out.println("User ID found: " + userId + " for username: " + username);
                return userId;
            } else {
                System.out.println("No user ID found for credentials");
                return -1;
            }
        } catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return -1;
        }
    }
    
   
    public static void testAllUsers() {
        try {
            Connection connect = DatabaseConnection.getConnection();
            
            PreparedStatement statement = connect.prepareStatement("SELECT * FROM User");
            ResultSet rs = statement.executeQuery();
            
            System.out.println("=== ALL USERS IN DATABASE ===");
            while (rs.next()) {
                System.out.println("UserID: " + rs.getInt("UserID") + 
                                 ", Username: '" + rs.getString("Username") + "'" +
                                 ", Password: '" + rs.getString("Password") + "'" +
                                 ", Email: '" + rs.getString("Email") + "'");
            }
            System.out.println("=== END USER LIST ===");
            
        } catch(SQLException e) {
            System.out.println("Error listing users: " + e.getMessage());
        }
    }
    
   
    public static String getUsernameById(int userId) {
        try {
            Connection connect = DatabaseConnection.getConnection();
         
            PreparedStatement statement = connect.prepareStatement("SELECT Username FROM User WHERE UserID = ?");
            
            statement.setInt(1, userId);
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("Username");
            }
        } catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }
    
    public static int getLanguageIdByUserId(int userId) {
        try {
            Connection connect = DatabaseConnection.getConnection();
         
            PreparedStatement statement = connect.prepareStatement("SELECT LanguageID FROM User WHERE UserID = ?");
            
            statement.setInt(1, userId);
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("LanguageID");
            }
        } catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return -1;
    }
}
