<%-- 
    Document   : admin_header
    Created on : Jan 19, 2021, 7:08:08 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>
            <a href="DispatcherServlet?btAction=Search&txtSearchType=searchByName&txtSearchValue=">HanaShop</a>
        </h1>
        <h2>
            <font color="red">Welcome, ${sessionScope.USER_DTO.fullName}</font> <br/>
            <a href="DispatcherServlet?btAction=Log Out">Log Out</a>
        </h2>
    </body>
</html>
