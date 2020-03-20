package com.example.passport.service;

import com.example.passport.pojo.TbUser;

public interface TbUserDubboService {
	/**
	 * 根据用户名和密码查询登录
	 * @param user
	 * @return
	 */
	TbUser selByUser(TbUser user);
}
