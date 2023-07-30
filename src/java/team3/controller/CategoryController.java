



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team3.recipe.RecipeOrganizeDAO;
import team3.DTO.RecipeDTO;
import team3.DTO.AccountDTO;
import team3.DTO.CategoryDTO;
import team3.recipe.RecipeOrganizeDTO;

/**
 *
 * @author dangt
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/CategoryController"})
public class CategoryController extends HttpServlet {

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
        try {
            String categoryID = request.getParameter("categoryID");
            String name = request.getParameter("name");
            RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
            ArrayList<RecipeOrganizeDTO> listCategories = dao.getAllCategories();
            List<RecipeOrganizeDTO> listRecipe = dao.getAllRecipe();
            List<RecipeOrganizeDTO> list = dao.getRecipeByCategory(categoryID);
            
            try (PrintWriter out = response.getWriter()){
                if (categoryID != null && !categoryID.isEmpty()) {
                    if (categoryID.equalsIgnoreCase("0")) {
                        list = listRecipe;
                    } else {
                        list = dao.getRecipeByCategory(categoryID);
                    }
                } else {
                    list = listRecipe;
                }
                request.setAttribute("listR", listRecipe);
                request.setAttribute("list", list);
                request.setAttribute("listC", listCategories);
                request.setAttribute("txtS", name);
                request.getRequestDispatcher("searchRecipe.jsp").forward(request, response);
            }
        } catch (Exception e) {
            out.println(e);
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
