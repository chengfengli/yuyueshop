package com.yuyue_shop_manager_dao.mapper;

import com.yuyue_shop_manager.entity.User;

public interface UserMapper {
	// 查询用户：登录
	public User selectUserByAccount(String userAccount);
}