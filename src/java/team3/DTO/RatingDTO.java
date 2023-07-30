/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.DTO;

import java.io.Serializable;

/**
 *
 * @author THIS PC
 */
public class RatingDTO implements Serializable {
    private int ratingID;
    private String userName;
    private int recipeID;
    private float ratingValue;

    public RatingDTO() {
    }

    public RatingDTO(int ratingID, String userName, int recipeID, float ratingValue) {
        this.ratingID = ratingID;
        this.userName = userName;
        this.recipeID = recipeID;
        this.ratingValue = ratingValue;
    }

    /**
     * @return the ratingID
     */
    public int getRatingID() {
        return ratingID;
    }

    /**
     * @param ratingID the ratingID to set
     */
    public void setRatingID(int ratingID) {
        this.ratingID = ratingID;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the recipeID
     */
    public int getRecipeID() {
        return recipeID;
    }

    /**
     * @param recipeID the recipeID to set
     */
    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    /**
     * @return the ratingValue
     */
    public float getRatingValue() {
        return ratingValue;
    }

    /**
     * @param ratingValue the ratingValue to set
     */
    public void setRatingValue(float ratingValue) {
        this.ratingValue = ratingValue;
    }
    
    
   
}
