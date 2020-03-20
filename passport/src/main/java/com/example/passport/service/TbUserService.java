package com.example.passport.service;

import com.example.base.response.ResponseModel;
import com.example.base.resultpojo.EgoResult;
import com.example.passport.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TbUserService {
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	ResponseModel login(TbUser user, HttpServletRequest request, HttpServletResponse response);
	/**
	 * 根据token查询用户信息
	 * @param token
	 * @return
	 */
	ResponseModel getUserInfoByToken(String token);

	/**
	 * 退出
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 */
	ResponseModel logout(String token, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 用户注册信息
	 * @param username
	 * @param password
	 * @param phoneNumber
	 * @param email
	 * @return
	 */
	ResponseModel registerUser(String username,String password,String phoneNumber,String email);
}
