<%-- 
    Document   : showrecipe
    Created on : Jun 3, 2023, 11:42:02 AM
    Author     : AS
--%>
<%@page import="team3.DTO.ShowRatingDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="team3.recipe.RecipeOrganizeDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="background-color: white">
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Title -->
        <title>Recipe Organize | Recipe Post</title>

        <!-- Favicon -->
        <link rel="icon" href="img/recipe/favicon.ico">
        <link rel='stylesheet prefetch' href='https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css'>

        <!-- Core Stylesheet -->
        <link rel="stylesheet" href="recipe.css">
        <link rel="stylesheet" href="css/rating.css">
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="css/TotalRating.css">
    </head>
    <body>
        <div style="background-color: white">
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
            </header>

            <!-- ##### Breadcumb Area Start ##### -->
            <div class="breadcumb-area bg-img bg-overlay" style="background-image: url(img/bg-img/breadcumb3.jpg);">
                <div class="container h-100">
                    <div class="row h-100 align-items-center">
                        <div class="col-12">
                            <div class="breadcumb-text text-center">
                                <h2>Recipe</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ##### Breadcumb Area End ##### -->

            <div class="receipe-post-area section-padding-80">

                <!-- Receipe Post Search -->
                <div class="receipe-post-search mb-80">
                    <div class="container">

                        <form value="${txtS}" action="SearchController" method="post">
                            <div class="row">
                                <!-- Recipe Category-->
                                <div class="col-12 col-lg-4">
                                    <ul class="category-list">
                                        <select onchange="location = this.value;">
                                            <option value="CategoryController?categoryID=0&name=All Recipe Categories" ${txtS == 'All Recipe Categories' ? 'selected' : ''}>All Recipe Categories</option>
                                            <option value="CategoryController?categoryID=1&name=Main Dish" ${txtS == 'Main Dish' ? 'selected' : ''}>Main Dish</option>
                                            <option value="CategoryController?categoryID=2&name=Pasta" ${txtS == 'Pasta' ? 'selected' : ''}>Pasta</option>
                                            <option value="CategoryController?categoryID=3&name=Salad" ${txtS == 'Salad' ? 'selected' : ''}>Salad</option>
                                            <option value="CategoryController?categoryID=4&name=Vegetarian" ${txtS == 'Vegetarian' ? 'selected' : ''}>Vegetarian</option>
                                            <option value="CategoryController?categoryID=5&name=Dessert" ${txtS == 'Dessert' ? 'selected' : ''}>Dessert</option>
                                            <option value="CategoryController?categoryID=6&name=Bakery" ${txtS == 'Bakery' ? 'selected' : ''}>Bakery</option>
                                        </select>
                                    </ul>
                                </div>
                                <!-- Recipe Search-->
                                <div class="col-12 col-lg-4">
                                    <input type="search" name="txtSearch" placeholder="Search Receipies" >
                                </div>
                                <div class="col-12 col-lg-4 text-right">
                                    <button type="submit" class="btn delicious-btn" name="btAction" value="search">Search</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!--Detail Page -->
                <c:forEach items="${detailP}" var="detail">
                    <div class="container">
                        <div class="row">
                            <div class="col-12">
                                <div class="receipe-slider owl-carousel image-recipe">
                                    <img src="${detail.imgUrl}">
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Receipe Content Area -->
                    <div class="receipe-content-area">
                        <div class="container">
                            <div class="row">
                                <div class="col-12">
                                    <div class="receipe-headline my-5">
                                        <span>April 05, 2018</span>
                                        <h2 style="color:  #008000;font-family: serif,Times,Times New Roman">${detail.recipeName}</h2>
                                        <span style="font-family: serif,Times,Times New Roman">
                                            <p style="color: black;font-size: 18px">${detail.description}</p>
                                        </span>

                                        <div class="row">
                                            <div class="col-12 col-md-8">   
                                                <div class="method-card">
                                                    <div >
                                                        <h4>How To Cook</h4>
                                                    </div>
                                                    <div class="show-steps">
                                                        <% String[] steps_recipe = (String[]) request.getAttribute("steps");
                                                            if (steps_recipe
                                                                    != null && steps_recipe.length > 0) {
                                                                for (int i = 0; i < steps_recipe.length; i++) {%>
                                                        <p style='color: black; font-size: 16px'>
                                                            <%= steps_recipe[i]%>
                                                        </p>
                                                        <% }
                                                            } else {
                                                                return;
                                                            } %>
                                                    </div>
                                                </div>
                                            </div>


                                            <!-- Ingredients -->
                                            <div class="col-12 col-md-4">
                                                <div class="ingredients">
                                                    <h4>Ingredients</h4>
                                                    <div class="show-ingde">
                                                        <% String[] ingde = (String[]) request.getAttribute("ingde");
                                                            if (ingde != null
                                                                    && ingde.length > 0) {
                                                                for (int i = 0; i < ingde.length; i++) {%>
                                                        <!-- your code here -->
                                                        <p style='color: black; margin-left: 20px'> - <%= ingde[i]%></p>
                                                        <% }
                                                            } else {
                                                                return;
                                                            }%>
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
            </c:forEach>
            <!-- End Detail Page -->
            <%
                    RecipeOrganizeDTO admin = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                    RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("USER");

                    if (user != null || admin != null) {
                %>
            <%
                List<ShowRatingDTO> result
                        = (List<ShowRatingDTO>) session.getAttribute("SHOWRATING");
                if (result != null) {
            %>
                    <%
                        for (ShowRatingDTO dto : result) {
                    %>
                         <div class="rating-container">
                            <h1>Total rating: <%= dto.getRatingCount()%></h1>
                            <p>Average rating: <%= dto.getAverageRating()%></p>
                        </div>
                    <%
                        }
                    %>
            <%
            } else {
            %>
            <h1>No Rating</h1>
            <%
                }
             } else {
                %>
                <h4>You need login for rating</h4>
                <%
                    }
                %>
            <div class="stars">
                <c:forEach items="${detailP}" var="detail">
                <div class="stars">
                    <form action="RatingController" method="POST">
                        <input class="star star-5" id="star-5" value="5" type="radio" name="ratingValue"/>
                        <label class="star star-5" for="star-5"></label>
                        <input class="star star-4" id="star-4" value="4" type="radio" name="ratingValue"/>
                        <label class="star star-4" for="star-4"></label>
                        <input class="star star-3" id="star-3" value="3" type="radio" name="ratingValue"/>
                        <label class="star star-3" for="star-3"></label>
                        <input class="star star-2" id="star-2" value="2" type="radio" name="ratingValue"/>
                        <label class="star star-2" for="star-2"></label>
                        <input class="star star-1" id="star-1" value="1" type="radio" name="ratingValue"/>
                        <label class="star star-1" for="star-1"></label>
                        <input type="hidden" name="recipeID" value="${detail.recipeID}" />
                        <input type="submit" value="Save" name="ratingValue" />
                    </form>
                </div>
                        
            </c:forEach>

            </div>
            <!-- Comment and Review-->
            <div class="row">
                <div class="col-12">
                    <div class="section-heading text-left">
                        <link rel="stylesheet" type="text/css" href="css/feedback.css">
                        <div class="comments-container">
                            <%-- Hiển thị thông báo lỗi nếu có --%>
                            <c:if test="${not empty commentError}">
                                <p style="color: red">${commentError}</p>
                            </c:if>

                            <%-- Form để người dùng thêm bình luận --%>
                            <h2>Add Comment:</h2>
                            <form action="DetailController" method="post" class="add-comment-form">
                                <c:forEach items="${detailP}" var="detail">
                                    <input type="hidden" name="recipeID" value="${detail.recipeID}" /> <%-- Giá trị recipeID giả định --%>
                                </c:forEach>
                                <textarea name="comment" rows="4" cols="50"></textarea>
                                <br/>
                                <c:if test="${empty sessionScope.USER && empty sessionScope.ADMIN}">
                                    <p style="color: black; font-weight: bold;">Bạn cần <a style="color: red;" href="login.jsp">đăng nhập</a> để thêm bình luận.</p>
                                </c:if>
                                <input type="submit" value="Submit Comment" />
                            </form>

                            <%-- Hiển thị danh sách bình luận --%>
                            <h2>Comments:</h2>
                            <div class="container">
                                <div class="row" id="commentsRow">
                                    <%-- Lặp qua danh sách bình luận và hiển thị --%>
                                    <c:forEach var="comment" items="${userComments}">
                                        <%-- Tính toán thời gian tương đối --%>
                                        <c:set var="currentTime" value="${System.currentTimeMillis()}" />
                                        <c:set var="timeDiff" value="${currentTime - comment.feedbackDate.time}" />
                                        <c:choose>
                                            <c:when test="${timeDiff < 60000}"> <!-- Thời gian dưới 1 phút -->
                                                <c:set var="relativeTime" value="A few seconds ago" />
                                            </c:when>
                                            <c:when test="${timeDiff < 3600000}"> <!-- Thời gian dưới 1 giờ -->
                                                <c:set var="minutes" value="${Math.round(timeDiff / 60000)}" />
                                                <c:set var="relativeTime" value="${minutes} minutes ago" />
                                            </c:when>
                                            <c:when test="${timeDiff < 86400000}"> <!-- Thời gian dưới 1 ngày -->
                                                <c:set var="hours" value="${Math.round(timeDiff / 3600000)}" />
                                                <c:set var="relativeTime" value="${hours} hours ago" />
                                            </c:when>
                                            <c:when test="${timeDiff < 2592000000}"> <!-- Thời gian dưới 1 tháng -->
                                                <c:set var="days" value="${Math.round(timeDiff / 86400000)}" />
                                                <c:set var="relativeTime" value="${days} days ago" />
                                            </c:when>
                                            <c:when test="${timeDiff < 31536000000}"> <!-- Thời gian dưới 1 năm -->
                                                <c:set var="months" value="${Math.round(timeDiff / 2592000000)}" />
                                                <c:set var="relativeTime" value="${months} months ago" />
                                            </c:when>
                                            <c:otherwise> <!-- Thời gian trên 1 năm -->
                                                <fmt:formatDate var="formattedDate" value="${comment.feedbackDate}" pattern="dd/MM/yyyy HH:mm:ss" />
                                                <c:set var="relativeTime" value="${formattedDate}" />
                                            </c:otherwise>
                                        </c:choose>

                                        <%-- Hiển thị thông tin bình luận --%>
                                        <div class="col-md-8">
                                            <div class="media g-mb-30 media-comment">
                                                <img class="d-flex g-width-50 g-height-50 rounded-circle g-mt-3 g-mr-15" src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Image Description">
                                                <div class="media-body u-shadow-v18 g-bg-secondary g-pa-30">
                                                    <div class="g-mb-15">
                                                        <h5 class="h5 g-color-gray-dark-v1 mb-0">${comment.userName}</h5>
                                                        <span class="g-color-gray-dark-v4 g-font-size-12">${relativeTime}</span>
                                                    </div>
                                                    <p>${comment.comment}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
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
                        <!-- ##### Footer Area Start ##### -->

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
                        <script src="js/rating.js"></script>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
