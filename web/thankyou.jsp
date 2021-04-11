<%-- 
    Document   : thankyou
    Created on : Jan 17, 2021, 6:10:37 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thank You Page</title>
    </head>
    <body>
        <jsp:include page="cus_header.jsp"/>
        <h1>
            <font color="green">
            Dear ${sessionScope.USER_DTO.fullName}, 
            ${requestScope.THANK_YOU_MESSAGE}
            </font>
        </h1>
        <br/>

        <c:set var="order_detail_list" value="${requestScope.ORDER_DETAIL_LIST}"/>
        <c:set var="order_dto" value="${requestScope.ORDER}"/>
        <c:if test="${not empty order_dto}">
            Order ID: ${order_dto.ID} <br/>
            Delivery address: ${order_dto.deliveryAddress} <br/>
            Order date: ${order_dto.orderDate} <br/>
            Payment method:
            <c:set var="paymentType" value="${order_dto.paymentTypeID}"/>
            <c:if test="${paymentType == 1}">Cash</c:if>
            <c:if test="${paymentType == 2}">Visa</c:if>
                <br/>
                Subtotal: ${order_dto.total} <br/>
        </c:if>
        <table border="1">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Food Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" items="${order_detail_list}">
                    <tr>
                        <td>${dto.orderID}</td>
                        <td>${dto.foodID}</td>
                        <td>${dto.quantity}</td>
                        <td>${dto.price}</td>
                    </tr>
                </c:forEach>
            </tbody>
            <a href="DispatcherServlet">Continue Shopping</a>
        </table>
    </body>
</html>
