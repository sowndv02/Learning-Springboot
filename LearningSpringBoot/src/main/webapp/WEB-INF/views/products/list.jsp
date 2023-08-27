<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<base href="/">
</head>
<body>
<h1>List Student </h1>
<table>
    <tr>
        <td>ProductId</td>
        <td>ProductName</td>
        <td>Quantity</td>
        <td>Price</td>
        <td></td>
    </tr>
    <c:forEach var="item" items="${products}">
        <tr>
            <td>${item.productId}</td>
            <td>${item.productName}</td>
            <td>${item.quantity}</td>
            <td>${item.price}</td>

            <td>
                <a href="shoppingCart/add/${item.productId}">Add to Cart</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="shoppingCart/list">Shopping Cart</a>

</body>
</html>