/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team3.recipe.RecipeOrganizeDAO;
import team3.recipe.RecipeOrganizeDTO;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author MSI BH
 */
@WebServlet(name = "SendEmailWithMealPlan", urlPatterns = {"/SendEmailWithMealPlan"})
public class SendEmailWithMealPlan extends HttpServlet {
            List<RecipeOrganizeDTO> individual_list;
            LocalDate currentDate = LocalDate.now();
            String planDate = currentDate.toString();
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
        try {           
            SendEmailWithMealPlan sendemailwithmealplan = new SendEmailWithMealPlan();
            List list = sendemailwithmealplan.getAllMealPlan();
            individual_list = new ArrayList<>();
            for (Object mealplan : list) {
                if (!mealplan.equals("#")) {
                    individual_list.add((RecipeOrganizeDTO) mealplan);
                } else {
                    Timer timer = new Timer();
                    // Định thời gian gửi email (ví dụ: 13h45 giờ)
                    Calendar scheduledTime = Calendar.getInstance();
                    scheduledTime.set(Calendar.HOUR_OF_DAY, 13);
                    scheduledTime.set(Calendar.MINUTE, 02);
                    scheduledTime.set(Calendar.SECOND, 0);

                    // Lên lịch gửi email
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {  
                            
                        }
                    }, scheduledTime.getTime());
                    SendMail();
                    individual_list.clear();
                }
            }
        } finally {
            response.sendRedirect("homePage.jsp");
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
            Logger.getLogger(SendEmailWithMealPlan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SendEmailWithMealPlan.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SendEmailWithMealPlan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SendEmailWithMealPlan.class.getName()).log(Level.SEVERE, null, ex);
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

    private List getAllMealPlan() throws ClassNotFoundException, SQLException {
        List new_mealplan_list = new ArrayList<>();
        RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
        List<RecipeOrganizeDTO> mealplan_list = dao.getAllMealPlanToSendEmailAutomatically();
        for (int i = 0; i < mealplan_list.size(); i++) {
            if (mealplan_list.get(i).equals(mealplan_list.get(mealplan_list.size() - 1))) {
                new_mealplan_list.add(mealplan_list.get(mealplan_list.size() - 1));
                new_mealplan_list.add("#");
            } else if (mealplan_list.get(i).getEmail().equals(mealplan_list.get(i + 1).getEmail())) {
                new_mealplan_list.add(mealplan_list.get(i));
            } else if (!mealplan_list.get(i).getEmail().equals(mealplan_list.get(i + 1).getEmail())) {
                new_mealplan_list.add(mealplan_list.get(i));
                new_mealplan_list.add("#");
            }
        }
        return new_mealplan_list;
    }
    
    private void SendMail() {
        final String team3_email = "doanphamdangkhoitd2@gmail.com";
        // Mật khẩu <ứng dụng> của email phải xác thực 2 yếu tố cho email đó
        final String password_team3_email = "zdeljopzejidaenn";
        // dia chi email nguoi nhan
        String toEmail = individual_list.get(0).getEmail();

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
        try {
            message.setFrom(new InternetAddress(team3_email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            message.setSubject("Recipe Organize");
        } catch (AddressException ex) {
            Logger.getLogger(SendEmailWithMealPlan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmailWithMealPlan.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        for (RecipeOrganizeDTO plandate : individual_list) {
            if (plandate.getPlanDate().equals(planDate)) {
                String htmlContent = "<h2>Today's Meal Plan</h2><br>";
                for (int i = 0; i < 2; i++) {
                    htmlContent += "<img src=\"" + individual_list.get(i).getImgUrl() + "\" alt=\"\" width=\"60\" height=\"60\">";
                    htmlContent += individual_list.get(i).getRecipeName() + "<br>";
                }
                for (int i = 2; i < 4; i++) {
                    htmlContent += "<img src=\"" + individual_list.get(i).getImgUrl() + "\" alt=\"\" width=\"60\" height=\"60\">";
                    htmlContent += individual_list.get(i).getRecipeName() + "<br>";
                }
                if (individual_list.size() == 5) {
                    htmlContent += "<img src=\"" + individual_list.get(4).getImgUrl() + "\" alt=\"\" width=\"60\" height=\"60\">";
                    htmlContent += individual_list.get(4).getRecipeName() + "<br>";
                } else {
                    for (int i = 4; i < 6; i++) {
                        htmlContent += "<img src=\"" + individual_list.get(i).getImgUrl() + "\" alt=\"\" width=\"60\" height=\"60\">";
                        htmlContent += individual_list.get(i).getRecipeName() + "<br>";
                    }
                }
                try {
                    message.setContent(htmlContent, "text/html");
                    Transport.send(message);
                } catch (MessagingException ex) {
                    Logger.getLogger(SendEmailWithMealPlan.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            } else {
                count += 1;
                if (individual_list.size() == 5 && count == 5) {
                    count = 0;
                    String link = "http://localhost:8084/RecipeOrgainze/";
                    String text = "Click here";
                    String htmlContent = "<h2>Today, you haven't set a meal plan yet!</h2><br>"
                            + "Create your own?" + "<a href=\"" + link + "\">" + text + "</a>";
                    try {
                        message.setContent(htmlContent, "text/html");
                        Transport.send(message);
                    } catch (MessagingException ex) {
                        Logger.getLogger(SendEmailWithMealPlan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (individual_list.size() == 6 && count == 6) {
                    count = 0;
                    String link = "http://localhost:8084/RecipeOrgainze/";
                    String text = "Click here";
                    String htmlContent = "<h2>Today, you haven't set a meal plan yet!</h2><br>"
                            + "Create your own?" + "<a href=\"" + link + "\">" + text + "</a>";
                    try {
                        message.setContent(htmlContent, "text/html");
                        Transport.send(message);
                    } catch (MessagingException ex) {
                        Logger.getLogger(SendEmailWithMealPlan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
    }   
}