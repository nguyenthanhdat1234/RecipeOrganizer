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
@WebServlet(name = "RefreshRecipeController", urlPatterns = {"/RefreshRecipeController"})
public class RefreshRecipeController extends HttpServlet {

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
        try {
            int recipeId = Integer.parseInt(request.getParameter("refreshRecipe"));
            LocalDate currentDate = LocalDate.now();
            String planDate = currentDate.toString();
            Random random = new Random();
            HttpSession session = request.getSession(false);
            RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
            String categoryID = dao.getCategoryByRecipeID(recipeId);
            List<RecipeOrganizeDTO> list = dao.getRecipeByCategory(categoryID);
            Collections.sort(list, new Comparator<RecipeOrganizeDTO>() {
                @Override
                public int compare(RecipeOrganizeDTO recipe1, RecipeOrganizeDTO recipe2) {
                    // So sánh dựa trên thuộc tính caloRecipe
                    return Float.compare(recipe1.getCaloRecipe(), recipe2.getCaloRecipe());
                }
            });
            if (session.getAttribute("USER") != null) {
                RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("USER");
                int userID = user.getUserID();
                int planID = 0;
                List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(userID);
                for (RecipeOrganizeDTO meal_plan : mealplan) {
                    if (recipeId == meal_plan.getRecipeID()) {
                        planID = meal_plan.getPlanID();
                        break;
                    }
                }
                for (int i = list.size() - 1; i >= 0; i--) {
                    if (recipeId == list.get(i).getRecipeID()) {
                        if (recipeId == list.get(list.size() - 1).getRecipeID()) {
                            int refresh_recipe = list.get(list.size() - 2).getRecipeID();
                            dao.updateMealPlan(planID, planDate, refresh_recipe);
                            List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                            session.setAttribute("MEAL_PLAN", mealPlan);
                        } else if (recipeId == list.get(0).getRecipeID()) {
                            int refresh_recipe = list.get(1).getRecipeID();
                            dao.updateMealPlan(planID, planDate, refresh_recipe);
                            List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                            session.setAttribute("MEAL_PLAN", mealPlan);
                        } else if (recipeId == list.get(i).getRecipeID()) {
                            int refresh_recipe1 = list.get(i - 1).getRecipeID();
                            int refresh_recipe2 = list.get(i + 1).getRecipeID();
                            int randomRecipe = random.nextInt(2) == 0 ? refresh_recipe1 : refresh_recipe2;
                            dao.updateMealPlan(planID, planDate, randomRecipe);
                            List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(userID);
                            session.setAttribute("MEAL_PLAN", mealPlan);
                        }
                        break;
                    }
                }
            } else {
                RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                int adminID = user.getUserID();
                int planID = 0;
                List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(adminID);
                for (RecipeOrganizeDTO meal_plan : mealplan) {
                    if (recipeId == meal_plan.getRecipeID()) {
                        planID = meal_plan.getPlanID();
                        break;
                    }
                }
                for (int i = list.size() - 1; i >= 0; i--) {
                    if (recipeId == list.get(i).getRecipeID()) {
                        if (recipeId == list.get(list.size() - 1).getRecipeID()) {
                            int refresh_recipe = list.get(list.size() - 2).getRecipeID();
                            dao.updateMealPlan(planID, planDate, refresh_recipe);
                            List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                            session.setAttribute("MEAL_PLAN", mealPlan);
                        } else if (recipeId == list.get(0).getRecipeID()) {
                            int refresh_recipe = list.get(1).getRecipeID();
                            dao.updateMealPlan(planID, planDate, refresh_recipe);
                            List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                            session.setAttribute("MEAL_PLAN", mealPlan);
                        } else if (recipeId == list.get(i).getRecipeID()) {
                            int refresh_recipe1 = list.get(i - 1).getRecipeID();
                            int refresh_recipe2 = list.get(i + 1).getRecipeID();
                            int randomRecipe = random.nextInt(2) == 0 ? refresh_recipe1 : refresh_recipe2;
                            dao.updateMealPlan(planID, planDate, randomRecipe);
                            List<RecipeOrganizeDTO> mealPlan = dao.getMealPlan(adminID);
                            session.setAttribute("MEAL_PLAN", mealPlan);
                        }
                        break;
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
            Logger.getLogger(RefreshRecipeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RefreshRecipeController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RefreshRecipeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RefreshRecipeController.class.getName()).log(Level.SEVERE, null, ex);
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
