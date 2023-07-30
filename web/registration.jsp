<%-- 
    Document   : registration
    Created on : May 29, 2023, 2:50:58 PM
    Author     : AS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="img/recipe/favicon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
        <script src="js/signup_error.js"></script>
        <link rel="stylesheet" href="css/registration.css">
    </head>
    <body>
        <form action="DispatchController" method="POST" class="form-signup">
            <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Sign up</h1>
            <div class="aleart alert-danger" role="alert">
                ${mes}
            </div>

            <table>
                <tr>
                    <td>Username*</td>
                    <td><input type="text" name ="txtusername" value="${param.txtusername}" placeholder="1 - 30 characters"> 
                        <font color="red">
                        <c:set var="errors" value="${requestScope.ERROR}" />
                        <c:if test="${not empty errors}">
                            <c:if test="${not empty errors.usernameLengthError}">
                                ${errors.usernameLengthError} <br/>
                            </c:if>
                            <c:if test="${not empty errors.existedUsernameError}">
                                ${errors.existedUsernameError} <br/>
                            </c:if>
                        </c:if>
                        </font>
                    </td>

                </tr>
                <tr>
                    <td>Password*</td>
                    <td><input type="password" name ="txtpassword" value="${param.txtpassword}" placeholder="1 - 30 characters">
                        <font color="red">
                        <c:set var="errors" value="${requestScope.ERROR}" />
                        <c:if test="${not empty errors}">
                            <c:if test="${not empty errors.passwordLengError}">
                                ${errors.passwordLengError} <br/>
                            </c:if>
                        </c:if>
                        </font>
                    </td>

                </tr>
                <tr>
                    <td>Comfirm*</td>
                    <td><input type="password" name ="txtrepassword" value="" placeholder="confirm password">
                        <font color="red">
                        <c:set var="errors" value="${requestScope.ERROR}" />
                        <c:if test="${not empty errors}">
                            <c:if test="${not empty errors.confirmError}">
                                ${errors.confirmError} <br/>
                            </c:if>
                        </c:if>
                        </font>
                    </td>

                </tr>
                <tr>
                    <td>FullName*</td>
                    <td><input type="text" name ="txtfullname" value="${param.txtfullname}" placeholder="1 - 50 characters">
                        <font color="red">
                        <c:set var="errors" value="${requestScope.ERROR}" />
                        <c:if test="${not empty errors}">
                            <c:if test="${not empty errors.fullNameLengthError}">
                                ${errors.fullNameLengthError} <br/>
                            </c:if>
                        </c:if>
                        </font>
                    </td>

                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type="text" name ="txtphone" value="${param.txtphone}">
                        <font color="red">
                        <c:set var="errors" value="${requestScope.ERROR}" />
                        <c:if test="${not empty errors}">
                            <c:if test="${not empty errors.phoneLengthError}">
                                ${errors.phoneLengthError} <br/>
                            </c:if>
                        </c:if>
                        </font>
                    </td>

                </tr>
                <tr>
                    <td>Email*</td>
                    <td><input type="text" name ="txtemail" value="${param.txtemail}" placeholder="your email">
                        <font color="red">
                        <c:set var="errors" value="${requestScope.ERROR}" />
                        <c:if test="${not empty errors}">
                            <c:if test="${not empty errors.emailError}">
                                ${errors.emailError} <br/>
                            </c:if>
                                <c:if test="${not empty errors.existedEmailError}">
                                ${errors.existedEmailError} <br/>
                            </c:if>
                        </c:if>
                        </font>
                        
                    </td>

                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value ="Sign up" name="btAction"></td>
                </tr>

            </table>
        </form>
    </body>
</html>


