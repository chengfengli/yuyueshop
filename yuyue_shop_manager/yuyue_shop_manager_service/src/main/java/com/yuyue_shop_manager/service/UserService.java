package com.yuyue_shop_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuyue_shop_common.utils.Message;
import com.yuyue_shop_manager.entity.User;
import com.yuyue_shop_manager_dao.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public Message getUserByAccount(String userAccount,String userPassword){
		Message msg = new Message();
		User user = userMapper.selectUserByAccount(userAccount);
		if(user != null){
			if(user.getUserpwd().equals(userPassword)){
				msg.isSuccess = true;
			}else{
				msg.isSuccess = false;
				msg.strMessage = "密码错误!";
				msg.errorType = "pwdError";
			}
		}else{
			msg.isSuccess = false;
			msg.strMessage = "账号不存在!";
			msg.errorType = "accountError";
		}
		return msg;
	}
}
