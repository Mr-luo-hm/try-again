package com.again.api.auth.queue;

import com.again.api.auth.model.entity.LogLogin;
import com.again.api.auth.service.LogLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogLoginQueue extends AbstractQueueThread<LogLogin> {

	private final LogLoginService logLoginService;

	@Override
	public void startLog() {
		log.info("登录日志线程已启动!");
	}

	@Override
	public void errorLog(Throwable e, List<LogLogin> list) {
		log.error("添加登录日志错误, [msg]:{}, [data]:{}", e.getMessage(), list);
	}

	@Override
	public void save(List<LogLogin> list) throws Exception {
		logLoginService.saveBatch(list);
	}

}
