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
    <h1>Welcome, ${user.userName}</h1>
    <p><a href="/logout">logout</a></p>
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
                <c:if test="${user.id != hall.creator.id}">
                    <th></th>
                </c:if>
                <c:if test="${user.id == hall.creator.id}">
                    <th>Reserved By</th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="date" items="${hall.reserveDates}">
                <tr>
                    <td><c:out value="${date.date}"></c:out></td>
                    <td><c:out value="${date.fromHour}"></c:out></td>
                    <td><c:out value="${date.toHour}"></c:out></td>
                    <c:if test="${user.id != hall.creator.id}">
                        <c:choose>
                            <c:when test="${date.isAvailable}">
                                <td><a href="/halls/hall${hall.id}/date${date.id}/book">reserve</a></td>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${user.id == date.booker.id}">
                                        <td>you reserved this date | <a href="/halls/hall${hall.id}/date${date.id}/unbook">unreserve</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>this date is already reserved</td>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${user.id == hall.creator.id}">
                        <td>${date.booker.userName}</td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <c:if test="${user.id == hall.creator.id}">
        <p>Add Availablity</p>
        <form:form action="/halls/${hall.id}/add_availability" method="post" modelAttribute="reserveDate">
            <form:input type="date" path="date"></form:input>
            <form:input type="number" min="0" max="23" path="fromHour"></form:input>
            <form:input type="number" min="1" max="24" path="toHour"></form:input>
            <input type="submit" value="Add">
        </form:form>
    </c:if>
</body>
</html>