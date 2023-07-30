<%-- 
    Document   : profile
    Created on : Jun 1, 2023, 7:46:14 PM
    Author     : tranb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="team3.recipe.RecipeOrganizeDTO"%>
<%@page import="team3.recipe.RecipeOrganizeDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Title -->
        <title>Recipe Organize | Profile</title>

        <!-- Favicon -->
        <link rel="icon" href="img/recipe/favicon.ico">

        <!-- Core Stylesheet -->
        <link rel="stylesheet" href="style.css">

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
                                <h2>Profile</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <br>
        </header>
        <header>
            <%
                RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("USER");
                RecipeOrganizeDTO admin = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                if (user != null || admin != null) {
                    if (user != null && admin == null) {

            %>
            <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
            <div class="container">
                <div class="row flex-lg-nowrap">
                    <div class="col">
                        <div class="row">
                            <div class="col mb-3">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="e-profile">                                            
                                            <form action="ChangePhotoProfileController" method = "post"> 
                                                <div class="row">
                                                    <div class="col-12 col-sm-auto mb-3">
                                                        <div class="mx-auto" style="width: 140px;">
                                                            <div class="d-flex justify-content-center align-items-center rounded" style="height: 140px; background-color: rgb(233, 236, 239);">
                                                                <c:if test="${sessionScope.USER.image_path == null}">
                                                                    <img src="img/recipe/default-user.jpg" alt="hình ảnh" width="140" height="140"/>
                                                                </c:if>
                                                                <c:if test="${sessionScope.USER.image_path != null}">
                                                                    <img src="${sessionScope.USER.image_path}" alt="picture" width="140" height="140" />
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
                                                        <div class="text-center text-sm-left mb-2 mb-sm-0">
                                                            <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap"><%= user.getFullName()%></h4>
                                                            <p class="mb-0">Fullname: <%= user.getFullName()%></p>
                                                            
                                                                     <c:if test="${sessionScope.USER.phone == null}">
                                                                    <p class="mb-0">Phone: Empty</p>
                                                                </c:if>
                                                                    <c:if test="${sessionScope.USER.phone != null}">
                                                                    <p class="mb-0">Phone: <%= user.getPhone()%></p>
                                                                </c:if>
                                                            <p class="mb-0">Email: <%= user.getEmail()%></p>
                                
                                                            <div class="mt-2">
                                                                <button class="btn btn-primary" type="file" accept=".jpg,.png">
                                                                    <i class="fa fa-fw fa-camera"></i>
                                                                    <span>Change Photo</span>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
 
                <%
                                            } else {
                                            %>
                                            
                                            <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
                                            <div class="container">
                                                <div class="row flex-lg-nowrap">
                                                    <div class="col">
                                                        <div class="row">
                                                            <div class="col mb-3">
                                                                <div class="card">
                                                                    <div class="card-body">
                                                                        <div class="e-profile">
                                                                            <form action="ChangePhotoProfileController" method = "post"> 
                                                                                <div class="row">
                                                                                    <div class="col-12 col-sm-auto mb-3">
                                                                                        <div class="mx-auto" style="width: 140px;">
                                                                                            <div class="d-flex justify-content-center align-items-center rounded" style="height: 140px; background-color: rgb(233, 236, 239);">
                                                                                                <c:if test="${sessionScope.ADMIN.image_path == null}">
                                                                                                    <img src="img/recipe/default-user.jpg" alt="hình ảnh" width="140" height="140"/>
                                                                                                </c:if>
                                                                                                <c:if test="${sessionScope.ADMIN.image_path != null}">
                                                                                                    <img src="${sessionScope.ADMIN.image_path}" alt="picture" width="140" height="140" />
                                                                                                </c:if>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
                                                                                        <div class="text-center text-sm-left mb-2 mb-sm-0">
                                                                                            <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap"><%= admin.getFullName()%></h4>
                                                                                            <p class="mb-0">Fullname: <%= admin.getFullName()%></p>
                                                                                            <c:if test="${sessionScope.ADMIN.phone == null}">
                                                                    <p class="mb-0">Phone: Empty</p>
                                                                </c:if>
                                                                    <c:if test="${sessionScope.ADMIN.phone != null}">
                                                                    <p class="mb-0">Phone: <%= admin.getPhone()%></p>
                                                                </c:if>
                                                                                            <p class="mb-0">Email: <%= admin.getEmail()%></p>
                                                                                            <div class="mt-2">
                                                                                                <button class="btn btn-primary" type="file" accept=".jpg,.png">
                                                                                                    <i class="fa fa-fw fa-camera"></i>
                                                                                                    <span>Change Photo</span>
                                                                                                </button>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </form>
                                                                                            
                                                                            <% } %>
                                                                            <% String message1 = (String) request.getAttribute("message1"); %>
                                                                            <% if (message1 != null) {%>
                                                                            <p style="color: red;"><%= message1%></p>
                                                                            <% }%>  
                                                                            <% String message2 = (String) request.getAttribute("message2"); %>
                                                                            <% if (message2 != null) {%>
                                                                            <p style="color: red;"><%= message2%></p>
                                                                            <% }%>
                                                                            <% String message3 = (String) request.getAttribute("message3"); %>
                                                                            <% if (message3 != null) {%>
                                                                            <p style="color: red;"><%= message3%></p>
                                                                            <% }%>
                                                                            <% String message4 = (String) request.getAttribute("message4"); %>
                                                                            <% if (message4 != null) {%>
                                                                            <p style="color: red;"><%= message4%></p>
                                                                            <% }%>
                                                                            <% String message5 = (String) request.getAttribute("message5"); %>
                                                                            <% if (message5 != null) {%>
                                                                            <p style="color: green;"><%= message5%></p>
                                                                            <% }%>
                                                                            <% String message6 = (String) request.getAttribute("message6"); %>
                                                                            <% if (message6 != null) {%>
                                                                            <p style="color: green;"><%= message6%></p>
                                                                            <% }%>
                                                                            <ul class="nav nav-tabs">
                                                                                <li class="nav-item"><a href="" class="active nav-link">Update Profile</a></li>
                                                                            </ul>
                                                                            <div class="tab-content pt-3">
                                                                                <div class="tab-pane active">
                                                                                    <form action="DispatchController" method="post" class="form" novalidate="">
                                                                                        <div class="row">
                                                                                            <div class="col">
                                                                                                <div class="row">
                                                                                                    <div class="col">
                                                                                                        <div class="form-group">
                                                                                                            <label>Full Name</label>
                                                                                                            <c:if test="${sessionScope.USER != null}">
                                                                                                                <input class="form-control" type="text" name="txtFullname" value="<%= user.getFullName()%>" placeholder="1 - 50 characters">
                                                                                                            </c:if>
                                                                                                            <c:if test="${sessionScope.ADMIN != null}">
                                                                                                                <input class="form-control" type="text" name="txtFullname" value="<%= admin.getFullName()%>" placeholder="1 - 50 characters">
                                                                                                            </c:if>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                    <div class="col">
                                                                                                        <div class="form-group">
                                                                                                            <label>Phone</label>
                                                                                                            <c:if test="${sessionScope.USER != null}">
                                                                                                                <input class="form-control" type="number" name="txtPhone" value="<%= user.getPhone()%>">
                                                                                                            </c:if>
                                                                                                            <c:if test="${sessionScope.ADMIN != null}">
                                                                                                                <input class="form-control" type="number" name="txtPhone" value="<%= admin.getPhone()%>">
                                                                                                            </c:if>
                                                                                                            
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                                <div class="row">
                                                                                                    <div class="col">
                                                                                                        <div class="form-group">
                                                                                                            <label>Email</label>
                                                                                                            <c:if test="${sessionScope.USER != null}">
                                                                                                               <input class="form-control" type="text" name="txtEmail" value="<%= user.getEmail()%>" placeholder="your email">
                                                                                                            </c:if>
                                                                                                            <c:if test="${sessionScope.ADMIN != null}">
                                                                                                                <input class="form-control" type="text" name="txtEmail" value="<%= admin.getEmail()%>" placeholder="your email">
                                                                                                            </c:if>
                                                                                                            
                                                                                                        </div>

                                                                                                    </div>
                                                                                                </div>
                                                                                                <div>
                                                                                                    <input type="submit" value ="Update" name="btAction" class="btn btn-primary">
                                                                                                </div>
                                                                                            </div> 
                                                                                        </div>
                                                                                    </form>
                                                                                </div>

                                                                            </div>

                                                                            <div class="tab-content pt-3">
                                                                                <div class="tab-pane active">
                                                                                    <form action="DispatchController" method="post" class="form" novalidate="">
                                                                                        <div class="row">
                                                                                            <div class="col-12 col-sm-6 mb-3">
                                                                                                <div class="mb-2"><b>Change Password</b></div>
                                                                                                <div class="row">
                                                                                                    <div class="col">
                                                                                                        <div class="form-group">
                                                                                                            <label>Username</label>
                                                                                                            <input class="form-control" type="text" name="txtUsername" placeholder="Enter a username" value="${param.txtUsername}">
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                                <div class="row">
                                                                                                    <div class="col">
                                                                                                        <div class="form-group">
                                                                                                            <label>Current Password</label>
                                                                                                            <input class="form-control" type="password" name="txtCurrentPassword" placeholder="Enter a current password">
                                                                                                            <font color="red">
                                                                                                            <c:if test="${not empty message8}">
                                                                                                                ${message8} <br/>
                                                                                                            </c:if>
                                                                                                            </font>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                                <div class="row">
                                                                                                    <div class="col">
                                                                                                        <div class="form-group">
                                                                                                            <label>New Password</label>
                                                                                                            <input class="form-control" type="password" name="txtNewPassword" placeholder="Enter a new password">
                                                                                                            <font color="red">                                                                                     
                                                                                                            <c:if test="${not empty message7}">
                                                                                                                ${message7} <br/>
                                                                                                            </c:if>
                                                                                                            </font>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="row">
                                                                                            <div class="col d-flex justify-content-end">
                                                                                                <input type="submit" value ="Save" name="btAction" class="btn btn-primary">

                                                                                            </div>
                                                                                        </div>
                                                                                    </form>

                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>


                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <%
                                            } else {
                                            %>
                                            <p>Cannot find user with username <%= request.getParameter("txtUsername")%></p>
                                            <%
                                                }
                                            %>
                                            
                                            </header>
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
