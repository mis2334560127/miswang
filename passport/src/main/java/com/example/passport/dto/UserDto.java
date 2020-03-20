package com.example.passport.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("用户接受参数")
public class UserDto{
    @ApiModelProperty(value="验证码",required=true)
    private String code;
    @ApiModelProperty(value="用户名",required=true)
    private String username;
    @ApiModelProperty(value="密码",required=true)
    private String password;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
