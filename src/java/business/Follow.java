/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

public class Follow {
    private int followId;    
    private String email;
    private String emailFollowing;
    private String date;
    


    public Follow(){
        email = " ";
        emailFollowing = " ";
        date = " ";
    }
    
    public Follow(String email, String emailFollowing, String date) {
        this.email = email;
        this.emailFollowing = emailFollowing;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailFollowing() {
        return emailFollowing;
    }

    public void setEmailFollowing(String emailFollowing) {
        this.emailFollowing = emailFollowing;
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
