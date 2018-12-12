/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.User;
import murach.sql.ConnectionPool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;


import java.sql.*;
import murach.util.DBUtil;

public class UserDB {
    
    public static boolean insert(User user) throws IOException 
    {  
        
        String sqlStatement = 
        "INSERT INTO twitterdb.user (fullname, username, emailAddress, "
        + "birthdate, password, questionNo, answer, salt) "
        + "VALUES ('%s', '%s', '%s', "
        + "'%s', '%s', '%s', '%s', '1234')";
        
        String sqlInsert = 
                String.format(sqlStatement, user.getFullName(), user.getUsername(),
                        user.getEmailAddress(), user.getBirthdate(), user.getPassword(),
                        user.getQuestionNo(), user.getAnswer()); 
        
        if (search_for_email(user.getEmailAddress()) != null){
            return false;
        }
        
        try {
            // load the driver
            Class.forName("com.mysql.jdbc.Driver");            
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            // create a statement
            Statement statement = connection.createStatement();

            // parse the SQL string
            sqlInsert = sqlInsert.trim();

            int i = statement.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                        
            ResultSet rs = statement.getGeneratedKeys();
            
            if (rs.next()) {
                user.setUserID(Integer.toString(rs.getInt(1)));
            }
            if (i == 0) { // a DDL statement
                return false;
            } else { // an INSERT, UPDATE, or DELETE statement
                return true;
            }
   
        } catch (SQLException e) {
            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }    
    }
    
    public static User search_for_email(String emailAddress) {
        try {
            // load the driver
            Class.forName("com.mysql.jdbc.Driver");   
            
            String query = "SELECT * FROM  twitterdb.user WHERE emailAddress = '" + emailAddress + "'";
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            // create a statement
            Statement statement = connection.createStatement();

            // parse the SQL string
            query = query.trim();

            ResultSet resultSet = statement.executeQuery(query);
            User user = new User();
            
            if (!resultSet.isBeforeFirst() ) {    
                return null; 
            } 
            
            if (resultSet.next()) {
                user.setUserID(resultSet.getString(1));
                user.setFullName(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setEmailAddress(resultSet.getString(4));
                user.setBirthdate(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                user.setQuestionNo(resultSet.getString(7));
                user.setAnswer(resultSet.getString(8));                
                return user;
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } catch (ClassNotFoundException ex) {
            return null;
        }   
    }
    
    public static User search_for_userID(String userID) 
    {
        try {
            // load the driver
            Class.forName("com.mysql.jdbc.Driver");   
            
            String query = "SELECT * FROM  twitterdb.user WHERE userID = '" + userID + "'";
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            // create a statement
            Statement statement = connection.createStatement();

            // parse the SQL string
            query = query.trim();

            ResultSet resultSet = statement.executeQuery(query);
            User user = new User();
            
            if (!resultSet.isBeforeFirst() ) {    
                return null; 
            } 
            
            if (resultSet.next()) {
                user.setUserID(resultSet.getString(1));
                user.setFullName(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setEmailAddress(resultSet.getString(4));
                user.setBirthdate(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                user.setQuestionNo(resultSet.getString(7));
                user.setAnswer(resultSet.getString(8));                
                return user;
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } catch (ClassNotFoundException ex) {
            return null;
        }   
    }
    
    public static String usernameToUserID(String userName) 
    {
        try {
            // load the driver
            Class.forName("com.mysql.jdbc.Driver");   
            
            String query = "SELECT userID FROM twitterdb.user WHERE userName = '" + userName + "'";
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            // create a statement
            Statement statement = connection.createStatement();

            // parse the SQL string
            query = query.trim();

            ResultSet resultSet = statement.executeQuery(query);
            
            if (!resultSet.isBeforeFirst()) {    
                return null; 
            } 
            
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } catch (ClassNotFoundException ex) {
            return null;
        }   
    }
    
    public static int update_password(User user) 
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
       
        String query = "Update User " +
               "set password = ? " +
               "where emailaddress = ?";
        int result = 0;
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getEmailAddress());
            
            
            result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        finally
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return result;
    }
    
    public static User search_for_username(String username) 
    {
        
        return null;
    }
    
}
