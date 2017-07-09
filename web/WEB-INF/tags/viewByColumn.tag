<%-- 
    Document   : viewByColumn
    Created on : 08-Nov-2015, 21:57:08
    Author     : mosza16
--%>
<%@tag  body-content="scriptless" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="product" required="true" rtexprvalue="true" type="model.Product"%>

<table>
    <tr>
     <td><img  src="images/${product.productId}.jpg" width="120" /></td>
    </tr>
    <tr>
        <td>ProductID</td><td>Price</td><td>Available</td><td>Qty</td><td>Description</td><td>Type</td>
    </tr>
    <tr>
        <td>${product.productId}</td><td>${product.price}</td><td>${product.available}<input type="checkbox" ${product.available?'checked':''} disabled ></td><td>${product.quantityOnHand}</td><td>${product.description}</td>
        <td>${product.productType}</td>
    </tr>
</table>