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
    private int tweetID;
    private String emailAddress;
    private String tweet;
    private String mention;

    public Tweet()
    {
        emailAddress = "";
        tweet = "";
        mention = "";

    }
    
    public Tweet(String emailAddress, String tweet)
    {
        this.setEmailAddress(emailAddress);
        this.setTweet(tweet);
    }
    
    public String getEmailAddress()
    {
        return this.emailAddress;
    }
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }
    
    public String getTweet()
    {
        return this.tweet;
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
    
    public int getTweetID()
    {
        return this.tweetID;
    }
    public void setTweetID(int tweetID)
    {
        this.tweetID = tweetID;
    }
    
    
    
}
