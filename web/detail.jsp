<%-- 
    Document   : detail
    Created on : Jan 18, 2021, 2:20:38 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Cart Page</title>
    </head>
    <body>
        <jsp:include page="cus_header.jsp"/>
        <h1>The invoice of ${sessionScope.USER_DTO.fullName}</h1>
        <c:set var="order" value="${requestScope.ORDER_DTO}"/>
        <c:set var="order_detail" value="${requestScope.ORDER_DETAIL_LIST}"/>
        <c:if test="${not empty order}">
            <h2>Order ID:</h2> ${order.ID}
            <h2>Delivery address:</h2> ${order.deliveryAddress}
            <h2>Order date:</h2> ${order.orderDate}
            <h2>Payment method:</h2>
            <c:set var="paymentType" value="${order.paymentTypeID}"/>
            <c:if test="${paymentType == 1}">Cash</c:if>
            <c:if test="${paymentType == 2}">Visa</c:if>
            <h2>Subtotal:</h2> ${order.total}
            <h2>In detail:</h2>
            <c:if test="${not empty order_detail}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Food Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order_detail_DTO" items="${order_detail}">
                            <tr>
                                <td>${order_detail_DTO.foodID}</td>
                                <td>${order_detail_DTO.quantity}</td>
                                <td>${order_detail_DTO.price}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:if>
    </body>
</html>
