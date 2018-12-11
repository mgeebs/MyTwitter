/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import java.io.Serializable;

/**
 *
 * @javabean for User Entity
 */
public class Tweet implements Serializable {
    //define attributes fullname, ...
    
    //define set/get methods for all attributes.
    private String tweetID;
    private String userID;
    private String tweet;
    private String mention;
    private String username;

    public Tweet()
    {
        userID = "";
        tweet = "";
        mention = "";
        username = "";

    }
    
    public Tweet(String userID, String tweet, String username)
    {
        this.setUserID(userID);
        this.setTweet(tweet);
        this.setUsername(username);
    }
    
    public String getUserID()
    {
        return this.userID;
    }
    public void setUserID(String userID)
    {
        this.userID = userID;
    }
    
    public String getTweet()
    {
        return this.tweet;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getUsername()
    {
        return this.username;
    }
    
    public void setTweet(String tweet)
    {  
        this.tweet = tweet;
    }
    
    public String getMention()
    {
        return this.mention;
    }
    public void setMention(String mentions)
    {
        this.mention = mentions;
    }
    
    public String getTweetID()
    {
        return this.tweetID;
    }
    public void setTweetID(String tweetID)
    {
        this.tweetID = tweetID;
    }
    
    
    
}
