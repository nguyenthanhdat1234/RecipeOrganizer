<%-- 
    Document   : plan
    Created on : Jun 16, 2023, 8:52:31 AM
    Author     : MSI BH
--%>
<%@page import="java.io.PrintWriter"%>
<%@page import="team3.recipe.RecipeOrganizeDAO"%>
<%@page import="java.util.List"%>
<%@page import="team3.recipe.RecipeOrganizeDTO"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="description" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Title -->
        <title>Recipe Organize | Plan for healthy</title>

        <!-- Favicon -->
        <link rel="icon" href="img/recipe/favicon.ico">

        <!-- Core Stylesheet -->
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="css/style_plan.css">
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
                                <h2>Meal Plan</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <br>
            </section>

            <section>

                <script>
                    function openPopup() {
                        document.getElementById("popup").style.display = "block";
                    }

                    function closePopup() {
                        document.getElementById("popup").style.display = "none";
                    }

                    function calculate() {
                        var your_hope = document.querySelector('input[name="goal"]:checked').value;
                        var your_gender = document.querySelector('input[name="gender"]:checked').value;
                        var your_height = document.getElementById('height').value;
                        var your_weight = document.getElementById('weight').value;
                        var your_age = document.getElementById('age').value;
                        var your_activity_level = parseFloat(document.getElementById('activity_level').value);
                        var BMR;
                        if (your_height >= 100 && your_height <= 210) {
                            if (your_weight >= 30 && your_weight <= 180 ) {
                        if (your_age >= 12 && your_age <= 110){
                        if (your_gender == "M") {
                            if (your_hope == "L") {
                                BMR = ((10 * your_weight) + (6.25 * your_height) - (5 * your_age) + 5) * (your_activity_level - 0.3);
                            } else if (your_hope == "M") {
                                BMR = ((10 * your_weight) + (6.25 * your_height) - (5 * your_age) + 5) * (your_activity_level - 0.15);
                            } else if (your_hope == "B") {
                                BMR = ((10 * your_weight) + (6.25 * your_height) - (5 * your_age) + 5) * your_activity_level;
                            }
                        } else {
                            if (your_hope == "L") {
                                BMR = ((10 * your_weight) + (6.25 * your_height) - (5 * your_age) - 161) * (your_activity_level - 0.3);
                            } else if (your_hope == "M") {
                                BMR = ((10 * your_weight) + (6.25 * your_height) - (5 * your_age) - 161) * (your_activity_level - 0.15);
                            } else if (your_hope == "B") {
                                BMR = ((10 * your_weight) + (6.25 * your_height) - (5 * your_age) - 161) * your_activity_level;
                            }
                        }
                    
                        document.getElementById("bmrInput").value = BMR;
                        // Close the popup
                        document.getElementById("popup").style.display = "none";
                    } else {
                        alert ("Age is 12 - 110");
                        }}else {
                            alert ("Weight is 30 - 180");
                        }
                            
                    } else {
                        alert ("Height is 100 - 210");
                    }
                }
                </script>
                <div class="popup-card">
                    <div class="form">
                        <div class="icon">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 34 34" height="34" width="34">
                            <path stroke-linejoin="round" stroke-width="2.5" stroke="#40ba37"
                                  d="M7.08385 9.91666L5.3572 11.0677C4.11945 11.8929 3.50056 12.3055 3.16517 12.9347C2.82977 13.564 2.83226 14.3035 2.83722 15.7825C2.84322 17.5631 2.85976 19.3774 2.90559 21.2133C3.01431 25.569 3.06868 27.7468 4.67008 29.3482C6.27148 30.9498 8.47873 31.0049 12.8932 31.1152C15.6396 31.1838 18.3616 31.1838 21.1078 31.1152C25.5224 31.0049 27.7296 30.9498 29.331 29.3482C30.9324 27.7468 30.9868 25.569 31.0954 21.2133C31.1413 19.3774 31.1578 17.5631 31.1639 15.7825C31.1688 14.3035 31.1712 13.564 30.8359 12.9347C30.5004 12.3055 29.8816 11.8929 28.6437 11.0677L26.9171 9.91666">
                            </path>
                            <path stroke-linejoin="round" stroke-width="2.5" stroke="#40ba37"
                                  d="M2.83331 14.1667L12.6268 20.0427C14.7574 21.3211 15.8227 21.9603 17 21.9603C18.1772 21.9603 19.2426 21.3211 21.3732 20.0427L31.1666 14.1667">
                            </path>
                            <path stroke-width="2.5" stroke="#40ba37"
                                  d="M7.08331 17V8.50001C7.08331 5.82872 7.08331 4.49307 7.91318 3.66321C8.74304 2.83334 10.0787 2.83334 12.75 2.83334H21.25C23.9212 2.83334 25.2569 2.83334 26.0868 3.66321C26.9166 4.49307 26.9166 5.82872 26.9166 8.50001V17">
                            </path>
                            <path stroke-linejoin="round" stroke-linecap="round" stroke-width="2.5" stroke="#40ba37"
                                  d="M14.1667 14.1667H19.8334M14.1667 8.5H19.8334"></path>
                            </svg>
                        </div>
                        <div class="note">
                            <h4 class="title">Put Your Diet On Autopilot</h4>
                            <button type="button" class="button" onclick="openPopup()">
                                <span class="button__text">Not sure?</span>
                                <span class="button__icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" viewBox="0 0 24 24"
                                                                stroke-width="2" stroke-linejoin="round" stroke-linecap="round" stroke="currentColor"
                                                                height="24" fill="none" class="svg">
                                    <line y2="19" y1="5" x2="12" x1="12"></line>
                                    <line y2="12" y1="12" x2="19" x1="5"></line>
                                    </svg></span>
                            </button>
                            <form action="DispatchController" method="post">
                                <span class="subtitle">I want to eat: </span>
                                <input type="number" min="1500" max="4000" step="any" pattern="[0-9]*" placeholder="####"
                                       name="numcalo" id="bmrInput" value="0" class="input_field">
                                <span class="subtitle"><b>Calories</b></span>
                                <button type="submit" value="Generate" name="btAction" class="submit">Generate</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div id="popup" class="modal">
                    <div class="  modal-dialog ">
                        <div class =" modal-content overflow-y-auto ">

                            <!-- Nội dung của cửa sổ popup -->
                            <div class="modal-header">   
                                <h3 class="modal-title">Nutrition calculator</h3>
                            </div>
                            <div class="modal-body  flex-column ">
                                <!--                                <p>
                                                                    This calculator uses a standard BMR equation (the Mifflin-St Jeor formula) to estimate your Calorie needs.
                                                                    We also make some rough macronutrient suggestions, but you're free to completely customize these values
                                                                    when you create a free account.
                                                                </p>
                                                                <p>
                                                                    <strong>Keep in mind that this is a general estimate.</strong> For best results, consult your healthcare provider.
                                                                </p>-->
                                <div class="px-6 w-100 ">
                                    <div class=" w-100 ">
                                        <div id="" class="btn-group btn-group-toggle d-flex flex-row w-100 align-items-center " name="goal" data-toggle="buttons">
                                            <div class="w-25">I want to: </div>
                                            <div class="w-100 d-flex justify-content-between align-items-center ">
                                                <input type="radio" name="goal" value="L" id="c59_goal-0">Lose weight
                                                <input type="radio" name="goal" value="M" id="c59_goal-1" checked="">Maintain
                                                <input type="radio" name="goal" value="B" id="c59_goal-2">Build muscle
                                            </div> 
                                        </div>
                                    </div>
                                </div>

                                <div class="w-100 mt-2">
                                    <div id="c59_goal" class="btn-group btn-group-toggle w-100 d-flex align-items-center " name="gender" data-toggle="buttons">
                                        <div class="w-25">I am: </div>
                                        <div class="w-100 d-flex">
                                            <div>
                                                <input type="radio" name="gender" value="M" id="c59_gender-0" value="Male"/>
                                                <label for="Male">Male</label>
                                            </div>
                                            <div class="ml-4" >
                                                <input type="radio" name="gender" value="F" id="c59_gender-1" checked="" value="Female"/>
                                                <label for="Female">Female</label>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="w-100 d-flex  ">
                                    <div class=" d-flex align-items-center">
                                        <label>Height:</label>
                                        <input type="number" min="100" max="210" step="any" pattern="[0-9]*" id="height" name="height" value="" class="form-control  inline_block ml-15">
                                        <label for="height-secondary" class="metric_inputs signup_input_label ml-15">cms</label>
                                    </div>
                                </div>


                                <div class="w-100 d-flex mt-2 ">
                                    <div class="d-flex align-items-center">
                                        <label>Weight:</label>
                                        <input type="number" min="30" max="180" step="any" pattern="[0-9]*" id="weight" name="weight" value="" class="form-control inline_block ml-15" type="number" >
                                        <label for="weight" class="signup_input_label metric_inputs ml-15">kgs</label>
                                    </div>
                                </div>

                                <div class="w-100 d-flex mt-2">
                                    <div class="d-flex align-items-center">
                                        <label>Age:</label>
                                        <input type="number" min="12" max="100" step="any" pattern="[0-9]*" name="age" id="age" value="" class="form-control inline_block ml-30 " type="number">
                                        <label for="age" class="signup_input_label ml-15">years</label>
                                    </div>
                                </div>

                                <br>

                                <div class="w-100 d-flex mt-2">
                                    <label>Activity level:</label>
                                    <select id="activity_level" class="form-control" name="activity_level">
                                        <option value="1.375">Lightly Active</option>
                                        <option value="1.55" selected>Moderately Active</option>
                                        <option value="1.725">Very Active</option>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">

                                <button class="py-2 px-4 rounded bg-success text-center text-white " onclick="calculate()">Calculate</button>
                                <button class="py-2 px-4 rounded  text-center  " onclick="closePopup()">Close</button>
                            </div>

                        </div>
                    </div>
                </div>
            </section>
            <section>

                <!-- Start Today's -->
                <c:if test="${not empty sessionScope.USER || not empty sessionScope.ADMIN}">
                    <c:if test="${not empty sessionScope.MEAL_PLAN}">
                        <div class='form-main'>
                            <div class="show-cart-plan">
                                <h2>
                                    <%
                                        LocalDate currentDate = LocalDate.now();
                                        String planDate = currentDate.toString();
                                        RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
                                        if (session.getAttribute("USER") != null) {
                                            RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("USER");
                                            int userID = user.getUserID();
                                            List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(userID);
                                            if (!mealplan.get(0).getPlanDate().equals(planDate)) {
                                                out.println(mealplan.get(0).getPlanDate());
                                            } else {
                                                out.println("<div style='text-align: center; font-family: Times New Roman '>Today's Meal Plan</div>");
                                            }
                                        } else {
                                            RecipeOrganizeDTO admin = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                                            int adminID = admin.getUserID();
                                            List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(adminID);
                                            if (!mealplan.get(0).getPlanDate().equals(planDate)) {
                                                out.println(mealplan.get(0).getPlanDate());
                                            } else {
                                                out.println("<div style='text-align: center; font-family: Times New Roman'>Today's Meal Plan</div>");
                                            }
                                        }
                                    %>
                                </h2>
                                <p style="display: inline-block; font-size: 20px; padding-left: 37% ">|</p>
                                <p style="display: inline-block;text-align: center; color: black; font-family: Times New Roman; font-size: 20px;">Your Calories Estimate: 
                                    <%
                                        float calo;
                                        if (session.getAttribute("USER") != null) {
                                            RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("USER");
                                            int userID = user.getUserID();
                                            calo = dao.getIndividualCalory(userID);
                                            out.println(calo);
                                        } else {
                                            RecipeOrganizeDTO admin = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                                            int adminID = admin.getUserID();
                                            calo = dao.getIndividualCalory(adminID);
                                            out.println(calo);
                                        }
                                    %>
                                </p>
                                <%
                                    float totalCalories = 0;
                                    if (session.getAttribute("USER") != null) {
                                        RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("USER");
                                        int userID = user.getUserID();
                                        List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(userID);
                                        for (RecipeOrganizeDTO mealPlan : mealplan) {
                                            totalCalories = totalCalories + mealPlan.getCaloRecipe();
                                        }
                                        request.setAttribute("TotalCalories", totalCalories);
                                    } else {
                                        RecipeOrganizeDTO admin = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                                        int adminID = admin.getUserID();
                                        List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(adminID);
                                        for (RecipeOrganizeDTO mealPlan : mealplan) {
                                            totalCalories = totalCalories + mealPlan.getCaloRecipe();
                                        }
                                        request.setAttribute("TotalCalories", totalCalories);
                                    }
                                %>
                                <p style="display: inline-block; font-size: 20px;">|</p>
                                <p style="display: inline-block; color: black; font-family: Times New Roman; font-size: 20px">Total Food's Calories: ${requestScope.TotalCalories}</p>
                            </div>
                        </div>
                        <form action="RefreshRecipeController" method="POST">
                            <div class="meal-frame">
                                <div class="meal-title"><h2 style="color: green; font-family: Times New Roman; text-align: center">Breakfast</h2></div>
                                <div style="background-color: grey; height: 3px"></div>
                                <div style="padding-bottom: 15px; padding-top: -5px"></div>
                                <div style="display: flex; justify-content: center;">
                                    <div class="container">
                                        <div class="row meal-items">
                                            <c:forEach var="meal_plan" items="${sessionScope.MEAL_PLAN}" varStatus="loop">
                                                <div class="col-12 col-sm-6">
                                                    <c:if test="${loop.index < 2}">
                                                        <div class="row meal-item">
                                                            <div class="col-12 col-sm-6">
                                                                <img src=${meal_plan.imgUrl}>
                                                            </div>
                                                            <div class="col-12 col-sm-6">
                                                                <a href="DetailController?recipeID=${meal_plan.recipeID}" title="View Product">
                                                                    <h4 style="font-family: Times New Roman">${meal_plan.recipeName}</h4>
                                                                </a>
                                                                <p><b>${meal_plan.caloRecipe} calories </b></p>

                                                                <button type="submit" name="refreshRecipe" value="${meal_plan.recipeID}" class="refresh-button">
                                                                    <img src="img/recipe/refresh.png" alt="Refresh">
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="meal-frame">
                                <div class="meal-title"><h2 style="color: green; font-family: Times New Roman; text-align: center">Lunch</h2></div>
                                <div style="background-color: grey; height: 3px"></div>
                                <div style="padding-bottom: 15px; padding-top: -5px"></div>
                                <div style="display: flex; justify-content: center;">
                                    <div class="container">
                                        <div class="inline-block-center">
                                            <div class="row meal-items">
                                                <c:forEach var="meal_plan" items="${sessionScope.MEAL_PLAN}" varStatus="loop">
                                                    <div class="col-12 col-md-6">
                                                        <c:if test="${loop.index > 1 && loop.index < 4}">
                                                            <div class="row meal-item">
                                                                <div class="col-12 col-md-6">
                                                                    <img src=${meal_plan.imgUrl}>
                                                                </div>
                                                                <div class="col-12 col-md-6">
                                                                    <a href="DetailController?recipeID=${meal_plan.recipeID}" title="View Product">
                                                                        <h4 style="font-family: Times New Roman">${meal_plan.recipeName}</h4>
                                                                    </a>
                                                                    <p><b>${meal_plan.caloRecipe} calories </b></p>
                                                                    <button type="submit" name="refreshRecipe" value="${meal_plan.recipeID}" class="refresh-button">
                                                                        <img src="img/recipe/refresh.png" alt="Refresh">
                                                                    </button>
                                                                </div>    
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="meal-frame">
                                <div class="meal-title"><h2 style="color: green; font-family: Times New Roman; text-align: center">Dinner</h2></div>
                                <div style="background-color: grey; height: 3px"></div>
                                <div style="padding-bottom: 15px; padding-top: -5px"></div>
                                <div style="display: flex; justify-content: center;">
                                    <div class="container">
                                        <div class="row meal-items">
                                            <c:forEach var="meal_plan" items="${sessionScope.MEAL_PLAN}" varStatus="loop">
                                                <div class="col-12 col-sm-6">
                                                    <c:if test="${loop.index > 3}">
                                                        <div class="row meal-item">
                                                            <div class="col-12 col-sm-6">
                                                                <img src=${meal_plan.imgUrl}>
                                                            </div>
                                                            <div class="col-12 col-sm-6">
                                                                <a href="DetailController?recipeID=${meal_plan.recipeID}" title="View Product">
                                                                    <h4 style="font-family: Times New Roman">${meal_plan.recipeName}</h4>
                                                                </a>
                                                                <p><b>${meal_plan.caloRecipe} calories </b></p>
                                                                <button type="submit" name="refreshRecipe" value="${meal_plan.recipeID}" class="refresh-button">
                                                                    <img src="img/recipe/refresh.png" alt="Refresh">
                                                                </button>
                                                            </div>                                               
                                                        </div>
                                                    </c:if>
                                                </div>
                                            </c:forEach>
                                        </div> 
                                    </div> 
                                </div> 
                            </c:if>

                        </c:if>
                        <c:if test="${not empty sessionScope.USER || not empty sessionScope.ADMIN || (empty sessionScope.USER and empty sessionScope.ADMIN)}">
                            <c:if test="${empty sessionScope.MEAL_PLAN}">
                                <h2>You haven't set a meal plan yet!</h2>
                            </c:if>
                        </c:if>
                    </div>
                </form>
            </section>
            <!-- End Today's -->
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

            <!-- All Plugins js -->
            <script src="js/plugins/plugins.js"></script>
            <!-- Active js -->
            <script src="js/active.js"></script>
    </body>
</html>
