/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author THIS PC
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {

    private final String HOME_PAGE = "homePage.jsp";
    private final String LOGIN_CONTROLLER = "LoginController";
    private final String LOGOUT_CONTROLLER = "LogoutController";
    private final String SEARCH_RECIPE = "SearchRecipe";
    private final String SIGN_UP_CONTROLLER = "SignUpController";
    private final String SEND_EMAIL_TO_GET_TOKEN_CONTROLLER = "SendEmailToGetTokenController";
    private final String RESET_PASSWORD_CONTROLLER = "ResetPasswordController";
    private final String CHANGE_PASS_CONTROLLER = "ChangePasswordController";
    private final String SEARCH_CONTROLLER = "SearchController";
    private final String UPDATE_PROFILE_CONTROLLER = "UpdateProfileController";
    private final String PLAN_CONTROLLER = "PlanController";
    private final String SEARCH_ACCOUNT_CONTROLLER = "searchAccountController";
    private final String MANAGER_STATUS_ACCOUNT = "updateStatusAccountController";
    private final String REMOVE_FAVORITE_CONTROLLER = "RemoveFavoriteController";

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

        String url = HOME_PAGE;
        String button = request.getParameter("btAction");
        try {
            if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {
                url = SEARCH_RECIPE;
            } else if (button.equals("Sign up")) {
                url = SIGN_UP_CONTROLLER;
            } else if (button.equals("Send")) {
                url = SEND_EMAIL_TO_GET_TOKEN_CONTROLLER;
            } else if (button.equals("Reset")) {
                url = RESET_PASSWORD_CONTROLLER;
            } else if (button.equals("Save")) {
                url = CHANGE_PASS_CONTROLLER;
            } else if (button.equals("search")) {
                url = SEARCH_CONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATE_PROFILE_CONTROLLER;
            } else if (button.equals("Log out")) {
                url = LOGOUT_CONTROLLER;
            } else if (button.equals("Generate")) {
                url = PLAN_CONTROLLER;
            } else if (button.equals("Search Account")) {
                url = SEARCH_ACCOUNT_CONTROLLER;
            } else if (button.equals("updateStatusAccount")) {
                url = MANAGER_STATUS_ACCOUNT;
            } else if (button.equals("Remove")) {
                url = REMOVE_FAVORITE_CONTROLLER;
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
