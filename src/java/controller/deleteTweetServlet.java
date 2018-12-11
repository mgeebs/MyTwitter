/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.User;
import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.Cookie; 
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Tweet;
import dataaccess.UserDB;
import dataaccess.TweetDB;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpSession;


@WebServlet(name = "deleteTweetServlet", urlPatterns = {"/deleteTweet"})
public class deleteTweetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
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
        
        String url = "/home.jsp";
        
        HttpSession session = request.getSession();
        String tweet_id = request.getParameter("tweet_id");
        
        User user = (User) session.getAttribute("user");
        String email = user.getEmailAddress();
        
        TweetDB tweetDB = new TweetDB();
        
        
        tweetDB.delete_tweet(tweet_id);

       List<Tweet> tweets = tweetDB.get_all_tweets(email);
       Collections.reverse(tweets); 

       session.setAttribute("tweets", tweets);
        
        getServletContext()
          .getRequestDispatcher(url)
          .forward(request, response);
     
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
