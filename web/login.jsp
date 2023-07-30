<%-- 
    Document   : login
    Created on : May 23, 2023, 1:44:45 PM
    Author     : THIS PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Login page</title>   
        <link rel="icon" href="img/recipe/favicon.ico">
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900'
              rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/animate.css">
        <link rel="stylesheet" href="css/css.css">
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    </head>
    <body>
        <div id="colorText">
        <div class="container">
            <div class="login-box animated fadeInUp" id="login">
                <div class="box-header">
                    <h2>Log In</h2>
                </div>
                <form id ="myform"action="DispatchController" method = "post" class="formlogin">
                    <font style='color:red;'><%= (request.getAttribute("WARNING") == null) ? "" : (String) request.getAttribute("WARNING")%> </font>

                    <table>
                        <tr>
                            <td>Username</td>
                            <td><input type="text" name ="txtUsername" value="${param.txtUsername}"></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password" name ="txtPassword" value="${param.txtPassword}"></td>
                        </tr>
                        <tr>
                        <td colspan="2"><input type="submit" value ="Login" name="btAction"></td>
                        </tr>

                    </table>

                </form>		
                <c:if test="${requestScope.WRONG}">
                    <c:set var="wrong" value="${requestScope.WRONG}" scope="request" />
                </c:if>
                <script>
                    var result = ${wrong};
                    if (result) {
                        alert("Tài khoản hoặc mật khẩu không đúng.");
                    }
                </script>

                <c:if test="${requestScope.BAN_ACC}">
                    <c:set var="ban_acc" value="${requestScope.BAN_ACC}" scope="request" />
                </c:if>
                <script>
                    var result1 = ${ban_acc};
                    if (result1) {
                        alert("Tài khoản không khả dụng.");
                    }
                </script>

                <c:if test="${requestScope.SIGNUP_SUCCESS}">
                    <c:set var="signup_successfully" value="${requestScope.SIGNUP_SUCCESS}" scope="request" />
                </c:if>
                <script>
                    var result2 = ${signup_successfully};
                    if (result2) {
                        alert("Đăng ký thành công.");
                    }
                </script>

                <c:if test="${requestScope.GMAIL_HAVEN_NOT_IN_DATABASE}">
                    <c:set var="gmail_have_not_in_database" value="${requestScope.GMAIL_HAVEN_NOT_IN_DATABASE}" scope="request" />
                </c:if>
                <script>
                    var result3 = ${gmail_have_not_in_database};
                    if (result3) {
                        alert("Bạn chưa đăng ký tài khoản");
                    }
                </script>

                <div class="small">
                    <p>Don’t you have an account? <a href="registration.jsp" style="color: green;">Sign up</a></p><br/>
                    <p><a href="forgot_password.jsp" style="color: green;">Forgot password?</a></p><br>
                    <button><a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8084/RecipeOrgainze/login-google&response_type=code
                   &client_id=416998610666-lbm6raon0at7tl6ldiiippgtsfbfq10e.apps.googleusercontent.com&approval_prompt=force" class="google-login-button">
                            <i class="fab fa-google"></i> Login with google</a> </button>
                </div>
                <br/>
            </div>
                    </div>
                    </div>
                <style>
                    .google-login-button {
                        display: inline-block;
                        background-color: #dd4b39;
                        color: #fff;
                        padding: 10px 20px;
                        text-decoration: none;
                        border-radius: 4px;
                        font-weight: bold;
                    }
                </style>

                <script src="js/change_color_text.js"></script>
                </body>
                </html>
