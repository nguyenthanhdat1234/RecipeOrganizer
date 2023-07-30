/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import team3.DTO.RecipeDTO;
import team3.DTO.ShowRatingDTO;
import team3.DTO.StepsDTO;
import team3.recipe.RecipeOrganizeDAO;
import team3.recipe.RecipeOrganizeDTO;

/**
 *
 * @author AS
 */
@WebServlet(name = "DetailController", urlPatterns = {"/DetailController"})
public class DetailController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String recipeID = request.getParameter("recipeID");
        String comment = request.getParameter("comment");
        if (comment != null && !comment.isEmpty()) {
            // Kiểm tra session để đảm bảo người dùng đã đăng nhập
            HttpSession session = request.getSession(false);
            if (session != null && (session.getAttribute("USER") != null || session.getAttribute("ADMIN") != null)) {
                RecipeOrganizeDTO user = null;
                if (session.getAttribute("USER") != null) {
                    user = (RecipeOrganizeDTO) session.getAttribute("USER");
                } else if (session.getAttribute("ADMIN") != null) {
                    user = (RecipeOrganizeDTO) session.getAttribute("ADMIN");
                }

                String userName = user.getUserName();
                RecipeOrganizeDAO commentDAO = new RecipeOrganizeDAO();
                Timestamp feedbackDate = new Timestamp(System.currentTimeMillis());
                boolean success = commentDAO.saveComment(userName, recipeID, comment, feedbackDate);
                if (success) {
                    // Hiển thị thông báo lưu comment thành công
                    request.setAttribute("commentSuccess", "Your comment has been saved successfully.");
                } else {
                    // Hiển thị thông báo lưu comment không thành công
                    request.setAttribute("commentError", "An error occurred while saving your comment.");
                }
            } else {
                // Chuyển hướng đến trang đăng nhập nếu người dùng chưa đăng nhập
                String loginMessage = "Please login or create an account to comment.";
                response.sendRedirect("login.jsp?message=" + URLEncoder.encode(loginMessage, "UTF-8"));
                return;
            }
        }

        // Lấy danh sách bình luận của công thức nấu ăn dựa trên recipeID
        RecipeOrganizeDAO commentDAO = new RecipeOrganizeDAO();
        List<RecipeOrganizeDTO> userComments = commentDAO.getCommentsByProductId(recipeID);
        request.setAttribute("userComments", userComments);

        try {
            HttpSession session = request.getSession();
            
            RecipeOrganizeDAO dao = new RecipeOrganizeDAO();
            List<RecipeDTO> detailProduct = dao.searchRecipeID(recipeID);
            List<StepsDTO> detailSteps = dao.stepByRecipeID(recipeID);
            dao.ShowRating(Integer.parseInt(recipeID));
            List<ShowRatingDTO> result = dao.getListRating();
            int reID = Integer.parseInt(recipeID);
            dao.updateRatingRecipe(0, reID);
            
            
            session.setAttribute("SHOWRATING", result);
            
            // Show inde
            String show = "";
            for(RecipeDTO s: detailProduct){
                show = s.getIngredient_table();
            }
            String[] ingde = new String[50];
            ingde = show.split("\\|");
            //show steps
            String showSteps = "";
            for(StepsDTO s: detailSteps){
                show = s.getDescriptionName();
            }
            String[] steps = new String[50];
            steps = show.split("\\|");
            request.setAttribute("steps", steps);
            request.setAttribute("ingde", ingde);
            request.setAttribute("detailP", detailProduct);
            request.getRequestDispatcher("detail.jsp").forward(request, response);
        } catch (Exception e) {

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
            Logger.getLogger(DetailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DetailController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DetailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DetailController.class.getName()).log(Level.SEVERE, null, ex);
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
