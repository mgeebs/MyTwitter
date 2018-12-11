<%-- 
    Document   : forgotpassword
    Created on : Oct 12, 2018, 1:53:07 PM
    Author     : Cole
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="main.js" type="text/javascript"></script>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <title>Forgot Password</title>
        <br/>
        <success>${successMsg}</success>
        <c:remove var="successMsg" scope="session"/>
        <error>${errorMsg}</error>
        <c:remove var="errorMsg" scope="session"/>
    </head>
    <body>
        <form action="membership" method="post" >
            <p> Fill in the following to receive a temporary password. </p>
            <div id="errordiv" class="notVisible"></div>
            <input type="email" name="email" placeholder="Email" required/>
            <br/>
            <input type="hidden" name="action" value="forgotPassword">
            <br/>
            
            <select class = "securityquestions" name="secquestion" required onchange="getSecQuestion()" selected="${user.getQuestionNo()}">
                 <option selected value="0">Security Question:</option>
                 <option value="1">What was the name of your first pet?</option>
                 <option value="2">What was the make and model of your first car?</option>
                 <option value="3">What was the name of your first school?</option>
            </select>
                 <br/>
        <input type="text" class="notVisible" name="secanswer" value="${user.getAnswer()}" required>
        <br/>
        <input class="registerbutton" type="submit" value="Submit" id="btnSubmit" class="margin_left">
            <br/>
        </form>
    </body>
    <c:import url="footer.jsp"/>
</html>
