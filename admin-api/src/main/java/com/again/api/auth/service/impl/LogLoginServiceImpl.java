package com.again.api.auth.service.impl;

import com.again.api.auth.mapper.LogLoginMapper;
import com.again.api.auth.model.entity.LogLogin;
import com.again.api.auth.service.LogLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogLoginServiceImpl extends ServiceImpl<LogLoginMapper, LogLogin> implements LogLoginService {

}
