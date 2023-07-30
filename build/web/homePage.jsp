<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
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
        <title>Recipe Organize | Home</title>

        <!-- Favicon -->
        <link rel="icon" href="img/recipe/favicon.ico">

        <!-- Core Stylesheet -->
        <link rel="stylesheet" href="style.css">

    </head>

    <body>

        <c:if test="${requestScope.RESET_PASSWORD_SUCCESS}">
            <c:set var="reset_password_success" value="${requestScope.RESET_PASSWORD_SUCCESS}" scope="request" />
        </c:if>
        <script>
            var result = ${reset_password_success};
            if (result) {
                alert("Reset your password successfully.");
            }
        </script>

        <!-- Preloader -->
        <div id="preloader">
            <i class="circle-preloader"></i>
            <img src="img/recipe/salad.png" alt="">
        </div>

        <!-- Search Wrapper -->
        <div class="search-wrapper">
            <!-- Close Btn -->
            <div class="close-btn"><i class="fa fa-times" aria-hidden="true"></i></div>

            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <form action="SearchController" method="post">
                            <input type="search" name="txtSearch" placeholder="Type any keywords...">
                            <button type="submit" name="btAction" value="search"><i class="fa fa-search" aria-hidden="true"></i></button>
                        </form>
                    </div>
                </div>
            </div>
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
                                        <i class="fa fa-search" aria-hidden="true"></i>
                                    </div>

                                </div>
                                <!-- Nav End -->
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </header>
        <!-- ##### Header Area End ##### -->

        <!-- ##### Hero Area Start ##### -->
        <section class="hero-area">
            <div class="hero-slides owl-carousel">

                <!-- Single Hero Slide -->
                <div class="single-hero-slide bg-img" style="background-image: url(img/bg-img/bg10.jpg);">
                    <div class="container h-100">
                        <div class="row h-100 align-items-center">
                            <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                                <div class="hero-slides-content" data-animation="fadeInUp" data-delay="100ms">
                                    <h2 data-animation="fadeInUp" data-delay="300ms">Com tam</h2>
                                    <p data-animation="fadeInUp" data-delay="700ms">Com tam is considered a specialty dish of Saigon people. Each plate of hot, fragrant broken rice is always the first choice for quick but nutritious meals.</p>
                                    <a href="DetailController?recipeID=91" class="btn delicious-btn" data-animation="fadeInUp" data-delay="1000ms">See Receipe</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Single Hero Slide -->
                <div class="single-hero-slide bg-img" style="background-image: url(img/bg-img/bg11.jpg);">
                    <div class="container h-100">
                        <div class="row h-100 align-items-center">
                            <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                                <div class="hero-slides-content" data-animation="fadeInUp" data-delay="100ms">
                                    <h2 data-animation="fadeInUp" data-delay="300ms">Pho bo</h2>
                                    <p data-animation="fadeInUp" data-delay="700ms">Pho is a famous Vietnamese dish loved by many people, it will be even more delicious when cooked at home for the family to enjoy. The taste of beef is sweet and nutritious, the noodles are chewy and chewy, and the broth is rich, mixed with the aroma of herbs. Speaking of which, I'm already hungry, don't wait long, let's go to the kitchen to do it right away.</p>
                                    <a href="DetailController?recipeID=92" class="btn delicious-btn" data-animation="fadeInUp" data-delay="1000ms">See Receipe</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- Single Hero Slide -->
                <div class="single-hero-slide bg-img" style="background-image: url(img/bg-img/bg12.jpg);">
                    <div class="container h-100">
                        <div class="row h-100 align-items-center">
                            <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                                <div class="hero-slides-content" data-animation="fadeInUp" data-delay="100ms">
                                    <h2 data-animation="fadeInUp" data-delay="300ms">Peach tea</h2>
                                    <p data-animation="fadeInUp" data-delay="700ms">Peach tea is the favorite drink of most young people today, not only because of the refreshing taste of tea but also because of the diverse combination of flavors from fresh peaches to create a unique drink. Today, we will tell you how to make peach tea at home very easily. Go to the kitchen and do it right away!</p>
                                    <a href="DetailController?recipeID=93" class="btn delicious-btn" data-animation="fadeInUp" data-delay="1000ms">See Receipe</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- ##### Hero Area End ##### -->

        <!-- ##### Top Catagory Area Start ##### -->
        <section class="top-catagory-area section-padding-80-0">
            <div class="container">
                <div class="row">
                    <!-- Top Catagory Area -->
                    <div class="col-12 col-lg-6">
                        <div class="single-top-catagory">
                            <img src="img/bg-img/bg2.jpg" alt="">
                            <!-- Content -->
                            <div class="top-cta-content">
                                <h3>Dessert</h3>
                                <h6>Simple &amp; Delicios</h6>
                                <a href="CategoryController?categoryID=5" class="btn delicious-btn">See Full Receipe</a>
                            </div>
                        </div>
                    </div>
                    <!-- Top Catagory Area -->
                    <div class="col-12 col-lg-6">
                        <div class="single-top-catagory">
                            <img src="img/bg-img/bg3.jpg" alt="">
                            <!-- Content -->
                            <div class="top-cta-content">
                                <h3>Main dish</h3>
                                <h6>Simple &amp; Delicios</h6>
                                <a href="CategoryController?categoryID=1" class="btn delicious-btn">See Full Receipe</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- ##### Top Catagory Area End ##### -->
        <section>
            <style>
                .receipe-grid {
                    display: grid;
                    grid-template-columns: repeat(3, 1fr);
                    grid-gap: 20px;
                    margin: -10px;
                }

                .single-best-receipe-area {
                    text-align: center;
                    margin: 10px;
                    padding: 10px;
                    background-color: #ffffff;
                    border-radius: 5px;
                    box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
                }

                .single-best-receipe-area .image-wrapper {
                    width: 100%;
                    height: 0;
                    padding-bottom: 100%;
                    position: relative;
                    overflow: hidden;
                    border-radius: 5px;
                }

                .single-best-receipe-area .image-wrapper img {
                    position: absolute;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                    object-fit: cover;
                    transition: transform 0.3s ease-in-out;
                }

                .single-best-receipe-area .image-wrapper:hover img {
                    transform: scale(1.05);
                }

                .single-best-receipe-area .receipe-content {
                    padding: 10px;
                }

                .single-best-receipe-area .receipe-content a {
                    text-decoration: none;
                }

                .single-best-receipe-area .receipe-content h5 {
                    margin-top: 10px;
                    margin-bottom: 5px;
                    font-size: 18px;
                    font-weight: bold;
                    color: #222222;
                }

                .single-best-receipe-area .receipe-content .ratings {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                }

                .single-best-receipe-area .receipe-content .ratings i {
                    font-size: 14px;
                    color: #ee0000;
                    margin-right: 2px;
                }

                .single-best-receipe-area .receipe-content .ratings p {
                    font-size: 14px;
                    color: #666666;
                }        
            </style>
            <!-- ##### Best Receipe Area Start ##### -->
            <div class="best-receipe-area">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <div class="section-heading">
                                <h3>The Best Recipes</h3><br>
                                <div class="receipe-grid">
                                    <% RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
                                        List<RecipeOrganizeDTO> results = dao.getAllRecipe();
                                        Collections.shuffle(results);
                                        int count = 0;
                                        if (results != null) {
                                            for (RecipeOrganizeDTO recipe : results) {
                                                int rating = recipe.getAvgRating();
                                                if (rating >= 4) {
                                                    if (count >= 9) {
                                                        break;
                                                    }
                                    %>
                                    <div class="single-best-receipe-area">
                                        <div class="image-wrapper">
                                            <img src="<%= recipe.getImgUrl()%>" alt="">
                                        </div>
                                        <div class="receipe-content">
                                            <a href="DetailController?recipeID=<%= recipe.getRecipeID()%>" title="View Product" ><%= recipe.getRecipeName()%><a>
                                                </a>
                                                <div class="ratings">
                                                    <% for (int i = 0; i < rating; i++) { %>
                                                    <i class="fa fa-star" aria-hidden="true"></i>
                                                    <% } %>
                                                    <% int remainingStars = 5 - rating;
                                                    for (int i = 0; i < remainingStars; i++) { %>
                                                    <i class="fa fa-star-o" aria-hidden="true"></i>
                                                    <% }%>
                                                </div>
                                                <p><%= recipe.getAvgRating()%> comment</p>                                            
                                        </div>
                                    </div>
                                    <% count++;
                                            }
                                        }
                                    } else { %>
                                    <p>No recipes found.</p>
                                    <% } %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
        </section>



        <!-- ##### Best Receipe Area End ##### -->

        <!-- ##### CTA Area Start ##### -->
        <section class="cta-area bg-img bg-overlay" style="background-image: url(img/bg-img/bg4.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <!-- Cta Content -->
                        <div class="cta-content text-center">
                            <h2>Healthy Food Recipes</h2>
                            <p>Healthy foods are foods that are healthy, safe and healthy for the user's body. Using organic foods, natural foods, free of harmful impurities, minimal processing to keep the essence of food are the principles of healthy food. As a result, healthy food brings positive values to users' health.</p>
                            <a href="AllRecipeController" class="btn delicious-btn">Discover all the receipies</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- ##### CTA Area End ##### -->

        <!-- ##### Small Receipe Area Start ##### -->
        <section class="small-receipe-area section-padding-80-0">
            <div class="container">
                <div class="row">
                    <%
                        Collections.shuffle(results);
                        float counti = 0;
                        if (results != null) {
                            for (RecipeOrganizeDTO recipe : results) {
                                int rating = recipe.getAvgRating();
                                float ratingcalo = recipe.getCaloRecipe();
                                if (ratingcalo < 300) {
                                    if (counti >= 16) {
                                        break;
                                    }
                    %>
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="single-small-receipe-area d-flex">
                            <div class="receipe-thumb">
                                <img src="<%= recipe.getImgUrl()%>" alt="">
                            </div>
                            <div class="receipe-content">
                                <span>April 04, 2023</span>
                                <a href="DetailController?recipeID=<%= recipe.getRecipeID()%>" title="View Product" ><%= recipe.getRecipeName()%><a>
                                    </a>
                                    <div class="ratings">
                                        <%
                                            for (int i = 0; i < rating; i++) {
                                        %>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <% } %>
                                        <%
                                            int remainingStars = 5 - rating;
                                            for (int i = 0; i < remainingStars; i++) {
                                        %>
                                        <i class="fa fa-star-o" aria-hidden="true"></i>
                                        <% }%>
                                    </div>
                                    <p> <%= recipe.getAvgRating()%> comment</p>
                            </div>
                        </div>
                    </div>
                    <%
                                counti++;
                            }
                        }
                    } else {
                    %>
                    <p>No recipes found.</p>
                    <% }%>
                </div>
            </div>
        </section>
        <!-- ##### CTA Area End ##### -->


        <!-- ##### Small Receipe Area End ##### -->

        <!-- ##### Quote Subscribe Area End ##### -->

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
    </body>

</html>