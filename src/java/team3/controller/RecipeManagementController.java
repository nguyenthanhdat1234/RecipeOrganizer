/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team3.recipe.RecipeOrganizeDAO;
import team3.recipe.RecipeOrganizeDTO;

/**
 *
 * @author dangt
 */
public class RecipeManagementController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    String searchValue = request.getParameter("txtSearch");

    try {
        // Create an instance of RecipeOrganizeDAO
        RecipeOrganizeDAO recipeDAO = new RecipeOrganizeDAO();
        List<RecipeOrganizeDTO> recipes;

        if (searchValue != null && !searchValue.trim().isEmpty()) {
            // If a search value is provided, perform a search using the DAO
            recipes = recipeDAO.searchRecipe(searchValue);

            if (recipes.isEmpty()) {
                // If no results found, set an error message and display it to the user
                String errorMessage = "No recipes found for the search: " + searchValue;
                request.setAttribute("errorMessage", errorMessage);
            }
        } else {
            // If no search value is provided, set searchValue to null and get all recipes using the DAO
            searchValue = null;
            recipes = recipeDAO.getAllRecipe();
        }

        // Set the recipes and search value as request attributes
        request.setAttribute("recipes", recipes);
        request.setAttribute("txtSearch", searchValue);

        // Forward the request to the recipe-management.jsp
        request.getRequestDispatcher("recipe-management.jsp").forward(request, response);
    } catch (Exception ex) {
        // Handle error message if an exception occurs
        String errorMessage = "An error occurred while loading the recipe list: " + ex.getMessage();
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("recipe-management.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
