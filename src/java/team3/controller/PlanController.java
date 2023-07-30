/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import team3.recipe.RecipeOrganizeDAO;
import team3.recipe.RecipeOrganizeDTO;

/**
 *
 * @author MSI BH
 */
@WebServlet(name = "PlanController", urlPatterns = {"/PlanController"})
public class PlanController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        float numcalo = Float.parseFloat(request.getParameter("numcalo"));
        HttpSession session = request.getSession(false);
        LocalDate currentDate = LocalDate.now();
        String planDate = currentDate.toString();
        try {
            RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
            Random random = new Random();
            //The breakfast
            List<RecipeOrganizeDTO> list_salad = dao.getRecipeByCategory("3");
            int randomIndex3 = random.nextInt(list_salad.size());
            RecipeOrganizeDTO randomBreakfast3 = list_salad.get(randomIndex3);
            List<RecipeOrganizeDTO> list_oats = dao.getRecipeByCategory("5");
            int randomIndex5 = random.nextInt(list_oats.size());
            RecipeOrganizeDTO randomBreakfast5 = list_oats.get(randomIndex5);

            //The lunch
            List<RecipeOrganizeDTO> list_lunch = dao.getRecipeByCategory("1");
            int randomIndexLunch1 = random.nextInt(list_lunch.size());
            RecipeOrganizeDTO randomLunch1 = list_lunch.get(randomIndexLunch1);
            int randomIndexLunch2;
            randomIndexLunch2 = random.nextInt(list_lunch.size());
            RecipeOrganizeDTO randomLunch2 = null;
            if (randomIndexLunch1 == randomIndexLunch2) {
                for (int i = 0; i < list_lunch.size(); i++) {
                    randomIndexLunch2 = random.nextInt(list_lunch.size());
                    if (randomIndexLunch1 != randomIndexLunch2) {
                        randomLunch2 = list_lunch.get(randomIndexLunch2);
                        break;
                    }
                }
            } else {
                randomLunch2 = list_lunch.get(randomIndexLunch2);
            }
            //The dinner
            List<RecipeOrganizeDTO> list_dinner = null;
            list_dinner = dao.getRecipeByCategory("1");
            int randomIndexDinner1;
            randomIndexDinner1 = random.nextInt(list_dinner.size());
            RecipeOrganizeDTO randomDinner1 = null;
            RecipeOrganizeDTO randomDinner2 = null;
            if (randomIndexLunch1 == randomIndexDinner1 || randomIndexLunch2 == randomIndexDinner1) {
                for (int i = 0; i < list_dinner.size(); i++) {
                    randomIndexDinner1 = random.nextInt(list_dinner.size());
                    if (randomIndexDinner1 != randomIndexLunch1 && randomIndexDinner1 != randomIndexLunch2) {
                        randomDinner1 = list_dinner.get(randomIndexDinner1);
                        break;
                    }
                }
            } else {
                randomDinner1 = list_dinner.get(randomIndexDinner1);
            }
            //The 6th recipe dinner
            float fiveRecipe_calories = 0;
            fiveRecipe_calories = randomBreakfast3.getCaloRecipe() + randomBreakfast5.getCaloRecipe()
                    + randomLunch1.getCaloRecipe() + randomLunch2.getCaloRecipe() + randomDinner1.getCaloRecipe();
            float sixRecipe_calories;
            // if fiveRecipe_calories of 5 first recipes is equal or more than numcalo then The 6th recipe dinner won't be recommended
            if (fiveRecipe_calories >= numcalo) {
                if (session.getAttribute("USER") != null) {
                    RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("USER");
                    int userID = user.getUserID();
                    dao.addIndividualCalory(userID, numcalo);
                    List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(userID);
                    Collections.sort(list_dinner, new Comparator<RecipeOrganizeDTO>() {
                        @Override
                        public int compare(RecipeOrganizeDTO recipe1, RecipeOrganizeDTO recipe2) {
                            // So sánh dựa trên thuộc tính caloRecipe
                            return Float.compare(recipe1.getCaloRecipe(), recipe2.getCaloRecipe());
                        }
                    });
                    if (mealplan.size() == 0) {
                        for (int i = list_dinner.size() - 1; i >= 0; i--) {
                            randomDinner1 = list_dinner.get(i);
                            if (randomDinner1 != randomLunch1 && randomDinner1 != randomLunch2) {
                                fiveRecipe_calories = randomBreakfast3.getCaloRecipe() + randomBreakfast5.getCaloRecipe()
                                        + randomLunch1.getCaloRecipe() + randomLunch2.getCaloRecipe() + randomDinner1.getCaloRecipe();
                                if (fiveRecipe_calories <= numcalo) {
                                    dao.addMealPlan(planDate, userID, randomBreakfast3.getRecipeID());
                                    dao.addMealPlan(planDate, userID, randomBreakfast5.getRecipeID());
                                    dao.addMealPlan(planDate, userID, randomLunch1.getRecipeID());
                                    dao.addMealPlan(planDate, userID, randomLunch2.getRecipeID());
                                    dao.addMealPlan(planDate, userID, randomDinner1.getRecipeID());
                                    List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                    session.setAttribute("MEAL_PLAN", mealPlan);
                                    break;
                                } else {
                                    RecipeOrganizeDTO minCaloRandomDinner1 = list_dinner.get(0);
                                    if (randomDinner1 == minCaloRandomDinner1) {
                                        dao.addMealPlan(planDate, userID, randomBreakfast3.getRecipeID());
                                        dao.addMealPlan(planDate, userID, randomBreakfast5.getRecipeID());
                                        dao.addMealPlan(planDate, userID, randomLunch1.getRecipeID());
                                        dao.addMealPlan(planDate, userID, randomLunch2.getRecipeID());
                                        dao.addMealPlan(planDate, userID, minCaloRandomDinner1.getRecipeID());
                                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                        session.setAttribute("MEAL_PLAN", mealPlan);
                                    }
                                }
                            }

                        }
                    }
                    else if (mealplan.size() == 5) {
                        dao.updateMealPlan(mealplan.get(0).getPlanID(), planDate, randomBreakfast3.getRecipeID());
                        dao.updateMealPlan(mealplan.get(1).getPlanID(), planDate, randomBreakfast5.getRecipeID());
                        dao.updateMealPlan(mealplan.get(2).getPlanID(), planDate, randomLunch1.getRecipeID());
                        dao.updateMealPlan(mealplan.get(3).getPlanID(), planDate, randomLunch2.getRecipeID());
                        for (int i = list_dinner.size() - 1; i >= 0; i--) {
                            randomDinner1 = list_dinner.get(i);
                            fiveRecipe_calories = randomBreakfast3.getCaloRecipe() + randomBreakfast5.getCaloRecipe()
                                    + randomLunch1.getCaloRecipe() + randomLunch2.getCaloRecipe() + list_dinner.get(i).getCaloRecipe();
                            if (fiveRecipe_calories <= numcalo) {
                                dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, list_dinner.get(i).getRecipeID());
                                List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                session.setAttribute("MEAL_PLAN", mealPlan);
                                break;
                            } else {
                                RecipeOrganizeDTO minCaloRandomDinner1 = list_dinner.get(0);
                                if (randomDinner1 == minCaloRandomDinner1) {
                                    dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, minCaloRandomDinner1.getRecipeID());
                                    List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                    session.setAttribute("MEAL_PLAN", mealPlan);
                                }
                            }
                        }
                    } else {
                        dao.updateMealPlan(mealplan.get(0).getPlanID(), planDate, randomBreakfast3.getRecipeID());
                        dao.updateMealPlan(mealplan.get(1).getPlanID(), planDate, randomBreakfast5.getRecipeID());
                        dao.updateMealPlan(mealplan.get(2).getPlanID(), planDate, randomLunch1.getRecipeID());
                        dao.updateMealPlan(mealplan.get(3).getPlanID(), planDate, randomLunch2.getRecipeID());
                        for (int i = list_dinner.size() - 1; i >= 0; i--) {
                            randomDinner1 = list_dinner.get(i);
                            fiveRecipe_calories = randomBreakfast3.getCaloRecipe() + randomBreakfast5.getCaloRecipe()
                                    + randomLunch1.getCaloRecipe() + randomLunch2.getCaloRecipe() + list_dinner.get(i).getCaloRecipe();
                            if (fiveRecipe_calories <= numcalo) {
                                dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, list_dinner.get(i).getRecipeID());
                                List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                session.setAttribute("MEAL_PLAN", mealPlan);
                                break;
                            } else {
                                RecipeOrganizeDTO minCaloRandomDinner1 = list_dinner.get(0);
                                if (randomDinner1 == minCaloRandomDinner1) {
                                    dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, minCaloRandomDinner1.getRecipeID());
                                    List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                    session.setAttribute("MEAL_PLAN", mealPlan);
                                }
                            }
                        }
                        dao.deleteMealPlan(mealplan.get(5).getPlanID());
                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                        session.setAttribute("MEAL_PLAN", mealPlan);
                    }
                } else {
                    RecipeOrganizeDTO admin = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                    int adminID = admin.getUserID();
                    dao.addIndividualCalory(adminID, numcalo);
                    List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(adminID);
                    Collections.sort(list_dinner, new Comparator<RecipeOrganizeDTO>() {
                        @Override
                        public int compare(RecipeOrganizeDTO recipe1, RecipeOrganizeDTO recipe2) {
                            // So sánh dựa trên thuộc tính caloRecipe
                            return Float.compare(recipe1.getCaloRecipe(), recipe2.getCaloRecipe());
                        }
                    });
                    if (mealplan.size() == 0) {
                        for (int i = list_dinner.size() - 1; i >= 0; i--) {
                            randomDinner1 = list_dinner.get(i);
                            if (randomDinner1 != randomLunch1 && randomDinner1 != randomLunch2) {
                                fiveRecipe_calories = randomBreakfast3.getCaloRecipe() + randomBreakfast5.getCaloRecipe()
                                        + randomLunch1.getCaloRecipe() + randomLunch2.getCaloRecipe() + randomDinner1.getCaloRecipe();
                                if (fiveRecipe_calories <= numcalo) {
                                    dao.addMealPlan(planDate, adminID, randomBreakfast3.getRecipeID());
                                    dao.addMealPlan(planDate, adminID, randomBreakfast5.getRecipeID());
                                    dao.addMealPlan(planDate, adminID, randomLunch1.getRecipeID());
                                    dao.addMealPlan(planDate, adminID, randomLunch2.getRecipeID());
                                    dao.addMealPlan(planDate, adminID, randomDinner1.getRecipeID());
                                    List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                    session.setAttribute("MEAL_PLAN", mealPlan);
                                    break;
                                } else {
                                    RecipeOrganizeDTO minCaloRandomDinner1 = list_dinner.get(0);
                                    if (randomDinner1 == minCaloRandomDinner1) {
                                        dao.addMealPlan(planDate, adminID, randomBreakfast3.getRecipeID());
                                        dao.addMealPlan(planDate, adminID, randomBreakfast5.getRecipeID());
                                        dao.addMealPlan(planDate, adminID, randomLunch1.getRecipeID());
                                        dao.addMealPlan(planDate, adminID, randomLunch2.getRecipeID());
                                        dao.addMealPlan(planDate, adminID, minCaloRandomDinner1.getRecipeID());
                                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                        session.setAttribute("MEAL_PLAN", mealPlan);
                                    }
                                }
                            }
                        }
                    }
                    else if (mealplan.size() == 5) {
                        dao.updateMealPlan(mealplan.get(0).getPlanID(), planDate, randomBreakfast3.getRecipeID());
                        dao.updateMealPlan(mealplan.get(1).getPlanID(), planDate, randomBreakfast5.getRecipeID());
                        dao.updateMealPlan(mealplan.get(2).getPlanID(), planDate, randomLunch1.getRecipeID());
                        dao.updateMealPlan(mealplan.get(3).getPlanID(), planDate, randomLunch2.getRecipeID());
                        for (int i = list_dinner.size() - 1; i >= 0; i--) {
                            randomDinner1 = list_dinner.get(i);
                            fiveRecipe_calories = randomBreakfast3.getCaloRecipe() + randomBreakfast5.getCaloRecipe()
                                    + randomLunch1.getCaloRecipe() + randomLunch2.getCaloRecipe() + list_dinner.get(i).getCaloRecipe();
                            if (fiveRecipe_calories <= numcalo) {
                                dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, list_dinner.get(i).getRecipeID());
                                List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                session.setAttribute("MEAL_PLAN", mealPlan);
                                break;
                            } else {
                                RecipeOrganizeDTO minCaloRandomDinner1 = list_dinner.get(0);
                                if (randomDinner1 == minCaloRandomDinner1) {
                                    dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, minCaloRandomDinner1.getRecipeID());
                                    List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                    session.setAttribute("MEAL_PLAN", mealPlan);
                                }
                            }
                        }
                    } else {
                        dao.updateMealPlan(mealplan.get(0).getPlanID(), planDate, randomBreakfast3.getRecipeID());
                        dao.updateMealPlan(mealplan.get(1).getPlanID(), planDate, randomBreakfast5.getRecipeID());
                        dao.updateMealPlan(mealplan.get(2).getPlanID(), planDate, randomLunch1.getRecipeID());
                        dao.updateMealPlan(mealplan.get(3).getPlanID(), planDate, randomLunch2.getRecipeID());
                        for (int i = list_dinner.size() - 1; i >= 0; i--) {
                            randomDinner1 = list_dinner.get(i);
                            fiveRecipe_calories = randomBreakfast3.getCaloRecipe() + randomBreakfast5.getCaloRecipe()
                                    + randomLunch1.getCaloRecipe() + randomLunch2.getCaloRecipe() + list_dinner.get(i).getCaloRecipe();
                            if (fiveRecipe_calories <= numcalo) {
                                dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, list_dinner.get(i).getRecipeID());
                                List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                session.setAttribute("MEAL_PLAN", mealPlan);
                                break;
                            } else {
                                RecipeOrganizeDTO minCaloRandomDinner1 = list_dinner.get(0);
                                if (randomDinner1 == minCaloRandomDinner1) {
                                    dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, minCaloRandomDinner1.getRecipeID());
                                    List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                    session.setAttribute("MEAL_PLAN", mealPlan);
                                }
                            }
                        }
                        dao.deleteMealPlan(mealplan.get(5).getPlanID());
                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                        session.setAttribute("MEAL_PLAN", mealPlan);
                    }
                }
            } else {
                // if fiveRecipe_calories of 5 first recipes is less than numcalo then The 6th recipe dinner will be recommended
                Collections.sort(list_dinner, new Comparator<RecipeOrganizeDTO>() {
                    @Override
                    public int compare(RecipeOrganizeDTO recipe1, RecipeOrganizeDTO recipe2) {
                        // So sánh dựa trên thuộc tính caloRecipe
                        return Float.compare(recipe1.getCaloRecipe(), recipe2.getCaloRecipe());
                    }
                });
                for (int i = list_dinner.size() - 1; i >= 0; i--) {
                    randomDinner2 = list_dinner.get(i);
                    if (randomDinner2 == randomLunch1 || randomDinner2 == randomLunch2 || randomDinner2 == randomDinner1) {
                        continue;
                    } else {
                        sixRecipe_calories = randomBreakfast3.getCaloRecipe() + randomBreakfast5.getCaloRecipe()
                                + randomLunch1.getCaloRecipe() + randomLunch2.getCaloRecipe() + randomDinner1.getCaloRecipe() + randomDinner2.getCaloRecipe();
                        if (sixRecipe_calories <= numcalo) {
                            //create session
                            if (session.getAttribute("USER") != null) {
                                RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("USER");
                                int userID = user.getUserID();
                                dao.addIndividualCalory(userID, numcalo);
                                List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(userID);
                                if (mealplan.size() == 0) {
                                    dao.addMealPlan(planDate, userID, randomBreakfast3.getRecipeID());
                                    dao.addMealPlan(planDate, userID, randomBreakfast5.getRecipeID());
                                    dao.addMealPlan(planDate, userID, randomLunch1.getRecipeID());
                                    dao.addMealPlan(planDate, userID, randomLunch2.getRecipeID());
                                    dao.addMealPlan(planDate, userID, randomDinner1.getRecipeID());
                                    dao.addMealPlan(planDate, userID, randomDinner2.getRecipeID());
                                    List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                    session.setAttribute("MEAL_PLAN", mealPlan);
                                }
                                else if (mealplan.size() == 6) {
                                    dao.updateMealPlan(mealplan.get(0).getPlanID(), planDate, randomBreakfast3.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(1).getPlanID(), planDate, randomBreakfast5.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(2).getPlanID(), planDate, randomLunch1.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(3).getPlanID(), planDate, randomLunch2.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, randomDinner1.getRecipeID());
                                    sixRecipe_calories = randomBreakfast3.getCaloRecipe() + randomBreakfast5.getCaloRecipe()
                                            + randomLunch1.getCaloRecipe() + randomLunch2.getCaloRecipe() + randomDinner1.getCaloRecipe() + list_dinner.get(i).getCaloRecipe();
                                    if (fiveRecipe_calories <= numcalo) {
                                        dao.updateMealPlan(mealplan.get(5).getPlanID(), planDate, list_dinner.get(i).getRecipeID());
                                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                        session.setAttribute("MEAL_PLAN", mealPlan);
                                        break;
                                    } else {
                                        RecipeOrganizeDTO minCaloRandomDinner2 = list_dinner.get(0);
                                        if (randomDinner2 == minCaloRandomDinner2) {
                                            dao.updateMealPlan(mealplan.get(5).getPlanID(), planDate, minCaloRandomDinner2.getRecipeID());
                                            List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                            session.setAttribute("MEAL_PLAN", mealPlan);
                                        }
                                    }
                                } else {
                                    dao.updateMealPlan(mealplan.get(0).getPlanID(), planDate, randomBreakfast3.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(1).getPlanID(), planDate, randomBreakfast5.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(2).getPlanID(), planDate, randomLunch1.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(3).getPlanID(), planDate, randomLunch2.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, randomDinner1.getRecipeID());
                                    sixRecipe_calories = randomBreakfast3.getCaloRecipe() + randomBreakfast5.getCaloRecipe()
                                            + randomLunch1.getCaloRecipe() + randomLunch2.getCaloRecipe() + randomDinner1.getCaloRecipe() + list_dinner.get(i).getCaloRecipe();
                                    if (fiveRecipe_calories <= numcalo) {
                                        dao.addMealPlan(planDate, userID, list_dinner.get(i).getRecipeID());
                                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                        session.setAttribute("MEAL_PLAN", mealPlan);
                                        break;
                                    } else {
                                        RecipeOrganizeDTO minCaloRandomDinner2 = list_dinner.get(0);
                                        if (randomDinner2 == minCaloRandomDinner2) {
                                            dao.addMealPlan(planDate, userID, minCaloRandomDinner2.getRecipeID());
                                            List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                            session.setAttribute("MEAL_PLAN", mealPlan);
                                        }
                                    }
                                }
                            } else {
                                RecipeOrganizeDTO admin = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                                int adminID = admin.getUserID();
                                dao.addIndividualCalory(adminID, numcalo);
                                List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(adminID);
                                if (mealplan.size() == 0) {
                                    dao.addMealPlan(planDate, adminID, randomBreakfast3.getRecipeID());
                                    dao.addMealPlan(planDate, adminID, randomBreakfast5.getRecipeID());
                                    dao.addMealPlan(planDate, adminID, randomLunch1.getRecipeID());
                                    dao.addMealPlan(planDate, adminID, randomLunch2.getRecipeID());
                                    dao.addMealPlan(planDate, adminID, randomDinner1.getRecipeID());
                                    dao.addMealPlan(planDate, adminID, randomDinner2.getRecipeID());
                                    List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                    session.setAttribute("MEAL_PLAN", mealPlan);
                                }
                                else if (mealplan.size() == 6) {
                                    dao.updateMealPlan(mealplan.get(0).getPlanID(), planDate, randomBreakfast3.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(1).getPlanID(), planDate, randomBreakfast5.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(2).getPlanID(), planDate, randomLunch1.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(3).getPlanID(), planDate, randomLunch2.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, randomDinner1.getRecipeID());
                                    sixRecipe_calories = randomBreakfast3.getCaloRecipe() + randomBreakfast5.getCaloRecipe()
                                            + randomLunch1.getCaloRecipe() + randomLunch2.getCaloRecipe() + randomDinner1.getCaloRecipe() + list_dinner.get(i).getCaloRecipe();
                                    if (fiveRecipe_calories <= numcalo) {
                                        dao.updateMealPlan(mealplan.get(5).getPlanID(), planDate, list_dinner.get(i).getRecipeID());
                                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                        session.setAttribute("MEAL_PLAN", mealPlan);
                                        break;
                                    } else {
                                        RecipeOrganizeDTO minCaloRandomDinner2 = list_dinner.get(0);
                                        if (randomDinner2 == minCaloRandomDinner2) {
                                            dao.updateMealPlan(mealplan.get(5).getPlanID(), planDate, minCaloRandomDinner2.getRecipeID());
                                            List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                            session.setAttribute("MEAL_PLAN", mealPlan);
                                        }
                                    }
                                } else {
                                    dao.updateMealPlan(mealplan.get(0).getPlanID(), planDate, randomBreakfast3.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(1).getPlanID(), planDate, randomBreakfast5.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(2).getPlanID(), planDate, randomLunch1.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(3).getPlanID(), planDate, randomLunch2.getRecipeID());
                                    dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, randomDinner1.getRecipeID());
                                    sixRecipe_calories = randomBreakfast3.getCaloRecipe() + randomBreakfast5.getCaloRecipe()
                                            + randomLunch1.getCaloRecipe() + randomLunch2.getCaloRecipe() + randomDinner1.getCaloRecipe() + list_dinner.get(i).getCaloRecipe();
                                    if (fiveRecipe_calories <= numcalo) {
                                        dao.addMealPlan(planDate, adminID, list_dinner.get(i).getRecipeID());
                                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                        session.setAttribute("MEAL_PLAN", mealPlan);
                                        break;
                                    } else {
                                        RecipeOrganizeDTO minCaloRandomDinner2 = list_dinner.get(0);
                                        if (randomDinner2 == minCaloRandomDinner2) {
                                            dao.addMealPlan(planDate, adminID, minCaloRandomDinner2.getRecipeID());
                                            List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                            session.setAttribute("MEAL_PLAN", mealPlan);
                                        }
                                    }
                                }
                            }
                            break;
                        } else {
                            RecipeOrganizeDTO minCaloRandomDinner2 = list_dinner.get(0);
                            if (randomDinner2 == minCaloRandomDinner2) {
                                if (session.getAttribute("USER") != null) {
                                    RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("USER");
                                    int userID = user.getUserID();
                                    dao.addIndividualCalory(userID, numcalo);
                                    List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(userID);
                                    if (mealplan.size() == 0) {
                                        dao.addMealPlan(planDate, userID, randomBreakfast3.getRecipeID());
                                        dao.addMealPlan(planDate, userID, randomBreakfast5.getRecipeID());
                                        dao.addMealPlan(planDate, userID, randomLunch1.getRecipeID());
                                        dao.addMealPlan(planDate, userID, randomLunch2.getRecipeID());
                                        dao.addMealPlan(planDate, userID, randomDinner1.getRecipeID());
                                        dao.addMealPlan(planDate, userID, minCaloRandomDinner2.getRecipeID());
                                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                        session.setAttribute("MEAL_PLAN", mealPlan);
                                    }
                                    else if (mealplan.size() == 6) {
                                        dao.updateMealPlan(mealplan.get(0).getPlanID(), planDate, randomBreakfast3.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(1).getPlanID(), planDate, randomBreakfast5.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(2).getPlanID(), planDate, randomLunch1.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(3).getPlanID(), planDate, randomLunch2.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, randomDinner1.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(5).getPlanID(), planDate, minCaloRandomDinner2.getRecipeID());
                                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                        session.setAttribute("MEAL_PLAN", mealPlan);
                                    } else {
                                        dao.updateMealPlan(mealplan.get(0).getPlanID(), planDate, randomBreakfast3.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(1).getPlanID(), planDate, randomBreakfast5.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(2).getPlanID(), planDate, randomLunch1.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(3).getPlanID(), planDate, randomLunch2.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, randomDinner1.getRecipeID());
                                        dao.addMealPlan(planDate, userID, minCaloRandomDinner2.getRecipeID());
                                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                                        session.setAttribute("MEAL_PLAN", mealPlan);
                                    }
                                } else {
                                    RecipeOrganizeDTO admin = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                                    int adminID = admin.getUserID();
                                    dao.addIndividualCalory(adminID, numcalo);
                                    List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(adminID);
                                    if (mealplan.size() == 0) {
                                        dao.addMealPlan(planDate, adminID, randomBreakfast3.getRecipeID());
                                        dao.addMealPlan(planDate, adminID, randomBreakfast5.getRecipeID());
                                        dao.addMealPlan(planDate, adminID, randomLunch1.getRecipeID());
                                        dao.addMealPlan(planDate, adminID, randomLunch2.getRecipeID());
                                        dao.addMealPlan(planDate, adminID, randomDinner1.getRecipeID());
                                        dao.addMealPlan(planDate, adminID, minCaloRandomDinner2.getRecipeID());
                                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                        session.setAttribute("MEAL_PLAN", mealPlan);
                                    }
                                    else if (mealplan.size() == 6) {
                                        dao.updateMealPlan(mealplan.get(0).getPlanID(), planDate, randomBreakfast3.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(1).getPlanID(), planDate, randomBreakfast5.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(2).getPlanID(), planDate, randomLunch1.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(3).getPlanID(), planDate, randomLunch2.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, randomDinner1.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(5).getPlanID(), planDate, minCaloRandomDinner2.getRecipeID());
                                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                        session.setAttribute("MEAL_PLAN", mealPlan);
                                    } else {
                                        dao.updateMealPlan(mealplan.get(0).getPlanID(), planDate, randomBreakfast3.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(1).getPlanID(), planDate, randomBreakfast5.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(2).getPlanID(), planDate, randomLunch1.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(3).getPlanID(), planDate, randomLunch2.getRecipeID());
                                        dao.updateMealPlan(mealplan.get(4).getPlanID(), planDate, randomDinner1.getRecipeID());
                                        dao.addMealPlan(planDate, adminID, minCaloRandomDinner2.getRecipeID());
                                        List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                                        session.setAttribute("MEAL_PLAN", mealPlan);
                                    }
                                }
                            }
                        }
                    }
                }

            }

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("plan.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
