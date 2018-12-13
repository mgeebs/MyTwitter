<%-- 
    Document   : login.jsp
    Created on : Sep 24, 2015, 6:44:58 PM
    Author     : xl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <%@ include file = "header.jsp" %>
    
    <body>
        <h2>Log in</h2>
        <p><c:out value="${login_error}"/></p>

        <form action=membership method="get">
            <div>
                <label class="pad_top"/>
                <input type="email" placeholder="email" name="email" required value="${user.getEmailAddress()}">
                <span id="fullname_error" class="notVisible"></span> <br>
                <label class="pad_top"/>
                <input type="password" placeholder="password" name="password" value="${user.getPassword()}" required>
                <span id="username_error" class="notVisible"></span> <br>
                <input type="submit" value="Log In" class="margin_left">
                
                <label><input type="checkbox" name="remember_me">Remember me</label>
                <a href="forgotpassword.jsp">Forgot Password</a><br>
            </div>
        </form>
        
        New?
        <a href="signup.jsp">Sign up now >></a>
        
        <%@ include file = "footer.jsp" %>

    </body>
</html>
