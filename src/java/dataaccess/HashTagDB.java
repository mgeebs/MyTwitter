/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import business.Hashtag;
import business.Tweet;
import murach.sql.ConnectionPool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;


import java.sql.*;
import murach.util.DBUtil;

public class HashTagDB {
    
    private static boolean hashTagExists(Hashtag hashtag) throws IOException {
        ConnectionPool pool = null;
        Connection connection = null;
        Statement statement = null;
        
        try{
            String sqlStatement = 
                "SELECT * FROM twitterdb.hashtag where hashtagText='%s'";
            
            Class.forName("com.mysql.jdbc.Driver");            
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();
            statement = connection.createStatement();
            String sqlInsert = 
                String.format(sqlStatement, hashtag.getHashtagText());
            sqlInsert = sqlInsert.trim();

            ResultSet i = statement.executeQuery(sqlInsert);
            
            if (!i.isBeforeFirst()) {    
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
    
    private static boolean insertHashtag(Hashtag hashtag){
        ConnectionPool pool = null;
        Connection connection = null;
        Statement statement = null;
        String sqlStatement = "";
        
        try{
            if (hashTagExists(hashtag)){
                sqlStatement = 
                    "UPDATE twitterdb.hashtag SET hashtagCount = hashtagCount + 1 WHERE hashtagText='%s';";
            } else {
                sqlStatement = 
                    "INSERT INTO twitterdb.hashtag (hashtagText, hashtagCount) VALUES ('%s', '1');"; 
            }
            
            Class.forName("com.mysql.jdbc.Driver");            
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();
            statement = connection.createStatement();
            String sqlInsert = 
                String.format(sqlStatement, hashtag.getHashtagText());
            sqlInsert = sqlInsert.trim();

            int i = statement.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                hashtag.setHashtagID(Integer.toString(rs.getInt(1)));
            }

            if(i == 0){
                return false;
            } else {
                return true;
            }
            
        } catch (SQLException e) {
            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        } catch (IOException io){
            return false;
        } finally {
            if (connection != null)
                pool.freeConnection(connection);
            if(statement != null)
                DBUtil.closeStatement(statement);
        }
        
    }
    
    private static String getHashTagIDFromText(String hashtag){
        try {
            // load the driver
            Class.forName("com.mysql.jdbc.Driver");   
            
            String query = "SELECT hashtagID FROM twitterdb.hashtag WHERE hashtagText = '" + hashtag + "'";
            
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
    
    private static boolean insertTweetHashtag(Hashtag hashtag){
        ConnectionPool pool = null;
        Connection connection = null;
        Statement statement = null;
        
        try{
            String sqlStatement = 
                "INSERT INTO twitterdb.tweetHashtag (tweetID, hashtagID) VALUES ('%s', '%s');";
            
            Class.forName("com.mysql.jdbc.Driver");            
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();
            statement = connection.createStatement();
            String sqlInsert = 
                String.format(sqlStatement, hashtag.getTweetID(), getHashTagIDFromText(hashtag.getHashtagText()));
            sqlInsert = sqlInsert.trim();

            int i = statement.executeUpdate(sqlInsert);
            
            if(i == 0){
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
    
    public static boolean insert(Hashtag hashtag) throws IOException 
    {             
        if(insertHashtag(hashtag) && insertTweetHashtag(hashtag)) // && insertTweetHashtag(hashtag))
            return true;
        else
            return false;
    }
//    
//    public static boolean delete_tweet(String tweet_id) throws IOException 
//    {  
//                
//        String query = 
//        "DELETE FROM  twitterdb.tweets WHERE tweetID="+ tweet_id;
//        
//        ConnectionPool pool = null;
//        Connection connection = null;
//        Statement statement = null;
//        
//        try {
////             load the driver
//            Class.forName("com.mysql.jdbc.Driver");            
//            
//            pool = ConnectionPool.getInstance();
//            connection = pool.getConnection();
//
////             create a statement
//            statement = connection.createStatement();
//
////             parse the SQL string
//            query = query.trim();
//
//            int i = statement.executeUpdate(query);
//
//            if (i == 0) { // a DDL statement
//                return false;
//            } else { // an INSERT, UPDATE, or DELETE statement
//                return true;
//            }
//   
//        } catch (SQLException e) {
//            return false;
//        } catch (ClassNotFoundException ex) {
//            return false;
//        } finally {
//            if (statement != null)
//                DBUtil.closeStatement(statement);
//            if (connection != null)
//                pool.freeConnection(connection);
//        }
//    }
    
//    
    public static List<Tweet> get_all_tweet_with_hash(String hashTag) 
    {
        ConnectionPool pool = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            // load the driver
            Class.forName("com.mysql.jdbc.Driver");   
            
            String query = "select t.tweetID, t.userID, t.tweet, t.username\n" +
                                "from twitterdb.tweets as t\n" +
                                    "join twitterdb.tweetHashtag as th on t.tweetID = th.tweetID\n" +
                                "where th.hashtagID = '%s'";
            
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();

            // create a statement
            statement = connection.createStatement();
            
            query = 
                String.format(query, getHashTagIDFromText(hashTag));

            // parse the SQL string
            query = query.trim();

            resultSet = statement.executeQuery(query);
            if (!resultSet.isBeforeFirst() ) {    
                return null; 
            } 
            
            List<Tweet> results = new ArrayList<Tweet>();
            
            Tweet temp_tweet = new Tweet(); 
            
            while(resultSet.next()) {   
                temp_tweet = new Tweet(resultSet.getString(1), resultSet.getString(3), resultSet.getString(4));
                results.add(temp_tweet);   
            }
            
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
    
    public static List<String> getTrending(){
        ConnectionPool pool = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        
        try {
            // load the driver
            Class.forName("com.mysql.jdbc.Driver");   
            
            String query = "SELECT hashtagText\n" +
                                "FROM twitterdb.hashtag\n" +
                                "ORDER BY hashtagCount DESC\n" +
                                "LIMIT 10";
            
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();

            // create a statement
            statement = connection.createStatement();
            
            // parse the SQL string
            query = query.trim();

            resultSet = statement.executeQuery(query);
            if (!resultSet.isBeforeFirst() ) {    
                return null; 
            } 
            
            List<String> results = new ArrayList<String>();
            
            while(resultSet.next()) {   
                results.add(resultSet.getString(1));   
            }
            
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
    
    public static void delete_all_hash_tag(String userID, HttpSession session){
        ConnectionPool pool = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        
        
        try {
            // load the driver
            Class.forName("com.mysql.jdbc.Driver");
            
            String query = 
                    "SELECT hashtagID from twitterdb.tweetHashtag where tweetID='%s';";
            
            query = query.format(query, userID);
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();
            statement = connection.createStatement();
            query = query.trim();
            ResultSet rs = statement.executeQuery(query);
            
            List<String> results = new ArrayList<>();
            
            while(rs.next()) {   
                results.add(rs.getString(1));  
            }
//            session.setAttribute("query", getHashTagIDFromText(rs.getString(1)));

            DBUtil.closeResultSet(resultSet);

            query = 
                    "UPDATE twitterdb.hashtag SET hashtagCount = hashtagCount - 1 WHERE hashtagID='0'"; //this is a hack
            
            for (String decrementHashID: results){
                query += " or hashtagID='" + decrementHashID + "'";
            }
            
            
            DBUtil.closeStatement(statement);
            connection = pool.getConnection();
            statement = connection.createStatement();
            query = query.trim();
            statement.executeUpdate(query);
            
            query = 
                    "DELETE FROM twitterdb.tweetHashtag WHERE tweetID="+ userID;
            
            DBUtil.closeStatement(statement);
            pool.freeConnection(connection);
            connection = pool.getConnection();
            statement = connection.createStatement();
            query = query.trim();
            statement.executeUpdate(query);
            
        } catch (SQLException e) {
            return;
        } catch (ClassNotFoundException ex) {
            return;
        } finally {
            if (statement != null)
                DBUtil.closeStatement(statement);
            if (connection != null && pool != null)
                pool.freeConnection(connection);
            if (resultSet != null)
                DBUtil.closeResultSet(resultSet);
        }
    }
}
