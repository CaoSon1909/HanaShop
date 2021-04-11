<%-- 
    Document   : cart
    Created on : Jan 15, 2021, 3:22:02 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <body>
        <jsp:include page="cus_header.jsp"/>
        <h1>The cart of ${sessionScope.USER_DTO.fullName}</h1>
        <c:set var="cart" value="${requestScope.FOOD_IN_CART}"/>
        <c:if test="${not empty cart}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Food Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Update Quantity</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <!--Out of Stock-->
                    <c:set var="out_of_stock" value="${requestScope.CANNOT_UPDATE_QUANTITY}"/>
                    <c:if test="${not empty out_of_stock}">
                        ${out_of_stock}
                    </c:if>
                    <!--//-->
                    <!--Quantity tào lao-->
                    <c:set var="exception" value="${requestScope.NUMBER_FORMAT_EXCEPTION}"/>
                    <c:if test="${not empty exception}">
                        ${exception}
                    </c:if>
                    <!--//-->
                    <c:forEach var="map" items="${requestScope.FOOD_MAP}" varStatus="counter">
                    <form action="DispatcherServlet">
                        <c:set var="cartDTO" value="${map.key}"/>
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${cartDTO.foodID}

                            </td>
                            <td>
                                <input type="number" name="txtQuantity" value="${cartDTO.quantity}" min="1" max="${map.value}" />
                            </td>
                            <td>
                                $${cartDTO.price}
                            </td>
                            <td>
                                $${cartDTO.quantity * cartDTO.price}
                            </td>
                            <td>
                                <input type="submit" value="Update Quantity" name="btAction" />
                                <input type="hidden" name="txtCartID" value="${cartDTO.cartID}" />
                                <input type="hidden" name="txtFoodName" value="${cartDTO.foodID}" />

                            </td>
                            <td>
                                <input type="submit" value="Remove" name="btAction" />
                                <input type="hidden" name="txtFoodName" value="${cartDTO.foodID}" />

                            </td>
                        </tr>
                    </form>
                </c:forEach>
                <tr>
                    <td>
                        Subtotal(${requestScope.SUM_OF_QUANTITY} items): $${requestScope.SUM_OF_CART} 
                    </td>
                    <td>
                        <form action="DispatcherServlet">
                            <input type="submit" value="Checkout" name="btAction" />
                            <input type="hidden" name="txtFoodName" value="${cartDTO.foodID}" />
                            <input type="hidden" name="txtOrderID" value="" />
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>

    </c:if>
    <c:if test="${empty cart}">
        There is nothing in your cart!
    </c:if>
    <a href="DispatcherServlet">Continue Shopping</a>
</body>
</html>
