/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.*;

import java.util.regex.Pattern;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.RequestDispatcher;
import team3.recipe.RecipeOrganizeDAO;
import team3.recipe.RecipeOrganizeDTO;

/**
 *
 * @author MSI BH
 */
@WebServlet(name = "SendEmailToGetTokenController", urlPatterns = {"/SendEmailToGetTokenController"})
public class SendEmailToGetTokenController extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String RESET_PASSWORD_PAGE = "forgot_password.jsp";

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        String email = request.getParameter("txtemail");
        String url = INVALID_PAGE;
        RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
        List<String> list = dao.getEmailToCheck(email);
        RecipeOrganizeDTO lists = dao.getTokenToResetPassword(email);
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundError = false;
        boolean inexisting_email = true;
        try {

            String email_pattern = "^[A-Za-z0-9]+@[A-Za-z0-9.-]+$";
            Pattern pattern = Pattern.compile(email_pattern);
            boolean isValidEmail = pattern.matcher(email).matches();

            for (String checkemail : list) {
                if (email.trim().equals(checkemail)) {
                    inexisting_email = false;
                    break;
                } else {
                    inexisting_email = true;
                }
            }
            if (isValidEmail == false) {
                foundError = true;
                errors.setEmailError("Invalid Email");
            } else if (isValidEmail && inexisting_email) {
                foundError = true;
                errors.setInexistingEmailError("You haven't signed up account before");
            }

            if (foundError) {
                url = RESET_PASSWORD_PAGE;
                request.setAttribute("ERROR", errors);
            } else {
                int count = 1;//biến đếm để set trạng thái của trang forgot_password.jsp
                request.setAttribute("COUNT", count);
                url = RESET_PASSWORD_PAGE;
                // Email team 3
                final String team3_email = "doanphamdangkhoitd2@gmail.com";
                // Mật khẩu ứng dụng của email phải xác thực 2 yếu tố cho email đó
                final String password_team3_email = "zdeljopzejidaenn";
                // dia chi email nguoi nhan
                String toEmail = email;
                final String subject = "Java Example Test";

                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
                props.put("mail.smtp.port", "587"); //TLS Port
                props.put("mail.smtp.auth", "true"); //enable authentication
                props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

                Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(team3_email, password_team3_email);
                    }
                });
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(team3_email));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
                message.setSubject(subject);
                String token = "";
                if (email.trim().equals(lists.getEmail())) {
                    token = lists.getToken();
                }
                message.setSubject("Reset your password");
                String htmlContent = "This is your token to reset your password: " + token;
                message.setContent(htmlContent, "text/html");
                Transport.send(message);
                boolean send_success = true;
                request.setAttribute("SEND_SUCCESS", send_success);
            }

        } catch (MessagingException e) {
            // Xử lý ngoại lệ MessagingException
            System.out.println("Lỗi gửi email: " + e.getMessage());
        } catch (NullPointerException e) {
            e.printStackTrace();
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
        } catch (SQLException ex) {
            Logger.getLogger(SendEmailToGetTokenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SendEmailToGetTokenController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(SendEmailToGetTokenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SendEmailToGetTokenController.class.getName()).log(Level.SEVERE, null, ex);
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
