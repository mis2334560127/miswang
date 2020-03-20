package com.example.passport.service.impl;

import com.example.base.enums.VerifyCodeEnum;
import com.example.base.response.ResponseCode;
import com.example.base.response.ResponseModel;
import com.example.base.utils.AESUtils;
import com.example.base.utils.data.DateUtils;
import com.example.passport.mapper.TbUserMapper;
import com.example.passport.redis.RedisFactory;
import com.example.passport.pojo.TbUser;
import com.example.passport.service.TbUserDubboService;
import com.example.passport.service.TbUserService;
import com.example.passport.util.CookieUtils;
import com.example.passport.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service("tbUserService")
public class TbUserServiceImpl implements TbUserService {
	@Autowired
	private TbUserDubboService tbUserDubboServiceImpl;
	@Autowired
	private RedisFactory redisFactory;
	@Autowired
	private TbUserMapper tbUserMapper;

	@Override
	public ResponseModel login(TbUser user, HttpServletRequest request, HttpServletResponse response) {
		TbUser userSelect = tbUserDubboServiceImpl.selByUser(user);
		if(userSelect!=null){
			//当用户登录成功后把用户信息放入到redis中
			String key =VerifyCodeEnum.getCodeKey(1003) + "_USER:" +  UUID.randomUUID().toString();
			//redis中存储的有效时间
			redisFactory.setex(key ,60*60*24*7, JsonUtils.objectToJson(userSelect));
			//产生Cookie
			CookieUtils.setCookie(request, response, "TT_TOKEN",  key, 60*60*24*7);
//            登录成功，返回跳转的路径
			return ResponseModel.getModel(ResponseCode.SUCCESS);
		}else{
            return ResponseModel.getModel(ResponseCode.FAIL,"用户名或者密码错误");
		}
	}
	@Override
	public ResponseModel getUserInfoByToken(String token) {
//		根据token信息，获取用户json串
		String json = redisFactory.get(token);
		if(json!=null&&!json.equals("")){
//			将用户json串，转换为对象
			TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
			//可以把密码清空
			tbUser.setPassword(null);
			tbUser.setCreated(null);
			tbUser.setUpdated(null);
			return ResponseModel.getModel(ResponseCode.SUCCESS,tbUser);
		}else{
			return ResponseModel.getModel(ResponseCode.FAIL,"未登录");
		}
	}
	@Override
	public ResponseModel logout(String token, HttpServletRequest request, HttpServletResponse response) {
//		清除token缓存
		Long index = redisFactory.del(token);
//		清除cookie里面的token信息
		CookieUtils.deleteCookie(request, response, "TT_TOKEN");
		return ResponseModel.getModel(ResponseCode.SUCCESS,"退出登录");
	}
	//开启事务：读写事务（可以避免脏读），propagation事务的传播行为：支持当前事务，如果当前没有事务，就新建一个事务
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel registerUser(String username, String password, String phoneNumber, String email) {
//		加密后的密码
		String aesPassword = AESUtils.AESEncode(AESUtils.KEY, password);
		TbUser tbUser = new TbUser(username,aesPassword,phoneNumber,email,DateUtils.getCurrDateTime(),DateUtils.getCurrDateTime());
		int insert = tbUserMapper.insert(tbUser);
		if (insert > 0){
			return ResponseModel.getModel(ResponseCode.SUCCESS,"用户注册成功");
		}else {
			return ResponseModel.getModel(ResponseCode.FAIL,"用户注册失败");
		}
	}

}
