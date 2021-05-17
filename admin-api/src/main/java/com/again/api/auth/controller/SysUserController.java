package com.again.api.auth.controller;

import com.again.api.auth.model.entity.SysUser;
import com.again.api.auth.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SysUserController {

	private final SysUserService sysUserService;

	@GetMapping
	public SysUser loadUserByUsername(String username) {
		SysUser sysUser = sysUserService.getByUsername(username);
		if (sysUser == null) {
			log.error("登陆：用户名错误，用户名：{}", username);
			throw new RuntimeException("username error!");
		}
		return sysUser;
	}

}
