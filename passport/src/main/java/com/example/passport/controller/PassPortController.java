package com.example.passport.controller;

import com.example.base.enums.VerifyCodeEnum;
import com.example.base.response.ResponseCode;
import com.example.base.response.ResponseModel;
import com.example.base.resultpojo.EgoResult;
import com.example.base.springmvc.HttpParameterParser;
import com.example.base.utils.AESUtils;
import com.example.base.utils.RandomUtils;
import com.example.base.utils.StringUtils;
import com.example.base.utils.VerifyCodeUtils;
import com.example.passport.dto.RegisterUserDto;
import com.example.passport.dto.UserDto;
import com.example.passport.pojo.TbUser;
import com.example.passport.redis.RedisFactory;
import com.example.passport.service.TbUserService;
import com.example.passport.util.CookieUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@RestController
@RequestMapping("/apis/user")
@Api(value="user", description="用户登录", position = 2)
public class PassPortController {
	@Autowired
	private TbUserService tbUserServiceImpl;
	@Autowired
	private RedisFactory redisFactory;

	@ApiOperation(value = "user：显示登录页面", notes = "user：显示登录页面", position = 5)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "currentUrl", value = "当前路径", required = true, paramType = "query", dataType = "string"),
	})
	@GetMapping("/showLogin")
	public String showLogin( Model model, String currentUrl){
		if(currentUrl!=null&&!currentUrl.equals("")){
			model.addAttribute("redirect", currentUrl);
		}else {
			model.addAttribute("redirect", "主页路径");
		}
		return "loginSSO";
	}

	/**
	 * 登录
	 * @return
	 */
	@ApiOperation(value = "user：登录", notes = "user：登录", position = 5)
	@ApiImplicitParams({
	})
	@PostMapping("/login")
	public ResponseModel login(@RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response){
		System.out.println("测试："+userDto.getUsername());
		if(StringUtils.isEmpty(userDto.getUsername())){
			return ResponseModel.getModel(ResponseCode.FAIL,"用户名不能为空");
		}
		if(StringUtils.isEmpty(userDto.getPassword())){
			return ResponseModel.getModel(ResponseCode.FAIL,"密码不能为空");
		}
		if(StringUtils.isEmpty(userDto.getCode())){
			return ResponseModel.getModel(ResponseCode.FAIL,"验证码不能为空");
		}
		String redisCaptcha = redisFactory.get(VerifyCodeEnum.getCodeKey(1002) + "_USER:" + userDto.getUsername());
		if (StringUtils.isNullOrEmpty(redisCaptcha)) {
			return ResponseModel.getModel(ResponseCode.FAIL, "请先获取图形验证码");
		}
		if (!userDto.getCode().toLowerCase().equals(redisCaptcha.toLowerCase())) {
			return ResponseModel.getModel(ResponseCode.FAIL, "输入的图形验证码不正确");
		}
		String s = AESUtils.AESEncode(AESUtils.KEY, userDto.getPassword());
		TbUser user = new TbUser();
		user.setUsername(userDto.getUsername());
		user.setPassword(s);
		return tbUserServiceImpl.login(user,request,response);
	}

	@ApiOperation(value = "user：获取用户信息", notes = "user：获取用户信息", position = 5)
	@ApiImplicitParams({
	})
	@GetMapping("/getUserInfo/{token}")
	public ResponseModel getUserInfo(@PathVariable String token){
		return tbUserServiceImpl.getUserInfoByToken(token);
	}

	@ApiOperation(value = "oos：用户注册", notes = "oos：用户注册", position = 5)
	@PostMapping("/register")
	public ResponseModel register(@RequestBody RegisterUserDto registerUserDto,HttpServletRequest request ){
		if(StringUtils.isEmpty(registerUserDto.getUsername())){
			return ResponseModel.getModel(ResponseCode.FAIL,"用户名不能为空");
		}
		if(StringUtils.isEmpty(registerUserDto.getPassword())){
			return ResponseModel.getModel(ResponseCode.FAIL,"密码不能为空");
		}
		if(StringUtils.isEmpty(registerUserDto.getRePassPort())){
			return ResponseModel.getModel(ResponseCode.FAIL,"重复密码不能为空");
		}
		if (!registerUserDto.getPassword().equals(registerUserDto.getRePassPort())) {
			return ResponseModel.getModel(ResponseCode.FAIL, "请确认两次密码相同");
		}
		if(StringUtils.isEmpty(registerUserDto.getPhone())){
			return ResponseModel.getModel(ResponseCode.FAIL,"电话号码不能为空");
		}
		// 验证手机号码  合法
		if (!VerifyCodeUtils.checkTeleTrue(registerUserDto.getPhone())) { //不是正确的手机号
			return ResponseModel.getModel(ResponseCode.FAIL,"手机号格式错误");
		}
		if(StringUtils.isEmpty(registerUserDto.getEmail())){
			return ResponseModel.getModel(ResponseCode.FAIL,"邮箱不能为空");
		}
		if(StringUtils.isEmpty(registerUserDto.getCode())){
			return ResponseModel.getModel(ResponseCode.FAIL,"验证码不能为空");
		}
		String redisCaptcha = redisFactory.get(VerifyCodeEnum.getCodeKey(1001) + "_USER:" + registerUserDto.getUsername());
		if (StringUtils.isNullOrEmpty(redisCaptcha)) {
			return ResponseModel.getModel(ResponseCode.FAIL, "请先获取图形验证码");
		}
		if (!registerUserDto.getCode().toLowerCase().equals(redisCaptcha.toLowerCase())) {
			return ResponseModel.getModel(ResponseCode.FAIL, "输入的图形验证码不正确");
		}
		return tbUserServiceImpl.registerUser(registerUserDto.getUsername(),registerUserDto.getPassword(),registerUserDto.getPhone(),registerUserDto.getEmail());
	}

	@ApiOperation(value = "user：根据token退出登录", notes = "user：根据token退出登录", position = 5)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userName", value = "用户", required = true, paramType = "query", dataType = "string"),
	})
	@GetMapping("/logout/{token}")
	public ResponseModel logout(@PathVariable String token, HttpServletRequest request, HttpServletResponse response){
		return tbUserServiceImpl.logout(token,request,response);
	}

	/**
	 * 点击获取图形验证码
	 */
	@ApiOperation(value = "点击获取图形验证码", notes = "点击获取图形验证码", position = 5)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userName", value = "用户名", required = true, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "type", value = "1001 注册图片验证码,1002 登录图片验证码,2001 找回图片验证码,4001 修改图片验证码",
					required = true, paramType = "query", dataType = "string"),
	})
	@GetMapping(value = "/getCaptcha")
	public void getCaptcha(@RequestParam String userName,  @RequestParam String type, HttpServletResponse response) {

		if (StringUtils.isNullOrEmpty(type)) { // 空 或 不为1  或 不为2
			return ;
		}
		if (StringUtils.isNullOrEmpty(userName)) { // 为空
			return ;
		}
		String codeKey = VerifyCodeEnum.getCodeKey(Integer.parseInt(type));

		String code = RandomUtils.randomStr(4);

		response.addHeader("Content-Type", "image/jpeg;charset=UTF-8");
		try {
			OutputStream os = response.getOutputStream();
			// 获取图片验证
			VerifyCodeUtils.outputImage(100, 50, os, code);
		} catch (Exception e) {
			//log.info("获取OutputStream 异常" + e);
			e.printStackTrace();
		}
		// 存入 账号对应的 验证码  有效期5分钟
		redisFactory.setex(codeKey + "_USER:"+userName, 60 * 5, code);
	}
}
