<%-- 
    Document   : home.jsp
    Created on : Sep 24, 2015, 6:47:02 PM
    Author     : xl
--%>

<%@ include file = "header.jsp" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    ${query}
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="javascript/main.js" type="text/javascript"></script>
        <title>Home Page</title>
    </head>
        <div class="homepage">
            <div class="left_content">
                <div class="tweet_box">

                    <div class="tweet_box">


                        <label>${user.fullName}</label>
                        <label>user info goes here</label><br><br>
                        <hr/>
                        <label>Full Name </label>
                        <label>Email address</label><br><br>
                        <label>Number of tweets </label>
                        <label>Profile Pic</label><br><br>
                    </div>
                </div>
                        
                <div class="trends">
                    <label>Trends</label><br><br>
                    <c:forEach items="${trending}" var="trend">
                        <a href="http://localhost:8080/MyTwitter/hashtag.jsp?h=${trend}">#${trend}</a>
                        <br>
                    </c:forEach>
                </div>
            </div>

            <div class="center_content">
                <div class="top_centered">
                    <form action="addTweet" method="post">
                        <input type="hidden" name="action" value="add"> 
                        <textarea class="tweetInput" name="tweettext" cols="40" rows="4" value="tweettext" maxlength="280" placeholder="type your tweet here" required></textarea><br>

                        <button type="submit" id="btnContinue" class="button">tweet</button>
                    </form> 
                </div>
                <br><br>                           
                
                <hr/>
                <br><br>
                <div class="bottom_centered">

                        <div id="tweet">  
                            <c:forEach items="${tweets}" var="tweet">
                                <div id="each_tweet">
                                <c:out value="user: ${tweet.getUsername()}"/><br>
                                ${tweet.getTweet()}
                                <c:if test="${tweet.getUserID() == user.getUserID()}">
                                    <form action=deleteTweet method="post">
                                        <input type="submit" value="delete" id="deleteTweet">
                                        <input id="tweet_id" name="tweet_id" type="hidden" value="${tweet.getTweetID()}">
                                    </form>
                                </c:if>
                                <br>
                                </div>
                            </c:forEach>
                        </div>
                </div>

            </div>

            <div class="right_content">
                <div class="tweet_box">

                    <label>who to follow: a for loop looping through and displaying all users</label><br>

                </div>
              
            </div>
        </div> 
    </body>
</html>

<%@ include file = "footer.jsp" %>