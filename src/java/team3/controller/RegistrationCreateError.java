/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.controller;

import java.io.Serializable;

/**
 *
 * @author MSI BH
 */
public class RegistrationCreateError implements Serializable{
    private String usernameLengthError;
    private String passwordLengError;
    private String confirmError;
    private String existedUsernameError;
    private String fullNameLengthError;
    private String phoneLengthError;
    private String emailError;
    private String existedEmailError;
    private String inexistingEmailError;
    
    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengError() {
        return passwordLengError;
    }

    public void setPasswordLengError(String passwordLengError) {
        this.passwordLengError = passwordLengError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getExistedUsernameError() {
        return existedUsernameError;
    }

    public void setExistedUsernameError(String existedUsernameError) {
        this.existedUsernameError = existedUsernameError;
    }

    public String getFullNameLengthError() {
        return fullNameLengthError;
    }

    public void setFullNameLengthError(String fullNameLengthError) {
        this.fullNameLengthError = fullNameLengthError;
    }

    public String getPhoneLengthError() {
        return phoneLengthError;
    }

    public void setPhoneLengthError(String phoneLengthError) {
        this.phoneLengthError = phoneLengthError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getExistedEmailError() {
        return existedEmailError;
    }

    public void setExistedEmailError(String existedEmailError) {
        this.existedEmailError = existedEmailError;
    }

    public String getInexistingEmailError() {
        return inexistingEmailError;
    }

    public void setInexistingEmailError(String inexistingEmailError) {
        this.inexistingEmailError = inexistingEmailError;
    }

    
}