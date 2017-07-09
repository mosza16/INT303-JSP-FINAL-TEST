<%-- 
    Document   : ProductListing
    Created on : 08-Nov-2015, 22:05:19
    Author     : mosza16
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  tagdir="/WEB-INF/tags/" prefix="t" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/header.jsp" />
        <form action="SearchProduct">
            <select name="cate">
                <option value="name">Name</option>
                 <option value="id">id</option>
                 <option value="price">Price</option>
            </select>
            <input type="text" name="text1">
            <input type="text" name="text2">
            <select name="viewBy">
                <option value="col">column</option>
                 <option value="row">row</option>
                 
            </select>
            <input type="submit" value="Enter">
        </form>
        <c:set value="${param.viewBy}" var="viewBy" />
        <c:choose >
            <c:when test="${param.viewBy=='col'}">
                <c:if test="${products!=null}" >
                    <c:forEach items="${products}" var="p">
                    <t:viewByColumn product="${p}" />
                    <form action="addCart">
                        <input type="hidden" name="t1" value="${param.text1}"> 
                        <input type="hidden" name="t2" value="${param.text2}"> 
                        <input type="hidden" name="cat" value="${param.cate}">
                         <input type="hidden" name="id" value="${p.productId}">
                          <input type="hidden" name="v" value="${param.viewBy}"> 
                        <input type="submit" value="add">
                    </form>
                    </c:forEach>
                </c:if>
            
    
                </c:when>
            <c:when test="${param.viewBy=='row'}">
                <c:if test="${products}" >
        
                </c:if>
            
    
                </c:when>
    </c:choose>
    </body>
</html>
