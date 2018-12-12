/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

public class Follow {
    private int followId;    
    private String email;
    private String followedEmail;
    private String date;
    


    public Follow(){
        email = " ";
        followedEmail = " ";
        date = " ";
    }
    
    public Follow(String email, String followedEmail, String date) {
        this.email = email;
        this.followedEmail = followedEmail;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFollowedEmail() {
        return followedEmail;
    }

    public void setFollowedEmail(String followedEmail) {
        this.followedEmail = followedEmail;
    }

       public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
