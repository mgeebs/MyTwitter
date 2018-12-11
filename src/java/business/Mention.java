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
public class Mention implements Serializable {
    //define attributes fullname, ...
    
    //define set/get methods for all attributes.
    private String mentionedID;
    private String tweetID;

    public Mention()
    {
        mentionedID = "";
        tweetID = "";

    }
    public Mention(String fromString)
    {
        String[] data = fromString.replace("[", "").split(",");
        this.setMentionedID(data[0]);
        this.setTweetID(data[1]);
    }
    
    public String getMentionedID(){
        return this.mentionedID;
    }
    
    public void setMentionedID(String mentionedID){
        this.mentionedID = mentionedID;
    }
    
    public String getTweetID(){
        return this.tweetID;
    }
    
    public void setTweetID(String tweetID)
    {
        this.tweetID = tweetID;
    }
 
}
