<%-- 
    Document   : viewByRow
    Created on : 09-Nov-2015, 13:23:22
    Author     : mosza16
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@tag  body-content="scriptless" %>
<%@attribute  name="product" required="true"  rtexprvalue="true" type="model.Product" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<table>
    <tr>
        <td><img src="images/${product.productId}.jpg" width="120"></td>
    </tr>
    <tr>
        
    </tr>
</table>