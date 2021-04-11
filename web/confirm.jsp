<%-- 
    Document   : confirm
    Created on : Jan 17, 2021, 12:04:56 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Page</title>
    </head>
    <body>
        <jsp:include page="cus_header.jsp"/>
        <h2>Invoice</h2>
            <c:set var="cart" value="${requestScope.FOOD_IN_CART}"/>
            <c:if test="${not empty cart}">
            <form action="DispatcherServlet">
                <h2>Delivery Address:</h2> 
                <input type="text" name="txtAddress" value="${param.txtAddress}" /> <br/> <br/>
                <c:set var="err" value="${requestScope.ERROR_MSG}"/>
                <c:if test="${not empty err}">${err}</c:if>
                <h2>Choose Payment Methods:</h2>
                <input type="radio" name="paymentTypeID" value="1" />Cash Payment <br/>
                <h2>Invoice Details:</h2> <br/>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Food Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="food" items="${requestScope.FOOD_IN_CART}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${food.foodID}</td>
                                <td>${food.quantity}</td>
                                <td>${food.price}</td>
                                <td style="text-align: center">${food.quantity * food.price}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="4" style="text-align: center">Subtotal:(${requestScope.QUANTITY_SUM} items) $${requestScope.SUB_TOTAL}</td>
                            <td colspan="2" style="text-align: center">
                                <input type="submit" value="Proceed to checkout" name="btAction" />
                                <input type="hidden" name="txtTotal" value="${requestScope.SUB_TOTAL}" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <a href="DispatcherServlet?btAction=Show Cart">Return to your cart</a>
        </c:if>
    </body>
</html>
