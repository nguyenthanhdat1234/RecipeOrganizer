/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.DTO;

/**
 *
 * @author THIS PC
 */
public class FavoriteDTO {
    private int recipeID;
    private String recipeName;
    private String imgUrl;

    public FavoriteDTO() {
    }

    public FavoriteDTO(int recipeID, String recipeName, String imgUrl) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.imgUrl = imgUrl;
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
     * @return the recipeName
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * @param recipeName the recipeName to set
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    /**
     * @return the imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl the imgUrl to set
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    
}
