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
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team3.recipe.RecipeOrganizeDAO;

/**
 *
 * @author AS
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/SignUpController"})
public class SignUpController extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String INVALID_PAGE = "invalid.html";
    private final String REGISTRATION_PAGE = "registration.jsp";

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
        PrintWriter out = response.getWriter();

        String url = INVALID_PAGE;
        String username = request.getParameter("txtusername");
        String password = request.getParameter("txtpassword");
        String confirm = request.getParameter("txtrepassword");
        String fullName = request.getParameter("txtfullname");
        String phone = request.getParameter("txtphone");
        String email = request.getParameter("txtemail");

        RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundError = false;
        boolean signup_success = false;
        boolean existed_email = false;

        try {
            if (username.trim().length() < 1 || username.trim().length() > 30) {
                foundError = true;
                errors.setUsernameLengthError("Username length requires 1 - 30 characters");
            }

            if (password.trim().length() < 1 || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengError("Password length requires 1 - 30 characters");
            } else if (!confirm.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmError("Confirm must match password");
            }

            if (fullName.trim().length() < 1 || fullName.trim().length() > 50) {
                foundError = true;
                errors.setFullNameLengthError("Fullname length requires 1 - 50 characters");
            }

            if (phone.trim().length() != 10 && phone.trim().length() != 0) {
                foundError = true;
                errors.setPhoneLengthError("Phone number must have 10 digits");
            } else if (phone.trim().length() == 0) {
                phone = null;
            }

            String email_pattern = "^[A-Za-z0-9]+@[A-Za-z0-9.-]+$";
            Pattern pattern = Pattern.compile(email_pattern);
            boolean isValidEmail = pattern.matcher(email).matches();
            List<String> list = dao.getEmailToCheck(email);
            for (String checkemail : list) {
                if (email.trim().equals(checkemail)) {
                    existed_email = true;
                    request.setAttribute("EXSITED_EMAIL", existed_email);
                    break;
                }

            }
            if (isValidEmail == false) {
                foundError = true;
                errors.setEmailError("Invalid Email");
            } else if (isValidEmail && existed_email) {
                foundError = true;
                errors.setExistedEmailError("Existed Email");
            }

            if (foundError) {
                url = REGISTRATION_PAGE;
                request.setAttribute("ERROR", errors);
            } else {
                Random random = new Random();
                int randomNumber = random.nextInt(1000); // Số ngẫu nhiên từ 0 đến 999
                String formattedNumber = String.format("%03d", randomNumber); // Định dạng thành 3 chữ số
                String token = "token" + formattedNumber;
                boolean result = dao.SignUp(username, password, fullName, phone, 1, false, token, email);
                if (result) {
                    url = LOGIN_PAGE;
                    signup_success = true;
                    request.setAttribute("SIGNUP_SUCCESS", signup_success);
                }
            }

        } catch (ClassNotFoundException ex) {
            log(ex.getMessage());
        } catch (NamingException ex) {
            log("Create Account     Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("Create Account     SQL" + ex.getMessage());
            String msg = ex.getMessage();
            if (msg.contains("duplicate")) {
                errors.setExistedUsernameError(username + " have existed");
                request.setAttribute("ERROR", errors);
                url = REGISTRATION_PAGE;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
