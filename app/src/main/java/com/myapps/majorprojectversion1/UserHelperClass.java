package com.myapps.majorprojectversion1;

public class UserHelperClass {
    String email, password, phone, username;

    public UserHelperClass(String email, String username, String password, String phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.username = username;
    }

    public UserHelperClass() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
