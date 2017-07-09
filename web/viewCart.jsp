<%-- 
    Document   : viewCart
    Created on : 08-Nov-2015, 23:05:05
    Author     : mosza16
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <c:if test="${CART.items!=null}">
        <form action="update">  
        <table>
            <tr>
                <td>No.</td><td>decription</td><td>Unit Price</td><td>Qty</td><<td>TotalPrice</td><td>All TotalPrice</td>
            </tr>
            <c:forEach items="${CART.items}" var="it" varStatus="mycount">
                <tr>
                    <td>${mycount.count}</td><td>${it.value.product.description}</td><td><fmt:formatNumber value='${it.value.product.price}' pattern="#,###.00" /></td><td><input type="number" name="_${it.key}" min="1" value="${it.value.quntity}"></td><td>${it.value.total}</td><td></td>
                    <td>Remove:<input type="checkbox" name="deleteItem" value="${it.value.product.productId}"></td>
                </tr>
            </c:forEach>
                <tr>
                    <td></td><td></td><td></td><td></td><td></td><td><fmt:formatNumber value="${CART.totalPrice}" pattern="#,###.00" /></td>
                </tr>
        </table>
                <a href="ProductListing.jsp">continue shop</a>
                <a href="CheckOut">CheckOUT</a>
                <input type="submit" value="UpdateCart">
        </form>
    </c:if>    
    </body>
</html>
