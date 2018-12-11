package murach.sql;

import murach.sql.ConnectionPool;
import business.User;
import dataaccess.UserDB;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

import java.util.Map;

public class SqlGatewayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        session.setAttribute("signed_in", false);
        session.setAttribute("user", null);
        Cookie[] cookies = request.getCookies();
        String password, fullName, emailAddress, userName, questionNo, answer, birthdate;
        password = fullName = emailAddress = userName = questionNo = answer = birthdate ="";
       
        
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("password")) {
                password = cookie.getValue();
                cookie.setPath("/");
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
            }
        }
        
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("fullName")) {
                fullName = cookie.getValue();
                cookie.setPath("/");
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
            }
        }
        
                
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("fullName")) {
                fullName = cookie.getValue();
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
            }
        }
        
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("emailAddress")) {
                emailAddress = cookie.getValue();
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
            }
        }
        
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("userName")) {
                userName = cookie.getValue();
                cookie.setMaxAge(-1);
                response.addCookie(cookie);

            }
        }
        
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("questionNo")) {
                questionNo = cookie.getValue();
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
            }
        }
        
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("answer")) {
                answer = cookie.getValue();
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
            }
        }
        
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("birthdate")) {
                birthdate = cookie.getValue();
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
            }
        }
 
        User user = new User(String.join(",", fullName, userName, emailAddress, password, birthdate, questionNo, answer));
        boolean db_result = UserDB.insert(user);
        
        session.setAttribute("user", user);
        
        String url = "";
        
        if (db_result == true) {
            session.setAttribute("signed_in", true);
            url = "/home.jsp";
        }
        else{
            request.setAttribute("error", "Email already in use");
            url = "/signup.jsp" ;
        }
                
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
}