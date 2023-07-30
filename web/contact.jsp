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
    <title>Recipe Organize | Contact</title>

    <!-- Favicon -->
    <link rel="icon" href="img/recipe/favicon.ico">

    <!-- Core Stylesheet -->
    <link rel="stylesheet" href="style.css">

</head>

<body>
    <script>
            var result = ${requestScope.SEND_SUCCESS};
            if (result) {
                alert("Send email successfully.");
            }
        </script>
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
    </header>
    <!-- ##### Header Area End ##### -->

    <!-- ##### Breadcumb Area Start ##### -->
    <div class="breadcumb-area bg-img bg-overlay" style="background-image: url(img/bg-img/breadcumb4.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="breadcumb-text text-center">
                        <h2>Contact</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Breadcumb Area End ##### -->

    <!-- ##### Contact Information Area Start ##### -->
    <div class="contact-information-area section-padding-80">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="logo mb-80">
                        <img src="img/recipe/logo.png" alt="">
                    </div>
                </div>
            </div>

            <div class="row">
                <!-- Contact Text -->
                <div class="col-12 col-lg-5">
                    <div class="contact-text">
                        <p>Culinary enthusiasts and health-conscious individuals alike often find it challenging to discover recipes that align with personal preferences and lifestyle. And the recipe organization system was born to solve this problem. This project aims to develop an efficient and user-friendly recipe organization system that helps families find recipes that suit their taste or culinary habits.</p>
                        <p>Please contact us if you have a problem to solve!</p>
                    </div>
                </div>

                <div class="col-12 col-lg-3">
                    <!-- Single Contact Information -->
                    <div class="single-contact-information mb-30">
                        <h6>Address:</h6>
                        <p>D1 Street Saigon Hi-tech Park <br>District 9, Ho Chi Minh City, Vietnam</p>
                    </div>
                    <!-- Single Contact Information -->
                    <div class="single-contact-information mb-30">
                        <h6>Phone:</h6>
                        <p>+84 12345 6789 <br>+84 98765 4321</p>
                    </div>
                    <!-- Single Contact Information -->
                    <div class="single-contact-information mb-30">
                        <h6>Email:</h6>
                        <p>doanphamdangkhoitd2@gmail.com</p>
                    </div>
                </div>

                <!-- Newsletter Area -->
                <div class="col-12 col-lg-4">
                    <div class="newsletter-form bg-img bg-overlay" style="background-image: url('img/bg-img/share.jpg'); height: 350px; width: 570px;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Contact Information Area End ##### -->

    <!-- ##### Contact Form Area Start ##### -->
    <div class="contact-area section-padding-0-80">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading">
                        <h3>Contact us</h3>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="contact-form-area">
                        <form action="SendEmailToContactController" method="post">
                            <div class="row">
                                <div class="col-12">
                                    <input type="email" class="form-control" name="email" placeholder="Your Email">
                                </div>
                                <div class="col-12">
                                    <input type="text" class="form-control" name="subject" placeholder="Subject">
                                </div>
                                <div class="col-12">
                                    <textarea name="message" class="form-control" name="message" cols="30" rows="10" placeholder="Message"></textarea>
                                </div>
                                <div class="col-12 text-center">
                                    <button class="btn delicious-btn mt-30" type="submit">Send</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Contact Form Area End ##### -->


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