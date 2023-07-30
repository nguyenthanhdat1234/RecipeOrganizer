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
public class ShowRatingDTO implements Serializable {
    private int recipeID;
    private int ratingCount;
    private float  averageRating;

    public ShowRatingDTO() {
    }

    public ShowRatingDTO(int recipeID, int ratingCount, float averageRating) {
        this.recipeID = recipeID;
        this.ratingCount = ratingCount;
        this.averageRating = averageRating;
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
     * @return the ratingCount
     */
    public int getRatingCount() {
        return ratingCount;
    }

    /**
     * @param ratingCount the ratingCount to set
     */
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    /**
     * @return the averageRating
     */
    public float getAverageRating() {
        return averageRating;
    }

    /**
     * @param averageRating the averageRating to set
     */
    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }
    

}