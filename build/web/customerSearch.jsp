<%-- 
    Document   : search
    Created on : Jan 4, 2021, 1:44:56 PM
    Author     : ACER
--%>

<%@page import="java.util.List"%>
<%@page import="sonpc.dtos.TblFoodDTO"%>
<%@page import="sonpc.daos.TblFoodDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Search Page</title>
    </head>
    <body>
        <!--
            -Lấy customerFullName trong sessionScope
            -Nếu như chưa có customerFullName => chưa đăng nhập => hiện link login.html, search form, list food
            -Nếu như có customerFullName => đã đăng nhập => hiện welcome, log out link, search form, list food
        -->
        <c:set var="fullNameCustomer" value="${sessionScope.USER_DTO.fullName}"/>
        <!--customer đã đăng nhập-->
        <c:if test="${not empty fullNameCustomer}">
            <jsp:include page="cus_header.jsp"/>
            <!--search By food name
                parameters: searchValue(foodName) + searchType (name= txtSearchType, value = searchByName) + button + roleID
            -->
            <c:if test="${not empty requestScope.WRONG_FORMAT_EXCEPTION}">
                ${requestScope.WRONG_FORMAT_EXCEPTION}
            </c:if>
            <c:if test="${not empty requestScope.POSTIVE_ERR}">
                ${requestScope.POSTIVE_ERR}
            </c:if>
            <c:if test="${not empty requestScope.MIN_ERR}">
                ${requestScope.MIN_ERR}
            </c:if>
            <c:if test="${not empty requestScope.MAX_ERR}">
                ${requestScope.MAX_ERR}
            </c:if>
            <form action="DispatcherServlet">
                Search Name: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /> <br/>
                <input type="hidden" name="txtSearchType" value="searchByName" />
                <input type="submit" value="Search" name="btAction" />
            </form>
            <br/>
            <!--search by min-max range of price
                parameters: minPrice, maxPrice, searchType (name= txtSearchType, value = searchByRange) + button + roleID
            -->
            <form name="DispatcherServlet">
                Min Price: <input type="text" name="txtMinPrice" value="${param.txtMinPrice}" /> <br/>
                Max Price: <input type="text" name="txtMaxPrice" value="${param.txtMaxPrice}" /> <br/>
                <input type="hidden" name="txtSearchType" value="searchByRange" />
                <input type="submit" value="Search" name="btAction" />
            </form>
            <br/>
            <!--search by category
                parameters: categoryID, searchType(name = txtSearchType, value = searchByCategory) + button + roleID
            -->
            <form action="DispatcherServlet">
                <select name="categoryID">
                    <c:forEach var="category" items="${requestScope.CATEGORY_LIST}">
                        <option value="${category.ID}">
                            ${category.name}
                        </option>
                    </c:forEach>
                </select> 
                <input type="hidden" name="txtSearchType" value="searchByCategory" /><br/>
                <input type="submit" value="Search" name="btAction" />
            </form>
            <br/>
            <!--display the default search result-->
            <c:set var="foodList" value="${requestScope.FOOD_LIST}"/>
            <c:if test="${not empty foodList}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Create Date</th>
                            <th>Category</th>
                            <th>Add to Cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="food" items="${foodList}" varStatus="counter">
                        <form action="DispatcherServlet">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${food.name}
                                </td>
                                <td>
                                    Chua co image huhu
                                </td>
                                <td>
                                    ${food.description}
                                </td>
                                <td>
                                    ${food.price}
                                </td>
                                <td>
                                    ${food.createDate}
                                </td>
                                <td>
                                    <c:if test="${food.categoryID == 1}">
                                        fast food
                                    </c:if>
                                    <c:if test="${food.categoryID == 2}">
                                        fast drink
                                    </c:if>
                                </td>
                                <td>
                                    <input type="submit" value="Add To Cart" name="btAction" />
                                    <input type="hidden" name="txtFoodID" value="${food.id}" />
                                </td>
                            </tr>
                        </form> 
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty foodList}">
            No record!!!
        </c:if>
        <!--//End display the default search result (20 rows from DB)-->
    </c:if>
    <!--customer chưa đăng nhập-->
    <c:if test="${empty fullNameCustomer}">
        <jsp:include page="unauth_cus_header.jsp"/>
        <!--search By food name
            parameters: searchValue(foodName) + searchType (name= txtSearchType, value = searchByName) + button , roleID
        -->
        <form action="DispatcherServlet">
            Search Name: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /> <br/>
            <input type="hidden" name="txtSearchType" value="searchByName" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br/>
        <!--search by min-max range of price
            parameters: minPrice, maxPrice, searchType (name= txtSearchType, value = searchByRange) + button , roleID
        -->
        <c:if test="${not empty requestScope.WRONG_FORMAT_EXCEPTION}">
            ${requestScope.WRONG_FORMAT_EXCEPTION}
        </c:if>
        <c:if test="${not empty requestScope.POSTIVE_ERR}">
            ${requestScope.POSTIVE_ERR}
        </c:if>
        <c:if test="${not empty requestScope.MIN_ERR}">
            ${requestScope.MIN_ERR}
        </c:if>
        <c:if test="${not empty requestScope.MAX_ERR}">
            ${requestScope.MAX_ERR}
        </c:if>
        <form name="DispatcherServlet">
            Min Price: <input id="min" type="text" name="txtMinPrice" value="${param.txtMinPrice}" /> <br/>
            Max Price: <input type="text" name="txtMaxPrice" value="${param.txtMaxPrice}" /> <br/>
            <input type="hidden" name="txtSearchType" value="searchByRange" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br/>
        <!--search by category
            parameters: categoryID, searchType(name = txtSearchType, value = searchByCategory) + button ,roleID
        -->
        <form action="DispatcherServlet">
            <select name="categoryID">
                <c:forEach var="category" items="${requestScope.CATEGORY_LIST}">
                    <option value="${category.ID}">
                        ${category.name}
                    </option>
                </c:forEach>
            </select>           
            <input type="hidden" name="txtSearchType" value="searchByCategory" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br/>
        <c:set var="foodList" value="${requestScope.FOOD_LIST}"/>
        <c:if test="${not empty foodList}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Image</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Create Date</th>
                        <th>Category</th>
                        <th>Add to Cart</th>
                    </tr>
                </thead>

                <c:forEach var="food" items="${foodList}" varStatus="counter">
                    <form action="DispatcherServlet">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${food.name}
                            </td>
                            <td>
                                Image chua co nha
                            </td>
                            <td>
                                ${food.description}
                            </td>
                            <td>
                                ${food.price}
                            </td>
                            <td>
                                ${food.createDate}
                            </td>
                            <td>
                                <c:if test="${food.categoryID == 1}">
                                    Food
                                </c:if>
                                <c:if test="${food.categoryID == 2}">
                                    Drink
                                </c:if>
                            </td>
                            <td>
                                <input type="submit" value="Add To Cart" name="btAction" />
                                <input type="hidden" name="txtEmail" value="${sessionScope.USER_DTO.email}" />
                                <input type="hidden" name="txtFoodID" value="${food.id}" />
                                <input type="hidden" name="txtFoodPrice" value="${food.price}" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>

            </table>
        </c:if>
        <c:if test="${empty foodList}">
            No record!!!
        </c:if>
    </c:if>
</body>
</html>
