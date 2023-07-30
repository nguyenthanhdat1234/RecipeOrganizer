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
public class AccountDTO {
    private int userID;
    private String userName;
    private String password;
    private String fullName;
    private String phone;
    private int status;
    private boolean role;
    private String token;
    private String email;

    public AccountDTO() {
    }

    public AccountDTO(int userID, String userName, String password, String fullName, String phone, int status, boolean role, String token, String email) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.status = status;
        this.role = role;
        this.token = token;
        this.email = email;
    }

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

    @Override
    public String toString() {
        return "AccountDTO{" + "userID=" + userID + ", userName=" + userName + ", password=" + password + ", fullName=" + fullName + ", phone=" + phone + ", status=" + status + ", role=" + role + ", token=" + token + ", email=" + email + '}';
    }
    
}
