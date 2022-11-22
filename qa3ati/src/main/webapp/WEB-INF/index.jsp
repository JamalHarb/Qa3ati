<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style.css">
<meta charset="ISO-8859-1">
<title>Project Manager</title>
<style>
    *{
        margin: 0;
        padding: 0;
    }
    h1.welcome{
        color: #2b78e4;
        /* text-align: center; */
    }
    header{
        margin-bottom: 5%;
    }
    .container{
        width: 85%;
        margin-top: 5%;
        margin-left: 7.5%;
        /* margin: 0 auto; */
        display: flex;
        flex-direction: column;
        align-items: center;
        /* border: 1px dotted red; */
    }
    .log-reg{
        width: 100%;
        display: flex;
        justify-content: space-between;
        /* border: 1px dotted red; */
    }
    .reg, .log{
        /* border: 1px dotted red; */
        width: 30%;
        display: flex;
        flex-direction: column;
    }
    .log-reg h1{
        margin-bottom: 3%;
    }
    table{
        margin-bottom: 3%;
    }
    .error{
        color: red;
    }
</style>
</head>
<body>
    <div class="container">
        <header>
            <h1 class="welcome">Qa3ati</h1>
            <p class="welcome">A place for people to reserve halls.</p>
        </header>
        <div class="log-reg">
            <div class="reg">
                <h1>Register</h1>
                <form:form action="/register" method="post" modelAttribute="newUser">
                    <table>
                        <tbody>
                            <tr>
                                <td></td>
                                <td><form:errors class="error" path="userName"></form:errors></td>
                            </tr>
                            <tr>
                                <td class="left">User Name:</td>
                                <td><form:input path="userName"></form:input></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><form:errors class="error" path="email"></form:errors></td>
                            </tr>
                            <tr>
                                <td class="left">Email:</td>
                                <td><form:input path="email"></form:input></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><form:errors class="error" path="password"></form:errors></td>
                            </tr>
                            <tr>
                                <td class="left">Password:</td>
                                <td><form:input type="password" path="password"></form:input></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><form:errors class="error" path="confirm"></form:errors></td>
                            </tr>
                            <tr>
                                <td class="left">Confirm Password:</td>
                                <td><form:input type="password" path="confirm"></form:input></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td align="right"><input class="btn" type="submit" value="Register"></td>
                            </tr>
                        </tbody>
                    </table>
                </form:form>
            </div>
            <div class="log">
                <h1>Login</h1>
                <form:form action="/login" method="post" modelAttribute="newLogin">
                    <table>
                        <tbody>
                            <tr>
                                <td>Email:</td>
                                <td><form:input path="email"></form:input></td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td><form:input type="password" path="password"></form:input></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td align="right"><input class="btn" type="submit" value="Login"></td>
                            </tr>
                        </tbody>
                    </table>
                    <p><form:errors class="error" path="email"></form:errors></p>
                    <p><form:errors class="error" path="password"></form:errors></p>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>