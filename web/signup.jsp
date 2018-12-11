<!DOCTYPE html>
 
<!--    Created on : Sep 19, 2018, 10:01:56 PM
    Author     : michaelhedrick-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <jsp:include page="header.jsp" />
    
    <head>
        <title>Michael Hedrick's MiniTwitter</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <script src="main.js" type="text/javascript"></script>
    </head>
    <body>
    <h1>Register for MiniTwitter!</h1>
    <p>Enter your information below</p>
    <br>
    <c:out value ="${error}"/>
    <br>
<form action="sqlGateway" method="post">
        <div id="errordiv" class="notVisible"></div>
    <div>
        <label class="pad_top">Full Name:</label>
        <input type="text" name="fullname" id="fullname" value="${user.getFullName()}" required>
        <span id="fullname_error" class="notVisible">*</span> <br>
        <label class="pad_top">Username:</label>
        <input type="text" id="username" value="${user.getUsername()}" required>
        <span id="username_error" class="notVisible">*</span> <br>
        <label class="pad_top">Email:</label>
        <input type="email" id="email" value="${user.getEmailAddress()}" required>
        <span id="email_error" class="notVisible">*</span> <br>
        <label class="pad_top">Password:</label>
        <input type="password" id="password" required>
        <span id="password_error" class="notVisible">*</span> <br>
        <label class="pad_top">Confirm Password:</label>
        <input type="password" id="confirmpassword" required>
        <span id="confirmpassword_error" class="notVisible">*</span> <br>
        <label class="pad_top">Date of birth:</label>
        <input type="date" id="dateofbirth" value="${user.getBirthdate()}" required>
        <span id="dateofbirth_error" class="notVisible">*</span> <br>
    </div>
        <label>&nbsp;</label>
        <br><br><br>
        <div class="securityquestions">
             <select id="securitybox" required onchange="getSecQuestion()" selected="${user.getQuestionNo()}">
                 <option selected value="0">Security Question:</option>
                 <option value="1">What was the name of your first pet?</option>
                 <option value="2">What was the make and model of your first car?</option>
                 <option value="3">What was the name of your first school?</option>
             </select>
        </div>
        <label class="notVisible" id="secanswerbox">Security Answer:</label>
        <input type="text" class="notVisible" id="securityanswer" value="${user.getAnswer()}" required>
        <br><br><br>
        <input type="button" value="Join Now" class="margin_left"
               Onclick="return validateForm()">
        <br>
    </form>
    
    <%@ include file = "footer.jsp" %>

</body>
</html>
