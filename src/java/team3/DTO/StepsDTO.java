/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.DTO;

/**
 *
 * @author AS
 */
public class StepsDTO {
    private int stepID;
    private String descriptionName;
    private int recipeID;

    public StepsDTO() {
    }

    public StepsDTO(int stepID, String descriptionName, int recipeID) {
        this.stepID = stepID;
        this.descriptionName = descriptionName;
        this.recipeID = recipeID;
    }

    public int getStepID() {
        return stepID;
    }

    public void setStepID(int stepID) {
        this.stepID = stepID;
    }

    public String getDescriptionName() {
        return descriptionName;
    }

    public void setDescriptionName(String descriptionName) {
        this.descriptionName = descriptionName;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    @Override
    public String toString() {
        return "StepsDTO{" + "stepID=" + stepID + ", descriptionName=" + descriptionName + ", recipeID=" + recipeID + '}';
    }
    
}
