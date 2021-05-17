package com.again.api.auth.service;

import com.again.api.auth.model.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysUserService extends IService<SysUser> {

	/**
	 * 根据用户名查询用户
	 * @param username 用户名
	 * @return SysUser- q
	 */
	SysUser getByUsername(String username);

}
