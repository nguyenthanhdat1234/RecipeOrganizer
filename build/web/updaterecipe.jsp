<%-- 
    Document   : updaterecipe
    Created on : Jul 9, 2023, 11:39:48 PM
    Author     : dangt
--%>
<%@page import="java.io.File"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="result" class="team3.recipe.RecipeOrganizeDAO" scope="request"/>
        <meta charset="UTF-8">
        <meta name="description" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Update Recipe</title>

        <!-- Đường dẫn tới file CSS của trang -->
        <!-- Favicon -->
        <link rel="icon" href="img/recipe/favicon.ico">
        <!-- Core Stylesheet -->
        <link rel="stylesheet" href="style.css">
          <link rel="stylesheet" href="css/update.css">
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
                                                        <li><a href="plan.jsp">Meal Planer</a></li>
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

            <!-- ##### Breadcumb Area Start ##### -->
            <div class="breadcumb-area bg-img bg-overlay" style="background-image: url(img/bg-img/breadcumb4.jpg);">
                <div class="container h-100">
                    <div class="row h-100 align-items-center">
                        <div class="col-12">
                            <div class="breadcumb-text text-center">
                                <h2>Update Recipe</h2>
                               
                            </div>
                        </div>
                    </div>
                </div>
            </div> <br>
        </header>



        <div class="update-form">
            <form action="UpdateRecipeServlet" method="post">
                <input type="hidden" name="recipeID" value="${recipeID}">
                <div class="form-group">
                    <label for="recipeName">Recipe Name:</label>
                    <input type="text" id="recipeName" name="recipeName" value="${recipeName}" class="form-control">
                </div>
                <div class="form-group">
                    <label for="caloRecipe">Calories:</label>
                    <input type="text" id="caloRecipe" name="caloRecipe" value="${caloRecipe}" class="form-control">
                </div>
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" name="description" class="form-control">${description}</textarea>
                </div>
                <div class="form-group">
                    <label for="imgUrl">Image URL:</label>
                    <select name="imgUrl" id="imgUrl" required class="form-control select-with-scroll">
                        <option value="" selected disabled>Choose an image</option>
                        <%-- Add image options from directory --%>
                        <%
                            String directoryPath = getServletContext().getRealPath("/img/recipe");
                            File[] files = new File(directoryPath).listFiles();
                            for (File file : files) {
                                if (file.isFile() && (file.getName().endsWith(".jpg") || file.getName().endsWith(".png"))) {
                                    String fileName = file.getName();
                        %>
                        <option value="<%= "img/recipe/" + fileName%>"><%= fileName%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="difficulty">Difficulty:</label>
                    <select name="difficulty" id="difficulty" required class="form-control">
                        <option value="" selected disabled>Choose difficulty</option>
                        <option value="High">High</option>
                        <option value="Easy">Easy</option>
                        <option value="Medium">Medium</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="ingredient_table">Ingredients:</label>
                    <textarea id="ingredient_table" name="ingredient_table" class="form-control">${ingredient_table}</textarea>
                </div>
                <div class="form-group">
                    <label for="categoryID">Category:</label>
                    <select name="categoryID" id="categoryID" class="form-control">
                        <option value="1">Main Dish</option>
                        <option value="2">Pasta</option>
                        <option value="3">Salad</option>
                        <option value="4">Vegetarian</option>
                        <option value="5">Dessert</option>
                        <option value="6">Bakery</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="DescriptionName">Step:</label>
                    <textarea id="steps" name="steps" class="form-control">${DescriptionName}</textarea>
                </div>
                <input type="submit" value="Update Recipe" class="btn-submit btn btn-primary">
            </form>
        </div>
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
