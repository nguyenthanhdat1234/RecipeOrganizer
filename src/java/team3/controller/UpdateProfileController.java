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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import team3.recipe.RecipeOrganizeDAO;
import team3.recipe.RecipeOrganizeDTO;

/**
 *
 * @author tranb
 */
public class UpdateProfileController extends HttpServlet {

    private final String PROFILE_CONTROLLER = "profile.jsp";
//    private final String UPDATE_PROFILE_CONTROLLER = "updateprofile.jsp";

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
        String url = PROFILE_CONTROLLER;
        HttpSession session = request.getSession(false);
        String fullName = request.getParameter("txtFullname");
        String phone = request.getParameter("txtPhone");
        String email = request.getParameter("txtEmail");
        RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
        boolean check = dao.searchAccount(email);
        boolean found_error = false;
        try {
            if (fullName.trim().isEmpty()) {
                found_error = true;
                request.setAttribute("message1", "The fullname is empty!");
            }
            if (phone.trim().isEmpty()) {
                phone = null;
            } else if (phone.length() != 10 && phone.length() != 0) {
                found_error = true;
                request.setAttribute("message2", "Phone number must have 10 digits!");
            }

            String email_pattern = "^[A-Za-z0-9]+@[A-Za-z0-9.-]+$";
            Pattern pattern = Pattern.compile(email_pattern);
            boolean isValidEmail = pattern.matcher(email).matches();
            if (isValidEmail == false) {
                found_error = true;
                request.setAttribute("message3", "Invalid email!");
            }

            if (session.getAttribute("USER") != null) {
                RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("USER");
                String userEmail = user.getEmail();
                if (isValidEmail && check && !email.trim().equals(userEmail)) {
                    found_error = true;
                    request.setAttribute("message4", "Email existed. Please check again!");
                } else if (isValidEmail && check && email.trim().equals(userEmail)) {
                    check = false;
                }
            } else {
                RecipeOrganizeDTO admin = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                String adminEmail = admin.getEmail();
                if (isValidEmail && check && !email.trim().equals(adminEmail)) {
                    found_error = true;
                    request.setAttribute("message4", "Email existed. Please check again!");
                } else if (isValidEmail && check && email.trim().equals(adminEmail)) {
                    check = false;
                }
            }
            if (check == false && found_error == false) {
                if (session.getAttribute("USER") != null) {
                    url = PROFILE_CONTROLLER;
                    RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("USER");
                    String userName = user.getUserName();
                    boolean result = RecipeOrganizeDAO.updateProfileAccount(userName, fullName, phone, email);
                    if (result) {
                        user.setFullName(fullName);
                        user.setPhone(phone);
                        user.setEmail(email);
                        session.setAttribute("USER", user);
                        request.setAttribute("message5", "Update successfully!");
                    }
                } else {
                    url = PROFILE_CONTROLLER;
                    RecipeOrganizeDTO admin = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                    String adminName = admin.getUserName();
                    boolean result = RecipeOrganizeDAO.updateProfileAccount(adminName, fullName, phone, email);
                    if (result) {
                        admin.setFullName(fullName);
                        admin.setPhone(phone);
                        admin.setEmail(email);
                        session.setAttribute("ADMIN", admin);
                        request.setAttribute("message6", "Update successfully!");
                    }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
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
