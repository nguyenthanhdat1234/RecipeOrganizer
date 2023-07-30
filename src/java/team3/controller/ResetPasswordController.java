/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team3.recipe.RecipeOrganizeDAO;
import team3.recipe.RecipeOrganizeDTO;

/**
 *
 * @author MSI BH
 */
@WebServlet(name = "ResetPasswordController", urlPatterns = {"/ResetPasswordController"})
public class ResetPasswordController extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String RESET_PASSWORD_PAGE = "forgot_password.jsp";
    private final String HOME_PAGE = "homePage.jsp";

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
        String email = request.getParameter("txtemail");
        String token = request.getParameter("txttoken");
        String password = request.getParameter("txtpassword");
        boolean reset_password = false;
        RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
        RecipeOrganizeDTO result = dao.getTokenToResetPassword(email);
        String url = INVALID_PAGE;
        try {
            if (email.trim().equals(result.getEmail())) {
                String get_token = result.getToken();
                if (token.trim().equals(get_token)) {
                    if (password.trim().isEmpty()) {
                        url = RESET_PASSWORD_PAGE;
                        request.setAttribute("INVALID_PASSWORD", "Invalid password");
                    } else {
                        reset_password = dao.resetPassword(password, email, token);
                    }
                    if (reset_password) {
                        url = HOME_PAGE;
                        boolean reset_password_success = true;
                        request.setAttribute("RESET_PASSWORD_SUCCESS", reset_password_success);
                    }
                } else {
                    request.setAttribute("INVALID_CODE", "Invalid code");
                    url = RESET_PASSWORD_PAGE;
                }
            }
        } catch (NullPointerException ex) {
           
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
            Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
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
