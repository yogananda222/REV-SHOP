<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="com.revshop.clientapp.dto.ProductsDTO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>


<h1>Welcome, ${userName}!</h1>

    <!-- Check if productsList is not empty -->
    <c:if test="${not empty sessionScope.productsList}">
        <c:forEach var="product" items="${sessionScope.productsList}">
            <div class="product-card">
                <h3>${product.productName}</h3>
                <p>Price: $${product.Price}</p>
                <p>Description: ${product.productDescription}</p>
            </div>
        </c:forEach>
    </c:if>

    <!-- Message if no products found -->
    <c:if test="${empty sessionScope.productsList}">
        <p>No products available at the moment.</p>
    </c:if>

</body>
</html>