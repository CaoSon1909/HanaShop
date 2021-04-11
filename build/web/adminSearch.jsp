<%-- 
    Document   : adminSearch
    Created on : Jan 5, 2021, 4:17:03 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Search Page</title>

    </head>
    <body>
        <jsp:include page="admin_header.jsp"/>
        <c:set var="adminFullName" value="${sessionScope.USER_DTO.fullName}"/>
        <!--search by name form
            parameters: txtSearchValue, txtSearchType(name = txtSearchType, value = searchByName), button, roleID
        -->
        <form action="DispatcherServlet">
            Search Value: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /> <br/>
            <input type="hidden" name="txtSearchType" value="searchByName" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br/>
        <!--search by min-max price
            parameters: txtMinPrice, txtMaxPrice, txtSearchType(name = txtSearchType, value = searchByRange), button, roleID
        -->
        <form action="DispatcherServlet">
            Min Price: <input type="text" name="txtMinPrice" value="${param.txtMinPrice}" /> 
            Max Price: <input type="text" name="txtMaxPrice" value="${param.txtMaxPrice}" /> <br/>
            <input type="hidden" name="txtSearchType" value="searchByRange" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br/>
        <c:if test="${not empty requestScope.FOOD_NAME_ERR}">
            ${requestScope.FOOD_NAME_ERR}
        </c:if>
        <c:if test="${not empty requestScope.FOOD_DES_ERR}">
            ${requestScope.FOOD_DES_ERR}
        </c:if>
        <c:if test="${not empty requestScope.FOOD_PRICE_ERR}">
            ${requestScope.FOOD_PRICE_ERR}
        </c:if>
        <c:if test="${not empty requestScope.FOOD_QUANTITY_ERR}">
            ${requestScope.FOOD_QUANTITY_ERR}
        </c:if>
        <c:if test="${not empty requestScope.POSITIVE_CONSTRAINT}">
            ${requestScope.POSITIVE_CONSTRAINT}
        </c:if>
        <c:if test="${not empty requestScope.WRONG_FORMAT_EXCEPTION}">
            ${requestScope.WRONG_FORMAT_EXCEPTION}
        </c:if>
        <c:if test="${not empty requestScope.DELETE_ERR}">
            ${requestScope.DELETE_ERR}
        </c:if>
        <c:if test="${not empty requestScope.CREATE_ERR}">
            ${requestScope.CREATE_ERR}
        </c:if>
        <c:if test="${not empty requestScope.UPDATE_ERR}">
            ${requestScope.UPDATE_ERR}
        </c:if>
        <!--search by categoryID
        parameters: categoryID(value = 1 or 2), txtSearchType(name = txtSearchType, value = searchByCategory), button
        -->
        <form action="DispatcherServlet">
            <select name="categoryID">
                <c:forEach var="category" items="${requestScope.CATEGORY_LIST}">
                    <option value="${category.ID}">
                        ${category.name}
                    </option>
                </c:forEach>
            </select>
            <br/>
            <input type="hidden" name="txtSearchType" value="searchByCategory" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br/>
        <!--create new food-->
        <a href="DispatcherServlet?btAction=Create Page">Create New Food</a>
        <br/>
        <!--display all food from DB-->
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
                        <th>Current Quantity</th>
                        <th>Category</th>
                        <th>Update</th>
                        <th>Delete</th>
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
                                <input type="text" name="txtFoodName" value="${food.name}" />
                            </td>
                            <td>
                                Image chua co nha
                            </td>
                            <td>
                                <input type="text" name="txtFoodDescription" value="${food.description}" />

                            </td>
                            <td>
                                <input type="text" name="txtFoodPrice" value="${food.price}" />

                            </td>
                            <td>
                                ${food.createDate}
                            </td>
                            <td>
                                <input type="text" name="txtCurrentQuantity" value="${food.currentQuantity}" />

                            </td>
                            <td>
                                <c:if test="${food.categoryID == 1}">
                                    <select name="categoryID">
                                        <option value="1" selected>fast food</option>
                                        <option value="2">fast drink</option>
                                    </select>
                                </c:if>
                                <c:if test="${food.categoryID ==2}">
                                    <select name="categoryID">
                                        <option value="1">fast food</option>
                                        <option value="2" selected>fast drink</option>
                                    </select>
                                </c:if>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="btAction" />
                                <input type="hidden" name="txtFoodID" value="${food.id}" />
                                <input type="hidden" name="txtSearchType" value="${param.txtSearchType}" />
                                <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />
                                <input type="hidden" name="txtMinPrice" value="${param.txtMinPrice}" />
                                <input type="hidden" name="txtMaxPrice" value="${param.txtMaxPrice}" />
                                <input type="hidden" name="categoryID" value="${param.categoryID}" />
                                <input type="hidden" name="btAction" value="Search" />
                            </td>
                            <td>
                                <c:url var="deleteLink" value="DispatcherServlet?btAction=Delete">
                                    <c:param name="txtFoodID" value="${food.id}"/>
                                </c:url>
                                <a href="${deleteLink}">Delete</a>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty foodList}">
        No record
    </c:if>
</body>
</html>
