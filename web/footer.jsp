<%-- 
    Document   : footer.jsp
    Created on : Sep 24, 2015, 6:47:16 PM
    Author     : xl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="header-card">
            <ul id="footer_links">
                <li>About</li>
                <li>Help Center</li>
                <li>Blog</li>
                <li>Status</li>
                <li>Jobs</li>
                <li>Terms</li>
                <li>Privacy Policy</li>
                <li>Cookies</li>
                <li>Ads info</li>
                <li>Brand</li>
                <li>Apps</li>
                <li>Advertise</li>
                <li>Marketing</li>
                <li>Businesses</li>
                <li>Developers</li>
                <li>Directory</li>
                <li>Settings</li>
                <jsp:useBean id="now" class="java.util.Date" />
                <fmt:formatDate var="year" value="${now}" pattern="yyyy" />
                <li>© ${year} Twitter</li>
            </ul>            
        </div>
    </body>
</html>
