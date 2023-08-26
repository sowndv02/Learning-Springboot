<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>
<base href="/">
</head>
<body>
<h1>Shopping Cart</h1>
<table>
    <tr>
        <td>No</td>
        <td>Name</td>
        <td>Quantity</td>
        <td>Price</td>
        <td></td>
    </tr>
    <c:set var="no" value="1"></c:set>
    <c:forEach var="item" items="${cartItems}">
        <form action="shoppingCart/update">
        <tr>
            <td>${no}</td>
            <td>${item.productName}</td>
            <td>
                <input type="hidden" name="productId" value="${item.productId}" /> 
                <input type="number" value="${item.quantity}" name="quantity" onblur="this.form.submit()" />
            </td>
            <td>${item.price}</td>
            <td>
                <a href="shoppingCart/remove/${item.productId}">Delete</a>
            </td>
        </tr>
        </form>
        <c:set var="no" value="${no + 1}"></c:set>
    </c:forEach>
</table>
<a href="products/list">List products</a>
<a href="shoppingCart/checkout">Checkout</a>

</body>
</html>