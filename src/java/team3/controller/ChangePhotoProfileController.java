/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFileChooser;
import team3.recipe.RecipeOrganizeDAO;
import team3.recipe.RecipeOrganizeDTO;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author MSI BH
 */
@WebServlet(name = "ChangePhotoProfileController", urlPatterns = {"/ChangePhotoProfileController"})
public class ChangePhotoProfileController extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String PROFILE_PAGR = "profile.jsp";

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
        String url = INVALID_PAGE;
        HttpSession session = request.getSession(false);
        try {
            // Tạo một đối tượng JFileChooser
            JFileChooser fileChooser = new JFileChooser();
            // Thiết lập thư mục mặc định
            String defaultDirectory = "D:\\Netbean\\swp391\\RecipeOrganize\\web\\img\\recipe";
            fileChooser.setCurrentDirectory(new File(defaultDirectory));
            // Thiết lập bộ lọc tệp tin
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images(.jpg, .png)", "jpg", "png");
            fileChooser.setFileFilter(filter);
            // Hiển thị hộp thoại chọn tệp tin
            int result = fileChooser.showOpenDialog(null);
            // Xử lý kết quả
            if (result == JFileChooser.APPROVE_OPTION) {
                if (fileChooser.getSelectedFile().getPath().contains("img\\recipe\\")) {
                    // Lấy đường dẫn đến tệp tin đã chọn               
                    String filePath = fileChooser.getSelectedFile().getPath();
                    // Đảo ngược chuỗi đường dẫn
                    String reversedPath = new StringBuilder(filePath).reverse().toString();
                    int thirdBackslashIndex = reversedPath.indexOf("\\", reversedPath.indexOf("\\") + 1) + 4;
                    String subpart = filePath.substring(filePath.length() - thirdBackslashIndex);
                    if (session.getAttribute("USER") != null) {
                        RecipeOrganizeDTO user = (RecipeOrganizeDTO) session.getAttribute("USER");
                        String userName = user.getUserName();
                        boolean update = RecipeOrganizeDAO.updatePhotoProfile(userName, subpart);
                        if (update) {
                            user.setImage_path(subpart);
                            session.setAttribute("USER", user);
                            url = PROFILE_PAGR;
                        }
                    } else {
                        RecipeOrganizeDTO admin = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                        String adminName = admin.getUserName();
                        boolean update = RecipeOrganizeDAO.updatePhotoProfile(adminName, subpart);
                        if (update) {
                            admin.setImage_path(subpart);
                            session.setAttribute("ADMIN", admin);
                            url = PROFILE_PAGR;
                        }
                    }
                } else {
                    request.setAttribute("message", "Invalid Format, please check again!");
                    url = PROFILE_PAGR;
                }
            } else if (result == JFileChooser.CANCEL_OPTION) {
                url = PROFILE_PAGR;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChangePhotoProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChangePhotoProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect(url);

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
