<%-- 
    Document   : create
    Created on : Jan 14, 2021, 2:37:39 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Food Page</title>
    </head>
    <body>
        <jsp:include page="admin_header.jsp"/>
        <header>
            <a href="DispatcherServlet?btAction=Search&txtSearchType=searchByName&txtSearchValue=">Hana Shop</a>
        </header>
        <h1>Create New Food</h1>
        <form action="DispatcherServlet">
            Food Name: <input type="text" name="txtFoodName" value="${param.txtFoodName}" /> <br/>
            <font color="red">
            <c:if test="${not empty requestScope.FOOD_NAME_ERR}">
                ${requestScope.FOOD_NAME_ERR}
            </c:if> <br/>
            </font>
            Food Description: <input type="text" name="txtFoodDescription" value="${param.txtFoodDescription}" /> <br/>
            <font color="red">
            <c:if test="${not empty requestScope.FOOD_DES_ERR}">
                ${requestScope.FOOD_DES_ERR}
            </c:if> <br/>
            </font>
            Food Price: <input type="text" name="txtFoodPrice" value="${param.txtFoodPrice}" /> <br/>
            <font color="red">
            <c:if test="${not empty requestScope.FOOD_PRICE_ERR}">
                ${requestScope.FOOD_PRICE_ERR}
            </c:if> 
            </font> <br/>
            Food Quantity: <input type="text" name="txtFoodQuantity" value="${param.txtFoodQuantity}" /> <br/>
            <font color="red">
            <c:if test="${not empty requestScope.FOOD_QUANTITY_ERR}">
                ${requestScope.FOOD_QUANTITY_ERR}
            </c:if>
            </font> <br/>
            Food Category: 
            <select name="categoryID">
                <option value="1">Food</option>
                <option value="2">Drink</option>
            </select> <br/>
            <input type="submit" value="Create" name="btAction" />
        </form>
        <font color="red">
        <c:if test="${not empty requestScope.NUMBER_FORMAT}">
            ${requestScope.NUMBER_FORMAT}
        </c:if>
        </font> <br/>
        <c:if test="${not empty requestScope.FOOD_CATEGORY_ERR}">
            ${requestScope.FOOD_CATEGORY_ERR}
        </c:if>
        <!--//-->
    </body>
</html>
