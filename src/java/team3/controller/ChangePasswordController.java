/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author THIS PC
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/ChangePasswordController"})
public class ChangePasswordController extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String CHANGE_PASSWORD_PAGE = "changepassword.jsp";

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
        String userName = request.getParameter("txtUsername");
        String currentPassword = request.getParameter("txtCurrentPassword");
        String newPassword = request.getParameter("txtNewPassword");
        String url = CHANGE_PASSWORD_PAGE;
        boolean found_error = false;
        try {
            if (newPassword.length() < 1) {
                found_error = true;
            }
            HttpSession session = request.getSession(false);
            if (session.getAttribute("USER") != null) {
                RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("USER");
                String username = user.getUserName();
                String userpassword = user.getPassword();
                if (!userName.trim().equals(username) || !currentPassword.trim().equals(userpassword)) {
                    found_error = true;
                    request.setAttribute("message8", "The username, password is wrong!");
                }
            } else {
                RecipeOrganizeDTO admin = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                String adminname = admin.getUserName();
                String adminpassword = admin.getPassword();
                if (!userName.trim().equals(adminname) || !currentPassword.trim().equals(adminpassword)) {
                    found_error = true;
                    request.setAttribute("message8", "The username, password is wrong!");
                }
            }
            RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
            RecipeOrganizeDTO isLogin = dao.checkLogin(userName, currentPassword);
            if (isLogin != null && found_error == false) {
                RecipeOrganizeDAO.changePassword(userName, newPassword);
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            } else if (isLogin != null && found_error) {
                request.setAttribute("message7", "New password is invalid!");
                RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
                rd.forward(request, response);
            } else if (isLogin == null) {
                request.setAttribute("message8", "The username, password is wrong!");
                RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
                rd.forward(request, response);
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
