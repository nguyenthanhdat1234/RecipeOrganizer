/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team3.recipe.RecipeOrganizeDAO;

/**
 *
 * @author dangt
 */
public class CreateRecipeController extends HttpServlet {

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
            String recipeName = request.getParameter("recipeName");
            String caloRecipe = request.getParameter("caloRecipe");
            String description = request.getParameter("description");
            String imgUrl = request.getParameter("imgUrl");
            String difficulty = request.getParameter("difficulty");
            String ingredient_table = request.getParameter("ingredient_table");
            String categoryID = request.getParameter("categoryID");

            // Lấy danh sách bước từ request
            String stepsString = request.getParameter("steps");
            List<String> steps = Arrays.asList(stepsString.split("\\|"));

            // Gọi phương thức createRecipe() trong lớp RecipeOrganizeDAO
            RecipeOrganizeDAO recipeDAO = new RecipeOrganizeDAO();
            // Gọi phương thức createRecipe() với danh sách bước
            boolean success = recipeDAO.CreateRecipe(recipeName, caloRecipe, description, imgUrl, difficulty, ingredient_table, categoryID, steps);
            if (success) {

                request.getRequestDispatcher("RecipeManagementController").forward(request, response);

            } else {
                // Thêm thất bại
                request.setAttribute("message", "Thêm công thức thất bại.");
                request.getRequestDispatcher("create.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Xử lý lỗi
            request.setAttribute("message", "Nhập công thức thất bại.");
            request.getRequestDispatcher("create.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi
            request.setAttribute("message", "Nhập công thức thất bại.");
            request.getRequestDispatcher("create.jsp").forward(request, response);
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
