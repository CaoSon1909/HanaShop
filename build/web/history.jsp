<%-- 
    Document   : history
    Created on : Jan 18, 2021, 1:05:36 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order History Page</title>
    </head>
    <body>
        <jsp:include page="cus_header.jsp"/>
        <h1>The order history of:         
            <font color="red">${sessionScope.USER_DTO.fullName}</font>
        </h1>
        <br/>
        <a href="DispatcherServlet">Continue Shopping</a>
<!--        <form action="DispatcherServlet">
            Search by name: <input type="text" name="txtFoodName" value="${param.txtFoodName}" /> <br/>
            <input type="hidden" name="txtSearchType" value="searchNameInHistory" />
            <input type="submit" value="Search" name="btAction" />
        </form>-->
        <c:set var="history" value="${requestScope.ORDER_LIST}"/>
        <c:if test="${not empty history}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Delivery Address</th>
                        <th>Subtotal</th>
                        <th>Order Date</th>
                        <th>Payment Type</th>
                        <th>Detail</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order_history" items="${history}">
                    <form action="DispatcherServlet">
                        <tr>
                            <td>${order_history.ID}</td>
                            <td>${order_history.deliveryAddress}</td>
                            <td>${order_history.total}</td>
                            <td>${order_history.orderDate}</td>
                            <td>
                                <c:set var="paymentType" value="${order_history.paymentTypeID}"/>
                                <c:if test="${paymentType == 1}">Cash</c:if>
                                <c:if test="${paymentType == 2}">Visa</c:if>
                                </td>
                                <td>
                                    <input type="submit" value="Show Detail" name="btAction" />
                                    <input type="hidden" name="txtCartID" value="${order_history.ID}" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty history}">
        No record
    </c:if>
</body>
</html>
