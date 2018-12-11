/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Tweet;
import business.User;
import dataaccess.UserDB;
import dataaccess.TweetDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.Cookie; 
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataaccess.UserDB;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import murach.util.MailUtilGmail;

   

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
    
    if(user == null){
        url = "/login.jsp";
        request.setAttribute("login_error", "User not found");
    }else{
        String userID = user.getUserID();
        
        if(user.getPassword().equals(password)){
            url = "/home";
            
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
        String action = request.getParameter("action");
        String url = "/signup.jsp";
        
        if (action.equals("forgotPassword")) {
            url = updatePassword(request, response);
        }
        
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
        
        String randomPass = createRandomPass(); //create a new password of 8 random characters
        
        User user = new User(); //create a new User object
        
        //set the user's credentials
        user.setPassword(randomPass);
        user.setEmailAddress(userEmail);
       
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
    
    private String createRandomPass() {
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
