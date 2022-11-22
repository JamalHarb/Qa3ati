<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${hall.name}</title>
</head>
<body>
    <p><a href="/halls">all halls</a></p>
    <h1>${hall.name}</h1>
    <p>Price: ${hall.basicPrice}</p>
    <p>Capacity: ${hall.capacity}</p>
    <p>Address: ${hall.address}</p>
    <p>City: ${hall.city.cityName}</p>
    <br>
    <p>Availablity</p>
    <table border="1px">
        <thead>
            <tr>
                <th>Date</th>
                <th>From</th>
                <th>To</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="date" items="${hall.reserveDates}">
                <tr>
                    <td><c:out value="${date.date}"></c:out></td>
                    <td><c:out value="${date.fromHour}"></c:out></td>
                    <td><c:out value="${date.toHour}"></c:out></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <p>Add Availablity</p>
    <form:form action="/halls/${hall.id}/add_availability" method="post" modelAttribute="reserveDate">
        <form:input type="date" path="date"></form:input>
        <form:input type="number" min="0" max="23" path="fromHour"></form:input>
        <form:input type="number" min="1" max="24" path="toHour"></form:input>
        <input type="submit" value="Add">
    </form:form>
</body>
</html>