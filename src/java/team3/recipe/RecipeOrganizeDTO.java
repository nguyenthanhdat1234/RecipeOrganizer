/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.recipe;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author THIS PC
 */
public class RecipeOrganizeDTO implements Serializable {
    // khởi tạo biến cho accounts   
    private int userID;
    private String userName;
    private String password;
    private String fullName;
    private String phone;
    private int status;
    private boolean role;
    private String token;
    private String email;
    private String image_path;
    
    // khởi tạo biến cho recipe
    private int recipeID;
    private String recipeName;
    private float caloRecipe;
    private String description;
    private String imgUrl;
    private int avgRating;
    private String difficulty;
    private String ingredient_table;
    // khởi tạo biến cho category
    private int categoryID;
    private String categoryName;
    
    // khởi tạo biến cho meal plan
    private int planID;
    private String planDate;
   
    //feeback
    private int feedbackID;
    private int rating;
    private String comment;
    private Timestamp feedbackDate;

    public RecipeOrganizeDTO(int feedbackID, String userName, String comment, Timestamp feedbackDate) {
        this.feedbackID = feedbackID;
        this.userName = userName;
        this.comment = comment;
        this.feedbackDate = feedbackDate;
    } 

    public RecipeOrganizeDTO(int feedbackID, int rating, String comment, Timestamp feedbackDate) {
        this.feedbackID = feedbackID;
        this.rating = rating;
        this.comment = comment;
        this.feedbackDate = feedbackDate;
    }
    
    
    
    // khởi tạo hàm khởi tạo ko tham số
    public RecipeOrganizeDTO() {
    }

    // hàm khởi tạo để get token từ email 
    public RecipeOrganizeDTO(int userID, String token, String email) {
        this.userID = userID;
        this.token = token;
        this.email = email;
    }

    // hàm khởi tạo cho meal plan

    public RecipeOrganizeDTO(int planID, String planDate, int userID, int recipeID, String recipeName, float caloRecipe, String description, String imgUrl, int avgRating, String difficulty, String ingredient_table, int categoryID) {
        this.planID = planID;
        this.planDate = planDate;
        this.userID = userID;
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.caloRecipe = caloRecipe;
        this.description = description;
        this.imgUrl = imgUrl;
        this.avgRating = avgRating;
        this.difficulty = difficulty;
        this.ingredient_table = ingredient_table;
        this.categoryID = categoryID;
    }

    
    
    
    // hàm khởi tạo cho accounts
    public RecipeOrganizeDTO(int userID, String userName, String password, String fullName, String phone, int status, boolean role, String token, String email, String image_path) {
     
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.status = status;
        this.role = role;
        this.token = token;
        this.email = email;
        this.image_path = image_path;
    }

     //hàm khởi tạo cho recipe
    public RecipeOrganizeDTO(int recipeID, String recipeName, float caloRecipe, String description, String imgUrl, int avgRating, String difficulty, String ingredient_table, int categoryID) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.caloRecipe = caloRecipe;
        this.description = description;
        this.imgUrl = imgUrl;
        this.avgRating = avgRating;
        this.difficulty = difficulty;
        this.ingredient_table = ingredient_table;
        this.categoryID = categoryID;
    }

    // hàm khởi tạo cho chức năng gửi email tự động về meal plan cá nhân 
    public RecipeOrganizeDTO(int userID, String email, String planDate, int recipeID, String recipeName, float caloRecipe, String imgUrl) {
        this.userID = userID;
        this.email = email;
        this.planDate = planDate;
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.caloRecipe = caloRecipe;
        this.imgUrl = imgUrl;
    }
  
    public RecipeOrganizeDTO(int recipeID, String recipeName, float caloRecipe, String description, String imgUrl, int avgRating, String difficulty) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.caloRecipe = caloRecipe;
        this.description = description;
        this.imgUrl = imgUrl;
        this.avgRating = avgRating;
        this.difficulty = difficulty;
    }

    //hàm khởi tạo cho category

    public RecipeOrganizeDTO(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

//    RecipeOrganizeDTO(String fullName, String phone, String email) {
//        this.fullName = fullName;
//        this.phone = phone;
//        this.email = email;
//    }
    

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public float getCaloRecipe() {
        return caloRecipe;
    }

    public void setCaloRecipe(float caloRecipe) {
        this.caloRecipe = caloRecipe;
    }

    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIngredient_table() {
        return ingredient_table;
    }

    public void setIngredient_table(String ingredient_table) {
        this.ingredient_table = ingredient_table;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Timestamp feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    
    
}