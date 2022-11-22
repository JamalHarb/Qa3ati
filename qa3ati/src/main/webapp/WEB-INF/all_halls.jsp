<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Halls</title>
</head>
<body>
    <h1>Welcome, ${user.userName}</h1>
    <p><a href="/logout">logout</a></p>
    <p><a href="/halls/new">+ add a hall</a></p>
    <table border="2px">
        <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Capacity</th>
                <th>Address</th>
                <th>City</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="hall" items="${halls}">
                <tr>
                    <td><a href="/halls/${hall.id}"><c:out value="${hall.name}"></c:out></a></td>
                    <td><c:out value="${hall.basicPrice}"></c:out></td>
                    <td><c:out value="${hall.capacity}"></c:out></td>
                    <td><c:out value="${hall.address}"></c:out></td>
                    <td><c:out value="${hall.city.cityName}"></c:out></td>
                    <c:if test="${user.id == hall.creator.id}">
                        <td><a href="/halls/${hall.id}/delete">delete</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>