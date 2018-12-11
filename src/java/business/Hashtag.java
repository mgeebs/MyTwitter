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
public class Hashtag implements Serializable {
    //define attributes fullname, ...
    
    //define set/get methods for all attributes.
    private String hashtagID;
    private String tweetID;
    private String hashtagText;

    public Hashtag()
    {
        hashtagID = "";
        tweetID = "";
        hashtagText = "";
    }
    public Hashtag(String hashTag, String tweetID)
    {
        this.setHashtagText(hashTag);
        this.setTweetID(tweetID);
    }
    
    public String getTweetID(){
        return this.tweetID;
    }
    
    public void setTweetID(String tweetID){
        this.tweetID = tweetID;
    }
    
    public String getHashtagID(){
        return this.hashtagID;
    }
    
    public void setHashtagID(String hashtagID){
        this.hashtagID = hashtagID;
    }
    
    public String getHashtagText(){
        return this.hashtagText;
    }
    
    public void setHashtagText(String hashtagText)
    {
        this.hashtagText = hashtagText;
    }
 
}
