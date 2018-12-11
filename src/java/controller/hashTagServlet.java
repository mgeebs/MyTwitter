/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.Hashtag;
import business.Mention;
import business.User;
import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Tweet;
import dataaccess.HashTagDB;
import dataaccess.MentionDB;
import dataaccess.UserDB;
import dataaccess.TweetDB;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.jsoup.Jsoup;


@WebServlet(name = "hashTagServlet", urlPatterns = {"/hashTag"})
public class hashTagServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    HttpSession session = request.getSession(true); 
    String hashText = request.getParameter("h");
    session.setAttribute("hashTag", hashText);
    
    List<Tweet> tweets = HashTagDB.get_all_tweet_with_hash(hashText);
    
    if (tweets != null)
        Collections.reverse(tweets); 
    
    session.setAttribute("tweets", tweets);
    
//        String search = request.getParameter("hashTag");
        
//        getServletContext()
//          .getRequestDispatcher("/hashTag.jsp")
//          .forward(request, response);
// 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    
//    protected Set getMentions(String text){
//        
//        String newMessage = text;
//        int startInd = 0;
//        
//        Set mentionedUsers = new HashSet<String>();
//        
//        while (text.indexOf("@", startInd) != -1) {
//            int indexOf = text.indexOf("@", startInd);
//        
//            int indexOfSpace = text.indexOf(" ", indexOf+1);
//            if(indexOfSpace == -1)
//                indexOfSpace = text.length();
//            
//            String mention = text.substring(indexOf, indexOfSpace);
//            mentionedUsers.add(mention);
//            
//            startInd = indexOf+1;
//        }
//        
//        return mentionedUsers;
//        
//    }
//    
//    protected Set getHashTags(String text){
//        
//        int startInd = 0;
//        String newMessage = text;
//        
//        Set hashTags = new HashSet<String>();
//
//        while(text.indexOf("#", startInd) != -1) {
//            int indexOf = text.indexOf("#", startInd);
//        
//            int indexOfSpace = text.indexOf(" ", indexOf+1);
//            if(indexOfSpace == -1)
//                indexOfSpace = text.length();
//            
//            String hashtag = text.substring(indexOf, indexOfSpace);
//            hashTags.add(hashtag);
//
//            newMessage = newMessage.replace(hashtag, "<font color=\"blue\">" + hashtag + "</font>");
//            startInd = indexOf+1;
//        }
//        
//        return hashTags;
//    }
//    
//    protected String updateText(String text){
//        
//        int startInd = 0;
//        String newMessage = text;
//
//        while (text.indexOf("@", startInd) != -1) {
//            int indexOf = text.indexOf("@", startInd);
//            int indexOfSpace = text.indexOf(" ", indexOf+1);
//            if(indexOfSpace == -1)
//                indexOfSpace = text.length();
//            String mention = text.substring(indexOf, indexOfSpace);            
//            newMessage = newMessage.replace(mention, "<font color=\"blue\">" + mention + "</font>");
//            startInd = indexOf+1;
//        }
//        
//        startInd = 0;
//        while(text.indexOf("#", startInd) != -1) {
//            int indexOf = text.indexOf("#", startInd);
//        
//            int indexOfSpace = text.indexOf(" ", indexOf+1);
//            if(indexOfSpace == -1)
//                indexOfSpace = text.length();
//            String hashtag = text.substring(indexOf, indexOfSpace);
//            newMessage = newMessage.replace(hashtag, "<font color=\"blue\">" + hashtag + "</font>");
//            startInd = indexOf+1;
//        }
//        
//        return newMessage;
//    }
//    
//    protected void updateMentions(Set mentionedUsers, Tweet tweet)  
//            throws IOException {
//        
//        for (Object mentionUsername: mentionedUsers ){
//            String mentionString = (String) mentionUsername;
//            mentionString = mentionString.replace("@", "");
//            String mentionedID = UserDB.usernameToUserID(mentionString);
//
//            if (mentionedID != null && !mentionedID.equals(tweet.getUserID())){
//                Mention mentionClass = new Mention(String.join(",", mentionedID, tweet.getTweetID()));
//                MentionDB.insert(mentionClass);
//            }
//        }   
//    }
//    
//    protected void updateHashtags(Set hashTags, Tweet tweet,  HttpSession session) throws IOException{
//        Hashtag hashTagClass;
//
//        for (Object hashTag: hashTags ){
//            String hashTagString = (String) hashTag;
//            hashTagString = hashTagString.replace("#", "");
//            hashTagClass = new Hashtag(hashTagString, tweet.getTweetID());
//            HashTagDB.insert(hashTagClass);
//        }
//    }
//    
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        String url = "/home";
//        
//        HttpSession session = request.getSession();
//        String text = request.getParameter("tweettext");        
//        text = Jsoup.parse(text).text();
//        
//        User user = (User) session.getAttribute("user");
//        
//        //TODO 
//        String email = user.getEmailAddress();
//        String userID = user.getUserID();
//        String username = user.getUsername();
//        
//        Set mentionedUsers = getMentions(text);
//        Set hashTags = getHashTags(text);
//        
//        text = updateText(text);
//        
//        Tweet tweet = new Tweet(userID, text, username);
//        TweetDB.insert(tweet);
//        
//        updateMentions(mentionedUsers, tweet);
//        updateHashtags(hashTags, tweet, session);
//        
//        session.setAttribute("test", request.getMethod());
//                
//        getServletContext()
//          .getRequestDispatcher(url)
//          .forward(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>

}
