/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team3.recipe.RecipeOrganizeDAO;
import team3.recipe.RecipeOrganizeDTO;

/**
 *
 * @author tranb
 */
@WebServlet(name = "searchAccountController", urlPatterns = {"/searchAccountController"})
public class searchAccountController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
            /* TODO output your page here. You may use following sample code. */
            String usersearch = request.getParameter("txtSearch");
            ArrayList<RecipeOrganizeDTO> result = dao.getManagerAccount();
            ArrayList<RecipeOrganizeDTO> result2 = new ArrayList<>();
            for (RecipeOrganizeDTO ac : result) {
                if (ac.getUserName().contains(usersearch)) {
                    result2.add(ac);
                }
            }
            request.setAttribute("usersearch", result2);
            request.getRequestDispatcher("managerAccount.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

//        try (PrintWriter out = response.getWriter()) {
//            RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
//            // Lấy giá trị từ request parameter
//            String usersearch = request.getParameter("txtSearch");
//
//            // Lấy danh sách tài khoản từ database
//            ArrayList<RecipeOrganizeDTO> result = dao.getManagerAccount();
//
//            // Filter danh sách theo giá trị tìm kiếm nếu có
//            if (usersearch != null && !usersearch.isEmpty()) {
//                ArrayList<RecipeOrganizeDTO> result2 = new ArrayList<>();
//                for (RecipeOrganizeDTO account : result) {
//                    if (account.getUserName().contains(usersearch)) {
//                        result2.add(account);
//                    }
//                }
//                request.setAttribute("usersearch", result2);
//            } else {
//                // Nếu không có giá trị tìm kiếm, sử dụng toàn bộ danh sách
//                request.setAttribute("accountList", result);
//            }
//
//            // Forward sang trang jsp để hiển thị danh sách
//            request.getRequestDispatcher("managerAccount.jsp").forward(request, response);
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }

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
