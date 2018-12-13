/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Follow;
import business.Tweet;
import business.User;
import dataaccess.FollowDB;
import dataaccess.TweetDB;
import java.io.IOException;
import javax.servlet.ServletException;
import java.util.Date;
import java.util.Calendar;
import javax.servlet.http.Cookie; 
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataaccess.UserDB;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import murach.util.MailUtilGmail;
import murach.util.PwHashingUtil;
   

    @WebServlet(name = "membershipServlet", urlPatterns = {"/membership"})
public class membershipServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    String url = "/login.jsp";
    
    HttpSession session = request.getSession(true); 

    String email = request.getParameter("email");
    request.setAttribute("email", email);
    String password = request.getParameter("password");
    request.setAttribute("password", password);
    String rememberMe = request.getParameter("remember_me");
        
    User user = UserDB.search_for_email(email);
    ArrayList<User> users = UserDB.select_all_users();
    request.setAttribute("users", users);
    session.setAttribute("users", users);
    for (Cookie cookie : request.getCookies()) {
        
        Cookie cook = new Cookie(cookie.getName(), "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cook);

        response.addCookie(cookie);
    };
    
    if (user == null){
        for (Cookie cookie : request.getCookies()) {
            
            if (cookie.getName() == "userName"){
                user = UserDB.search_for_email(email);
            }
            
        };
    }
    
    if(user == null){
        url = "/login.jsp";
        request.setAttribute("login_error", "User not found");
    }else{
        PwHashingUtil hasher = new PwHashingUtil();
        String userID = user.getUserID();
        String userHash = user.getPassword();
        if(hasher.checkPasswordHash(userHash, password, user.getSalt().getBytes())){
            url = "/home.jsp";
            
            session.setAttribute("user", user);
             
            if(rememberMe != null){
                Cookie email_cookie = new Cookie("user", email);
                email_cookie.setPath("/");
                response.addCookie(email_cookie);
                
                Cookie password_cookie = new Cookie("password", password);
                password_cookie.setPath("/");
                response.addCookie(password_cookie);
                
                Cookie remember_me = new Cookie("rememeber_me", "true");
                remember_me.setPath("/");
                response.addCookie(remember_me);

            }
            
            session.setAttribute("signed_in", "true");
            
            session.setAttribute("login_error", "");
            session.setAttribute("user", user);
            
            
            TweetDB tweetDB = new TweetDB();
            List<Tweet> tweets = tweetDB.get_all_tweets(user.getEmailAddress());
            
            if (tweets != null) {
            Collections.reverse(tweets); 
        
            session.setAttribute("tweets", tweets);
            
            }
                                                   
        } 
        else {
            url = "/login.jsp";
            session.setAttribute("login_error", "Password incorrect");
        }
    }
        
    getServletContext()
      .getRequestDispatcher(url)
      .forward(request, response);
    
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
        Date today = Calendar.getInstance().getTime();
        String date = sdf.format(today);
        
        String url = "/signup.jsp";
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User sessionUser = (User) session.getAttribute("user");
        String folEmail = request.getParameter("action1");
        String salt = request.getParameter("salt");
        
        if (action.equals("follow")) {
            User user = UserDB.search_for_email(sessionUser.getEmailAddress());
            email = user.getEmailAddress();
            Follow follow = new Follow(email, folEmail, date);
            FollowDB.insert(follow);
            url = "/home.jsp";
            session.setAttribute("user", user);
            request.setAttribute("email", email);
        }

        if (action.equals("unfollow")) {
            User user = UserDB.search_for_email(sessionUser.getEmailAddress());
            email = user.getEmailAddress();
            
            FollowDB.unfollow(email, folEmail);

            url = "/home.jsp";

            session.setAttribute("user", user);
            request.setAttribute("email", email);
            
        }
        if (action.equals("forgotPassword")) {
            
            request.setAttribute("salt", salt);
            url = updatePassword(request, response);
        }
        
        ArrayList<Follow> follows = FollowDB.searchByEmail(email);
        request.setAttribute("follows", follows);
        int followingCount = 0;
        followingCount = follows.size();
        request.setAttribute("followingCount", followingCount);

        ArrayList<Follow> followers = FollowDB.followers(email);
        int followersCount = 0;
        followersCount = followers.size();
        request.setAttribute("followersCount", followersCount);
        
        getServletContext().getRequestDispatcher(url).forward(request,response);
        
    }

    
    private String updatePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(); //get the current session
        String url = "/forgotpassword.jsp";
        String userEmail = request.getParameter("email");
        User userCheck = UserDB.search_for_email(userEmail);
        
        if (userCheck == null) {

            return url; //need to implement redirect/stuff saying user didn't exist
        }
        
        String questionCheck = request.getParameter("secquestion");
        String answerCheck = request.getParameter("secanswer");
        
        if (!questionCheck.equals(userCheck.getQuestionNo()) || (!answerCheck.equals(userCheck.getAnswer()))) {
            String errorMsg = "You selected the wrong security credentials";
            session.setAttribute("errorMsg", errorMsg);
            return url; //need to implement redirect/stuff saying the answer didn't match
        }
        String salt = userCheck.getSalt();
        PwHashingUtil hasher = new PwHashingUtil();
        String randomPass = createRandomPass(); //create a new password of 8 random characters
        String hashedPass = hasher.hashPassword(randomPass, salt.getBytes());
        User user = new User(); //create a new User object
        
        //set the user's credentials
        user.setPassword(hashedPass);
        user.setEmailAddress(userEmail);
        user.setSalt(createRandomPass());
        UserDB.update_password(user);
        session.setAttribute("user", user);
        
        String successfulMsg = "Email sent!";
        session.setAttribute("successful", successfulMsg);
        
        String fullName = userCheck.getFullName(); //will use this in the body of the email
        String recipientEmail = userEmail;
        String senderEmail = "candmtwitterteam@gmail.com";
        String emailSubject = fullName + " - Your MyTwitter password recovery";
        String emailBody = fullName + ",\n" + "Your temporary password is below:\n\n"
                + randomPass + "\n\n" + "Thank you, \n\nThe MyTwitter Team";
        boolean isBodyHtml = false;
        
        try {
        MailUtilGmail.sendMail(recipientEmail, senderEmail, emailSubject, emailBody, isBodyHtml);
        }
        catch (MessagingException e) {
            String errorMsg = "Error, unable to send the email: " + e.getMessage();
            this.log("Unable to send email to " + recipientEmail);
            session.setAttribute("errorMsg", errorMsg);
        }
        
        url = "/forgotpassword.jsp";
        return url;
        
    }
    
    public static String createRandomPass() {
        String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerChars = upperChars.toLowerCase();
        String numbers = "1234567890";
        
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int i = 0;
        // fill sb with random uppercase until there are 4 slots left to fill
        while (i < 2) {
            sb.append(upperChars.charAt(random.nextInt(upperChars.length())));
            i++;
        }
        //append some numbers to the string
        while (i < 4) {
            sb.append(numbers.charAt(random.nextInt(numbers.length())));
            i++;
        }
        // append lowercase chars to the string
        while (i < 6) {
            sb.append(lowerChars.charAt(random.nextInt(lowerChars.length())));
            i++;
        }
        //append some numbers to the string
        while (i < 8) {
            sb.append(numbers.charAt(random.nextInt(numbers.length())));
            i++;
        }
        
        return sb.toString();
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
