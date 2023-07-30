<%-- 
    Document   : profile
    Created on : Jun 1, 2023, 7:46:14 PM
    Author     : tranb
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="team3.recipe.RecipeOrganizeDTO"%>
<%@page import="team3.recipe.RecipeOrganizeDAO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:useBean id="result" class="team3.recipe.RecipeOrganizeDAO" scope="request"/>
        <meta charset="UTF-8">
        <meta name="description" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Title -->
        <title>Recipe Organize | Manager Account</title>

        <!-- Favicon -->
        <link rel="icon" href="img/recipe/favicon.ico">

        <!-- Core Stylesheet -->
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="css/managerAccount.css">
    </head>
    <body>
        <!-- Preloader -->
        <div id="preloader">
            <i class="circle-preloader"></i>
            <img src="img/recipe/salad.png" alt="">
        </div>
        

        <!-- ##### Header Area Start ##### -->
        <header class="header-area">

            <!-- Top Header Area -->
            <div class="top-header-area">
                <div class="container h-100">
                    <div class="row h-100 align-items-center justify-content-between">
                        <!-- Breaking News -->
                        <div class="col-12 col-sm-6">
                            <div class="breaking-news">
                                <div id="breakingNewsTicker" class="ticker">
                                    <ul>
                                        <c:if test="${not empty sessionScope.ADMIN}">
                                            <c:set var="customer" value="${sessionScope.ADMIN}" scope="request" />
                                        </c:if>
                                        <c:if test="${not empty sessionScope.USER}">
                                            <c:set var="customer" value="${sessionScope.USER}" scope="request" />
                                        </c:if>
                                        <li><a href="#">Hello ${customer.fullName}</a></li>
                                        <li><a href="#">Welcome to Recipe Organize</a></li>
                                        <li><a href="#">Hi Delicious!</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <!-- Top Social Info -->
                        <div class="col-12 col-sm-6">
                            <div class="top-social-info text-right">
                                <a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                                <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                                <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                                <a href="#"><i class="fa fa-dribbble" aria-hidden="true"></i></a>
                                <a href="#"><i class="fa fa-behance" aria-hidden="true"></i></a>
                                <a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Navbar Area -->
            <div class="delicious-main-menu">
                <div class="classy-nav-container breakpoint-off">
                    <div class="container">
                        <!-- Menu -->
                        <nav class="classy-navbar justify-content-between" id="deliciousNav">

                            <!-- Logo -->
                            <a class="nav-brand" href="homePage.jsp"><img src="img/recipe/logo.png" alt=""></a>

                            <!-- Navbar Toggler -->
                            <div class="classy-navbar-toggler">
                                <span class="navbarToggler"><span></span><span></span><span></span></span>
                            </div>

                            <!-- Menu -->
                            <div class="classy-menu">

                                <!-- close btn -->
                                <div class="classycloseIcon">
                                    <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                                </div>

                                <!-- Nav Start -->
                                <div class="classynav">
                                    <ul>
                                        <li class="active"><a href="homePage.jsp">Home</a></li>
                                        <li><a href="AllRecipeController">Recipes</a></li>
                                        <li><a href="favorite.jsp">Favorite</a></li>
                                        <c:if test="${(empty sessionScope.USER and empty sessionScope.ADMIN) or not empty sessionScope.USER}">
                                        <li><a href="contact.jsp">Contact</a></li>
                                        </c:if>
                                        <li><a href="about.jsp">About Us</a></li>
                                            <c:if test="${empty sessionScope.ADMIN and empty sessionScope.USER}">
                                            <li><a href="login.jsp">Login</a></li>
                                            </c:if>
                                            <c:if test="${not empty sessionScope.ADMIN or not empty sessionScope.USER}">    

                                            <li><a href="#">User</a>
                                                <div class="megamenu">
                                                    <ul class="dropdown">
                                                        <li><a href="profile.jsp">Profile</a></li>
                                                        <li><a href="plan.jsp">Meal Plan</a></li>
                                                            <c:if test="${not empty sessionScope.ADMIN}">
                                                            <li><a href="managerAccount.jsp">Management Account</a></li>
                                                            <li><a href="RecipeManagementController">Management Recipe</a></li>
                                                            </c:if>
                                                        <li><a href="LogoutController">Logout</a> </li>
                                                    </ul>
                                                </div>
                                            </li>
                                        </c:if>                                        
                                    </ul>

                                    <!-- Newsletter Form -->
                                    <div class="search-btn">
                                        <i></i>
                                    </div>

                                </div>
                                <!-- Nav End -->
                            </div>
                        </nav>
                    </div>
                </div>
            </div>

            <div class="breadcumb-area bg-img bg-overlay" style="background-image: url(img/bg-img/breadcumb4.jpg);">
                <div class="container h-100">
                    <div class="row h-100 align-items-center">
                        <div class="col-12">
                            <div class="breadcumb-text text-center">
                                <h2>Manager Account</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <br>
        </header>

    <form action="DispatchController" method="post">
        <input type="text" name="txtSearch" placeholder="Search by username">
        <input type="submit" value="Search Account" name="btAction">
    </form><br>
        <table>
            <tr>
                <th>ID</th>
                <th>User name</th>
                <th>Full name</th>
                <th>Status</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
            <c:set var="kq" value="${requestScope.usersearch}"/>
            <c:if test="${kq == null}">
                <c:set var="list" value="${requestScope.usersearch}"/>
            </c:if>
            <c:if test="${kq != null}">
                <c:set var="list" value="${requestScope.usersearch}"/>
            </c:if>
            <c:choose>
                <c:when test="${empty kq}">
                    <c:forEach items="${result.getManagerAccount()}" var="manager">
                        <tr>
                            <td>${manager.getUserID()}</td>
                            <td>${manager.getUserName()}</td>
                            <td>${manager.getFullName()}</td>
                            <td><c:choose>
                                    <c:when test="${manager.getStatus() eq 1}">Active</c:when>
                                    <c:otherwise>Inactive</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${manager.getPhone()}</td>
                            <td>${manager.getEmail()}</td>
                            <td>
                                <c:choose >
                                    <c:when test="${manager.getRole() eq true}">Admin</c:when>
                                    <c:otherwise>User</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:if test="${manager.getRole() eq false}">
                                    <c:url var="mylink" value="DispatchController">
                                        <c:param name="userName" value="${manager.getUserName()}"></c:param>
                                        <c:param name="status" value="${manager.getStatus()}"></c:param>
                                        <c:param name="btAction" value="updateStatusAccount"></c:param>
                                    </c:url>
                                    <a class="action-link" href="${mylink}">
                                        <c:choose>
                                            <c:when test="${manager.getStatus() eq 1}">Block</c:when>
                                            <c:otherwise>Unlock</c:otherwise>
                                        </c:choose>
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="manager" items="${list}">
                        <tr>
                            <td>${manager.getUserID()}</td>
                            <td>${manager.getUserName()}</td>
                            <td>${manager.getFullName()}</td>
                            <td><c:choose>
                                    <c:when test="${manager.getStatus() eq 1}">Active</c:when>
                                    <c:otherwise>Inactive</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${manager.getPhone()}</td>
                            <td>${manager.getEmail()}</td>
                            <td>
                                <c:choose >
                                    <c:when test="${manager.getRole() eq true}">Admin</c:when>
                                    <c:otherwise>User</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:if test="${manager.getRole() eq false}">
                                    <c:url var="mylink" value="DispatchController">
                                        <c:param name="userName" value="${manager.getUserName()}"></c:param>
                                        <c:param name="status" value="${manager.getStatus()}"></c:param>
                                        <c:param name="btAction" value="updateStatusAccount"></c:param>
                                    </c:url>
                                    <a class="action-link" href="${mylink}">
                                        <c:choose>
                                            <c:when test="${manager.getStatus() eq 1}">Block</c:when>
                                            <c:otherwise>Unlock</c:otherwise>
                                        </c:choose>
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </table>



        <!-- ##### Follow Us Instagram Area Start ##### -->
        <div class="follow-us-instagram">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h5>Enjoy Your Passion</h5>
                    </div>
                </div>
            </div>
            <!-- Instagram Feeds -->
            <div class="insta-feeds d-flex flex-wrap">
                <!-- Single Insta Feeds -->
                <div class="single-insta-feeds">
                    <img src="img/bg-img/insta1.jpg" alt="">
                    <!-- Icon -->
                    <div class="insta-icon">
                        <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                    </div>
                </div>

                <!-- Single Insta Feeds -->
                <div class="single-insta-feeds">
                    <img src="img/bg-img/insta2.jpg" alt="">
                    <!-- Icon -->
                    <div class="insta-icon">
                        <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                    </div>
                </div>

                <!-- Single Insta Feeds -->
                <div class="single-insta-feeds">
                    <img src="img/bg-img/insta3.jpg" alt="">
                    <!-- Icon -->
                    <div class="insta-icon">
                        <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                    </div>
                </div>

                <!-- Single Insta Feeds -->
                <div class="single-insta-feeds">
                    <img src="img/bg-img/insta4.jpg" alt="">
                    <!-- Icon -->
                    <div class="insta-icon">
                        <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                    </div>
                </div>

                <!-- Single Insta Feeds -->
                <div class="single-insta-feeds">
                    <img src="img/bg-img/insta5.jpg" alt="">
                    <!-- Icon -->
                    <div class="insta-icon">
                        <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                    </div>
                </div>

                <!-- Single Insta Feeds -->
                <div class="single-insta-feeds">
                    <img src="img/bg-img/insta6.jpg" alt="">
                    <!-- Icon -->
                    <div class="insta-icon">
                        <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                    </div>
                </div>

                <!-- Single Insta Feeds -->
                <div class="single-insta-feeds">
                    <img src="img/bg-img/insta7.jpg" alt="">
                    <!-- Icon -->
                    <div class="insta-icon">
                        <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <!-- ##### Follow Us Instagram Area End ##### -->

        <!-- ##### Footer Area Start ##### -->
        <footer class="footer-area">
            <div class="container h-100">
                <div class="row h-100">
                    <div class="col-12 h-100 d-flex flex-wrap align-items-center justify-content-between">
                        <!-- Footer Social Info -->
                        <div class="footer-social-info text-right">
                            <a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                            <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                            <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                            <a href="#"><i class="fa fa-dribbble" aria-hidden="true"></i></a>
                            <a href="#"><i class="fa fa-behance" aria-hidden="true"></i></a>
                            <a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
                        </div>
                        <!-- Footer Logo -->
                        <div class="footer-logo">
                            <a href="homePage.jsp"><img src="img/recipe/logo.png" alt=""></a>
                        </div>

                    </div>
                </div>
            </div>
        </footer>
        <!-- ##### All Javascript Files ##### -->
        <!-- jQuery-2.2.4 js -->
        <script src="js/jquery/jquery-2.2.4.min.js"></script>
        <!-- Popper js -->
        <script src="js/bootstrap/popper.min.js"></script>
        <!-- Bootstrap js -->
        <script src="js/bootstrap/bootstrap.min.js"></script>
        <!-- All Plugins js -->
        <script src="js/plugins/plugins.js"></script>
        <!-- Active js -->
        <script src="js/active.js"></script>
    </body>
</html>
