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
public class User implements Serializable {
    //define attributes fullname, ...
    
    //define set/get methods for all attributes.
    private String userID;
    private String fullName;
    private String username;
    private String emailAddress;
    private String password;
    private String birthdate;
    private String questionNo;
    private String answer;
    private String salt;

    public User()
    {
        userID = "";
        fullName = "";
        username = "";
        emailAddress = "";
        password = "";
        birthdate = "";
        questionNo = "";
        answer = "";
        salt = "";
    }
    public User(String fromString)
    {
        String[] data = fromString.replace("[", "").split(",");
        this.setFullName(data[0]);
        this.setUsername(data[1]);
        this.setEmailAddress(data[2]);
        this.setPassword(data[3]);
        this.setBirthdate(data[4]);
        this.setQuestionNo(data[5]);
        this.setAnswer(data[6]);
        this.setSalt(data[7]);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public String getUserID(){
        return this.userID;
    }
    
    public void setUserID(String userID){
        this.userID = userID;
    }
    
    public String getFullName(){
        return this.fullName;
    }
    
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
    
    public String getUsername()
    {
        return this.username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getEmailAddress()
    {
        return this.emailAddress;
    }
    public void setEmailAddress(String email)
    {
        this.emailAddress = email;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }    
    
    public String getBirthdate()
    {
        return this.birthdate;
    }
    public void setBirthdate(String birthdate)
    {
        this.birthdate = birthdate;
    }
    
    public String getQuestionNo()
    {
        return this.questionNo;
    }
    public void setQuestionNo(String questionNo)
    {
        this.questionNo = questionNo;
    }  
    
    public String getAnswer()
    {
        return this.answer;
    }
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }    
 
}
