
/* 
    Created on : Sep 19, 2018, 10:01:56 PM
    Author     : michaelhedrick

    File for containing validation functions of form field entries
*/

/* global birthdate */

function validateForm() {

    //get the values from the form
    var password = document.getElementById('password');
    var confirmPassword = document.getElementById('confirmpassword');
    var fullName = document.getElementById("fullname");
    var email = document.getElementById("email");
    var userName = document.getElementById("username");
    var securitybox = document.getElementById("securitybox");
    var secanswerbox = document.getElementById("secanswerbox");
    var securityanswer = document.getElementById("securityanswer");

    var dateofbirth = document.getElementById("dateofbirth");
    
    var dateofbirth_error = document.getElementById("dateofbirth_error");
    var passError = document.getElementById("password_error");
    var confError = document.getElementById("confirmpassword_error");
    var fullnameError = document.getElementById("fullname_error");
    var usernameError = document.getElementById("username_error");
    var emailError = document.getElementById("email_error");
    var errorMsg = document.getElementById("errordiv");
    var successMsg = document.getElementById("successdiv"); 

    //Check 1 - make sure password and confirmpassword match
    if (password.value !== confirmPassword.value) {
        //make the field yellow
        password.style.backgroundColor = "yellow";
        confirmPassword.style.backgroundColor = "yellow";
        
        //make the password error spans visible and display the error
        
        confError.style.display = "inline";
        confError.className = "isVisible";
        passError.style.display = "inline";
        passError.className = "isVisible";
        errorMsg.className = "isVisible";
        errorMsg.innerHTML = "error! password and confirm password do not match!";
        
        return false;
    }
    //change back to white if they match
    else {
       errorMsg.className = "notVisible";
       passError.className = "notVisible";
       confError.className = "notVisible";
       confirmPassword.style.backgroundColor = "white";
       password.style.backgroundColor = "white";
   }
   
   //Check 2 - Check whether the full name is one word or multiple words
   // if index of a space is less than 0, then the space doesn't exist and the
   // name must be one word
   if (fullName.value.indexOf(" ") < 0) {
       errorMsg.className = "isVisible";
       //errorMsg.innerHTML = "full name is not valid";
       fullnameError.className = "isVisible";
       fullName.style.backgroundColor = "yellow";
       return false;
   }
   
   else {
       errorMsg.className = "notVisible";
       fullnameError.className = "notVisible";
       fullName.style.backgroundColor = "white";
   }
   
   //Check 3 - make sure none of the fields contain a single quote
   if (fullName.value.indexOf("\'") >= 0) {
       errorMsg.className = "isVisible";
       errorMsg.innerHTML = "Full name has invalid characters";
       fullnameError.className = "isVisible";
       fullName.style.backgroundColor = "yellow";
       return false;
   }
   else {
       errorMsg.className = "notVisible";
       fullnameError.className = "notVisible";
       fullName.style.backgroundColor = "white";
       
   }
   
   if (userName.value.indexOf("\'") >= 0) {
       errorMsg.className = "isVisible";
       errorMsg.innerHTML = "Username has invalid characters";
       usernameError.className = "isVisible";
       userName.style.backgroundColor = "yellow";
       return false;
   }
   else {
       errorMsg.className = "notVisible";
       usernameError.className = "notVisible";
       userName.style.backgroundColor = "white";
   }
   
   if (email.value.indexOf("\'") >= 0) {
       errorMsg.className = "isVisible";
       errorMsg.innerHTML = "Email has invalid characters";
       emailError.className = "isVisible";
       email.style.backgroundColor = "yellow";
       return false;
   }
   else {
       errorMsg.className = "notVisible";
       emailError.className = "notVisible";
       email.style.backgroundColor = "white";
   }
   
   if (password.value.indexOf("\'") >= 0) {
       errorMsg.className = "isVisible";
       errorMsg.innerHTML = "Password has invalid characters";
       passError.className = "isVisible";
       password.style.backgroundColor = "yellow";
       return false;
   }
   else {
       errorMsg.className = "notVisible";
       passError.className = "notVisible";
       password.style.backgroundColor = "white";
    }
    
    if (confirmPassword.value.indexOf("\'") >= 0) {
       errorMsg.className = "isVisible";
       errorMsg.innerHTML = "Password has invalid characters";
       confError.className = "isVisible";
       confirmPassword.style.backgroundColor = "yellow";
       return false;
   }
   else {
       errorMsg.className = "notVisible";
       confError.className = "notVisible";
       confirmPassword.style.backgroundColor = "white";
   }
   
   //Check 4 - Valide that the password contains a lowercase, uppercase,
   //and a number
   var capsRegEx = /^(?=.*[A-Z])/;
   var lowersRegEx = /^(?=.*[a-z])/;
   var numsRegEx = /^(?=.*[\d])/;
   
   var checkCaps = capsRegEx.test(password.value);
   var checkLowers = lowersRegEx.test(password.value);
   var checkNumbers = numsRegEx.test(password.value);
   
   if (checkCaps === false) {
       errorMsg.className = "isVisible";
       errorMsg.innerHTML = "Password must contain at least one capital letter";
       passError.className = "isVisible";
       password.style.backgroundColor = "yellow";
       return false;
   }
   
   else if (checkLowers === false) {
       errorMsg.className = "isVisible";
       errorMsg.innerHTML = "Password must contain at least one lowercase letter";
       passError.className = "isVisible";
       password.style.backgroundColor = "yellow";
       return false;
   }
   
   else if (checkNumbers === false) {
       errorMsg.className = "isVisible";
       errorMsg.innerHTML = "Password must contain at least one number";
       passError.className = "isVisible";
       password.style.backgroundColor = "yellow";
       return false;
   }
   
   else {
       errorMsg.className = "notVisible";
       passError.className = "notVisible";
       password.style.backgroundColor = "white";
   }
   
    document.cookie = "password=".concat(password.value);
    document.cookie = "fullName=".concat(fullName.value.replace(" ", "-"));
    document.cookie = "emailAddress=".concat(email.value);
    document.cookie = "userName=".concat(userName.value);
    document.cookie = "questionNo=".concat(securitybox.value);
    document.cookie = "answer=".concat(securityanswer.value);
    document.cookie = "birthdate=".concat(dateofbirth.value);
   
   window.location.replace("http://localhost:8080/MyTwitter/sqlGateway");

}

function getSecQuestion() {
       document.getElementById("securityanswer").className = "isVisible";
       document.getElementById("secanswerbox").className = "isVisible";
}

