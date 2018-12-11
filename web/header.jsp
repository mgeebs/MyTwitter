<%-- 
    Document   : header.jsp
    Created on : Sep 24, 2015, 6:47:09 PM
    Author     : xl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css" />
        <title>JSP Page</title>
    </head>   
    
    <c:if test="${sessionScope.signed_in  == true}">
        <form action=home method="get">
            <input type="submit" value="Home" id="home">
        </form>
        
        <form action=notification method="get">
            <input type="submit" value="Notifications" id="notifications">
        </form>

        <form action=profile method="get">
            <input type="submit" value="Profile" id="profile">
        </form>
<!--            <button onclick="/signup.jsp" id="profile">profile</button>       -->
        <button class="tablink" id ="header_bar">Twitter</button>
        
        <form action=signout method="get">
            <input type="submit" value="signout" id="signout">
        </form>
        
    </c:if>
</html>
