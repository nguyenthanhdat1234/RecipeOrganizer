/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
public class HeathyRecipeController extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Lấy giá trị từ request
       
        String minCaloriesString = request.getParameter("minCalories");
        String maxCaloriesString = request.getParameter("maxCalories");

        // Kiểm tra nếu không có giá trị được gửi đi, sử dụng giá trị mặc định
        int minCalories = minCaloriesString != null && !minCaloriesString.isEmpty() ? Integer.parseInt(minCaloriesString) : 500;
        int maxCalories = maxCaloriesString != null && !maxCaloriesString.isEmpty() ? Integer.parseInt(maxCaloriesString) : 1000;

        try {
            // Gọi phương thức getRecipesByCategoryAndCalories với các giá trị đã lấy
            RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
            List<RecipeOrganizeDTO> recipes = dao.getRecipesCalories( minCalories, maxCalories);

            // Đặt danh sách sản phẩm vào thuộc tính request
            request.setAttribute("recipes", recipes);

            // Chuyển hướng đến trang hiển thị danh sách sản phẩm
            RequestDispatcher dispatcher = request.getRequestDispatcher("heathy.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu có
            request.setAttribute("message", "Error occurred while retrieving recipes.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
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
