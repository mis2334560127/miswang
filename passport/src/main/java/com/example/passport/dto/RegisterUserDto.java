package com.example.passport.dto;

import com.example.passport.pojo.TbUser;

public class RegisterUserDto {
    private String username;

    private String password;

    private String phone;

    private String email;
//    验证码
    private String code;
//    重复密码
    private String rePassPort;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRePassPort() {
        return rePassPort;
    }

    public void setRePassPort(String rePassPort) {
        this.rePassPort = rePassPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
