<%-- 
    Document   : cus_header
    Created on : Jan 18, 2021, 5:46:39 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>
            <a href="DispatcherServlet">HanaShop</a>
        </h1>
        <h2>
            <font color="red">Welcome, ${sessionScope.USER_DTO.fullName}</font>
        </h2>
        <a href="DispatcherServlet?btAction=Log Out">Log Out</a> <br/>
        <a href="DispatcherServlet?btAction=Show Cart">Show your cart</a> <br/>
        <a href="DispatcherServlet?btAction=Show History">Show your order history</a> <br/>
    </body>
</html>
