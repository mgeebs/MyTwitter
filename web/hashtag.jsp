<%-- 
    Document   : home.jsp
    Created on : Sep 24, 2015, 6:47:02 PM
    Author     : xl
--%>

<%@ include file = "header.jsp" %> 
<jsp:include page="/hashTag" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <c:out value="#${hashTag}"></c:out><br>
    
    <c:forEach items="${tweets}" var="tweet">
        <div id="each_tweet">
            ${tweet.getTweet()}
        </div>
    </c:forEach>
</html>

<%@ include file = "footer.jsp" %>