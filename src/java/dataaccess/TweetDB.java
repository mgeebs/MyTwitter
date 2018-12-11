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
import murach.util.DBUtil;

public class TweetDB {
    
    public static boolean insert(Tweet tweet) throws IOException 
    {  
                
        String sqlStatement = 
        "INSERT INTO twitterdb.tweets (userID, tweet, username) "
        + "VALUES ('%s', '%s', '%s');";
        
        String sqlInsert = 
                String.format(sqlStatement, tweet.getUserID(), tweet.getTweet(), tweet.getUsername());
        
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

            int i = statement.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = statement.getGeneratedKeys();
            
            if (rs.next()) {
                tweet.setTweetID( Integer.toString(rs.getInt(1)));
            }
            
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
            if (connection != null)
                pool.freeConnection(connection);
            if(statement != null)
                DBUtil.closeStatement(statement);
        }
    }
    
    public static boolean delete_tweet(String tweet_id) throws IOException 
    {  
                
        String query = 
        "DELETE FROM  twitterdb.tweets WHERE tweetID="+ tweet_id;
        
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
            if (statement != null)
                DBUtil.closeStatement(statement);
            if (connection != null)
                pool.freeConnection(connection);
        }
    }
    
    public static List<Tweet> get_all_tweets(String userID) 
    {
        ConnectionPool pool = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            // load the driver
            Class.forName("com.mysql.jdbc.Driver");   
            
            String query = "SELECT tweetID, userID, tweet, username  FROM  twitterdb.tweets WHERE userID = '" + userID + "'";
            
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();

            // create a statement
            statement = connection.createStatement();

            // parse the SQL string
            query = query.trim();

            resultSet = statement.executeQuery(query);
            List<Tweet> results = new ArrayList<Tweet>();
                        
            if (!resultSet.isBeforeFirst() ) {    
                return null; 
            } 
            
            Tweet temp_tweet = new Tweet(); 
            
            while(resultSet.next()) {   
                temp_tweet = new Tweet(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                temp_tweet.setTweetID(resultSet.getString(1));
                results.add(temp_tweet);   
            }
            
            DBUtil.closeResultSet(resultSet);
            pool.freeConnection(connection);
            
            return results;
            
        } catch (SQLException e) {
            return null;
        } catch (ClassNotFoundException ex) {
            return null;
        } finally {
            if (statement != null)
                DBUtil.closeStatement(statement);
            if (connection != null)
                pool.freeConnection(connection);
            if (resultSet != null)
                DBUtil.closeResultSet(resultSet);
        }
    }
    
    public static String search_for_username(String username) 
    {
        return null;
    }
    
}
