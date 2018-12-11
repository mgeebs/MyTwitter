/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Mention;
import murach.sql.ConnectionPool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;


import java.sql.*;
import murach.util.DBUtil;

public class MentionDB {
    
    public static boolean insert(Mention mention) throws IOException 
    {  
                
        String sqlStatement = 
        "INSERT INTO twitterdb.mentions (mentionedID, tweetID) "
        + "VALUES ('%s', '%s');";
        
        String sqlInsert = 
                String.format(sqlStatement, mention.getMentionedID(), mention.getTweetID());
        
        ConnectionPool pool = null;
        Connection connection = null;
        Statement statement = null;
        
        try {
//             load the driver
            Class.forName("com.mysql.jdbc.Driver");            
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();
//             create a statement
            statement = connection.createStatement();

//             parse the SQL string
            sqlInsert = sqlInsert.trim();

            int i = statement.executeUpdate(sqlInsert);


            if (i == 0) { // a DDL statement
                return false;
            } else { // an INSERT, UPDATE, or DELETE statement
                return true;
            }
   
        } catch (SQLException e) {
            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        } finally {
            if (connection != null)
                pool.freeConnection(connection);
            if(statement != null)
                DBUtil.closeStatement(statement);
        }
    }
    
    public static boolean delete_all_mentions(String tweetID) throws IOException 
    {  
                
        String query = 
        "DELETE FROM twitterdb.mentions WHERE tweetID="+ tweetID;
        
        ConnectionPool pool = null;
        Connection connection = null;
        Statement statement = null;
        
        try {
//             load the driver
            Class.forName("com.mysql.jdbc.Driver");            
            
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();

//             create a statement
            statement = connection.createStatement();

//             parse the SQL string
            query = query.trim();

            int i = statement.executeUpdate(query);

            if (i == 0) {
                return false;
            } else {
                return true;
            }
   
        } catch (SQLException e) {
            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        } finally {
            if (statement != null)
                DBUtil.closeStatement(statement);
            if (connection != null)
                pool.freeConnection(connection);
        }
    }
   
}
