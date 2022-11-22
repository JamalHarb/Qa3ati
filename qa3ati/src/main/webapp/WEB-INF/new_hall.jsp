<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/new_hall_style.css">
<meta charset="ISO-8859-1">
<title>Create a Hall</title>
</head>
<body>
	<div>
        <form:form action="/halls/create" method="post" modelAttribute="hall">
            <form:input type="hidden" path="creator" value="${user.id}"></form:input>
            <table>
                <tr>
                    <td>Name</td>
                    <td><form:input path="name"></form:input></td>
                    <td><form:errors path="name"></form:errors></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><form:input path="address"></form:input></td>
                    <td><form:errors path="address"></form:errors></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><form:input path="basicPrice"></form:input></td>
                    <td><form:errors path="basicPrice"></form:errors></td>
                </tr>
                <tr>
                    <td>Capacity</td>
                    <td><form:input path="capacity"></form:input></td>
                    <td><form:errors path="capacity"></form:errors></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><form:input path="description"></form:input></td>
                    <td><form:errors path="description"></form:errors></td>
                </tr>
                <tr>
                    <td>Phone Number</td>
                    <td><form:input path="phoneNumber"></form:input></td>
                    <td><form:errors path="phoneNumber"></form:errors></td>
                </tr>
                <tr>
                    <td>City</td>
                    <td><form:select path="city">
                        <c:forEach var="city" items="${cities}">
                            <option value="${city.id}"><c:out value="${city.cityName}"></c:out></option>
                        </c:forEach>
                    </form:select></td>
                    <td><form:errors path="city"></form:errors></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Add"></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>