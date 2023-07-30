/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String LOGIN_PAGE = "login.jsp";
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = INVALID_PAGE;
        String userName = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String email;
        if (request.getAttribute("email_name") == null) {
            email = null;
        } else {
            email = request.getAttribute("email_name").toString();
        }
        boolean invalid = false;
        boolean ban_acc = false;
        boolean invalid_gmail = false;
        try {
            RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
            RecipeOrganizeDTO result = dao.checkLogin(userName, password);
            RecipeOrganizeDTO results = dao.loginByGmail(email);
            HttpSession session = request.getSession(true);
            if (email == null) {
                if (result != null) {
                    if (result.getStatus() == 1) {
                        if (result.getRole() == true) {
                            session.setAttribute("ADMIN", result);
                            List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(result.getUserID());
                            session.setAttribute("MEAL_PLAN", mealplan);
                            url = HOME_PAGE;
                        } else if (result.getRole() == false) {
                            session.setAttribute("USER", result);
                            List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(result.getUserID());
                            session.setAttribute("MEAL_PLAN", mealplan);
                            url = HOME_PAGE;
                        }
                    } else {
                        url = LOGIN_PAGE;
                        ban_acc = true;
                        request.setAttribute("BAN_ACC", ban_acc);
                    }
                } else {
                    url = LOGIN_PAGE;
                    invalid = true;
                    request.setAttribute("WRONG", invalid);
                }
            } else {
                if (results != null) {

                    if (results.getStatus() == 1) {
                        if (results.getRole() == true) {
                            session.setAttribute("ADMIN", results);
                            List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(results.getUserID());
                            session.setAttribute("MEAL_PLAN", mealplan);
                            url = HOME_PAGE;
                        } else if (results.getRole() == false) {
                            session.setAttribute("USER", results);
                            List<RecipeOrganizeDTO> mealplan = dao.getMealPlan(results.getUserID());
                            session.setAttribute("MEAL_PLAN", mealplan);
                            url = HOME_PAGE;
                        }
                    } else {
                        url = LOGIN_PAGE;
                        ban_acc = true;
                        request.setAttribute("BAN_ACC", ban_acc);
                    }

                } else {
                    url = LOGIN_PAGE;
                    invalid_gmail = true;
                    request.setAttribute("GMAIL_HAVEN_NOT_IN_DATABASE", invalid_gmail);
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
