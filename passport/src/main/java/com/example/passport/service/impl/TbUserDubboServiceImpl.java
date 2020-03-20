package com.example.passport.service.impl;

import com.example.passport.mapper.TbUserMapper;
import com.example.passport.pojo.TbUser;
import com.example.passport.pojo.TbUserExample;
import com.example.passport.service.TbUserDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tbUserDubboService")
public class TbUserDubboServiceImpl implements TbUserDubboService {
	@Autowired
	private TbUserMapper tbUserMapper;
	@Override
	public TbUser selByUser(TbUser user) {
		TbUserExample example = new TbUserExample();
		example.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
