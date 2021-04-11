<%-- 
    Document   : login
    Created on : Jan 6, 2021, 1:19:02 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>
            <a href="DispatcherServlet">HanaShop</a>
        </h1>
        <c:set var="error" value="${requestScope.LOGIN_ERR}"/>
        <c:if test="${not empty error}">
            <font color="red">${error}</font>
        </c:if>
        <form action="DispatcherServlet" method="POST">
            Email: <input type="text" name="txtEmail" value="" /> <br/>
            Password: <input type="password" name="txtPassword" value="" /> <br/>
            <input type="submit" value="Login" name="btAction" />
        </form>
    </body>
</html>
