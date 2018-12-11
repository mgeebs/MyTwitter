/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Tweet;
import murach.sql.ConnectionPool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;


import java.sql.*;

public class TweetDB {
    
    public static boolean insert(Tweet tweet) throws IOException 
    {  
                
        String sqlStatement = 
        "INSERT INTO twitterdb.tweets (emailAddress, tweet) "
        + "VALUES ('%s', '%s')";
        
        String sqlInsert = 
                String.format(sqlStatement, tweet.getEmailAddress(), tweet.getTweet());
        
        try {
//             load the driver
            Class.forName("com.mysql.jdbc.Driver");            
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

//             create a statement
            Statement statement = connection.createStatement();

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
        }    
    }
    
    public static boolean delete_tweet(String tweet_id) throws IOException 
    {  
                
        String query = 
        "DELETE FROM  twitterdb.tweets WHERE tweetID="+ tweet_id;
        
        try {
//             load the driver
            Class.forName("com.mysql.jdbc.Driver");            
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

//             create a statement
            Statement statement = connection.createStatement();

//             parse the SQL string
            query = query.trim();

            int i = statement.executeUpdate(query);
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
    
    public static List<Tweet> get_all_tweets(String userEmail) 
    {
       
        try {
            // load the driver
            Class.forName("com.mysql.jdbc.Driver");   
            
            String query = "SELECT * FROM  twitterdb.tweets WHERE emailAddress = '" + userEmail + "'";
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            // create a statement
            Statement statement = connection.createStatement();

            // parse the SQL string
            query = query.trim();

            ResultSet resultSet = statement.executeQuery(query);
            List<Tweet> results = new ArrayList<Tweet>();;
            
            if (!resultSet.isBeforeFirst() ) {    
                return null; 
            } 
            
            Tweet temp_tweet = new Tweet(); 
            
            while(resultSet.next()) {   
                temp_tweet = new Tweet(resultSet.getString(2), resultSet.getString(3));
                temp_tweet.setTweetID(resultSet.getInt(1));
                results.add(temp_tweet);   
            }
            
            return results;
            
        } catch (SQLException e) {
            return null;
        } catch (ClassNotFoundException ex) {
            return null;
        }   
    }
    
    public static String search_for_username(String username) 
    {
        
        return null;
    }
    
}
