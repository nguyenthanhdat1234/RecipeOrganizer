<%-- 
    Document   : forgot_password
    Created on : Jun 1, 2023, 7:39:04 PM
    Author     : MSI BH
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="img/recipe/favicon.ico">       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
        <link rel="stylesheet" type="text/css" href="css/forgot_password.css">
    </head>
    <body>
        <div id="colorText">
        <h1>Reset your password</h1>
        <p>Enter your email address and we will send you code to reset your password.</p>
        <form action="DispatchController" method="POST">
            <table>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name ="txtemail" value="${param.txtemail}" placeholder="Enter your email">
                        <input type="submit" value ="Send" name="btAction">
                        <font color="red">
                        <c:set var="errors" value="${requestScope.ERROR}" />
                        <c:if test="${not empty errors}">
                            <c:if test="${not empty errors.emailError}">
                                ${errors.emailError} <br/>
                            </c:if>
                            <c:if test="${not empty errors.inexistingEmailError}">
                                <p>${errors.inexistingEmailError} <br/></p>
                                <p>Don’t you have an account? <a href="registration.jsp">Sign up</a></p>
                            </c:if>
                        </c:if>
                        </font>
                    </td>              
                </tr>


                <c:if test="${requestScope.COUNT == 1}">
                    <c:if test="${empty errors.emailError and empty errors.inexistingEmailError}">
                        <tr>
                            <td>Code</td>
                            <td><input type="password" name ="txttoken" value="${param.txttoken}" placeholder="Enter your token">
                                <font color="red">
                                <c:set var="invalid_code" value="${requestScope.INVALID_CODE}" />
                                <c:if test="${not empty invalid_code}">                           
                                    ${invalid_code} <br/>
                                </c:if>
                                </font>
                            </td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password" name ="txtpassword" value="${param.txtpassword}" placeholder="New password">
                                <font color="red">
                                <c:set var="invalid_password" value="${requestScope.INVALID_PASSWORD}" />
                                <c:if test="${not empty invalid_password}">                           
                                    ${invalid_password} <br/>
                                </c:if>
                                </font>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="submit" value ="Reset" name="btAction"></td>
                        </tr>
                    </c:if>
                </c:if>

            </table>
        </form>
        </div>
        <c:if test="${requestScope.SEND_SUCCESS}">
            <c:set var="send_success" value="${requestScope.SEND_SUCCESS}" scope="request" />
        </c:if>
        <script>
            var result = ${send_success};
            if (result) {
                alert("Gửi mail thành công.");
            }
        </script>
<script src="js/change_color_text.js"></script>

    </body>
</html>