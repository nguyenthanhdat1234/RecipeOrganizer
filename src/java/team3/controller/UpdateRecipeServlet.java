/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team3.DTO.RecipeDTO;
import team3.DTO.StepsDTO;
import team3.recipe.RecipeOrganizeDAO;

/**
 *
 * @author dangt
 */
public class UpdateRecipeServlet extends HttpServlet {

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
   
    try {
        
        
        // Lấy thông tin từ request
       String recipeID = request.getParameter("recipeID"); // Thay đổi giá trị này để lấy recipeID tùy thuộc vào logic của bạn

        // Gọi phương thức trong DAO để lấy thông tin công thức
        RecipeOrganizeDAO recipeDAO = new RecipeOrganizeDAO();
        List<RecipeDTO> recipes = recipeDAO.searchRecipeID(recipeID);
        List<StepsDTO> recipeS = recipeDAO.stepByRecipeID(recipeID);

        // Đặt thông tin công thức vào thuộc tính request
        request.setAttribute("recipeID", recipes.get(0).getRecipeID());
        request.setAttribute("recipeName", recipes.get(0).getRecipeName());
        request.setAttribute("caloRecipe", recipes.get(0).getCaloRecipe());
        request.setAttribute("description", recipes.get(0).getDescription());
        request.setAttribute("imgUrl", recipes.get(0).getImgUrl());
        request.setAttribute("difficulty", recipes.get(0).getDifficulty());
        request.setAttribute("ingredient_table", recipes.get(0).getIngredient_table());
        request.setAttribute("DescriptionName", recipeS.get(0).getDescriptionName());

        // Forward đến trang cập nhật
        request.getRequestDispatcher("updaterecipe.jsp").forward(request, response);
    } catch (Exception ex) {
        // Xử lý thông báo lỗi nếu có
        String errorMessage = "Đã xảy ra lỗi khi lấy thông tin công thức: " + ex.getMessage();
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("updaterecipe.jsp").forward(request, response);
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try {
        // Lấy thông tin từ request
        String recipeID = request.getParameter("recipeID");
        String recipeName = request.getParameter("recipeName");
        String caloRecipe = request.getParameter("caloRecipe");
        String description = request.getParameter("description");
        String imgUrl = request.getParameter("imgUrl");
        String difficulty = request.getParameter("difficulty");
        String ingredient_table = request.getParameter("ingredient_table");
        String categoryID = request.getParameter("categoryID");

        // Lấy danh sách các bước từ request
        String stepsString = request.getParameter("steps");
        List<String> steps = Arrays.asList(stepsString.split("\\|"));

        // Gọi phương thức trong DAO để cập nhật công thức
        RecipeOrganizeDAO recipeDAO = new RecipeOrganizeDAO();
        recipeDAO.updateRecipe(recipeName, caloRecipe, description, imgUrl, difficulty, ingredient_table, categoryID, recipeID, steps);

        // Chuyển hướng đến trang quản lý công thức sau khi cập nhật thành công
        response.sendRedirect("RecipeManagementController");
    } catch (Exception ex) {
        // Xử lý thông báo lỗi nếu có
        String errorMessage = "Đã xảy ra lỗi khi cập nhật công thức: " + ex.getMessage();
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("updaterecipe.jsp").forward(request, response);
    }
}

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
