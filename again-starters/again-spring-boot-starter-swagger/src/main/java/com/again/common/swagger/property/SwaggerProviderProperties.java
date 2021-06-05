package com.again.common.swagger.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Hccake
 * @version 1.0
 * @date 2019/11/1 20:05
 */
@Data
@Component
@ConfigurationProperties("swagger.provider")
public class SwaggerProviderProperties {

	/**
	 * 聚合者的来源，用于控制跨域放行
	 */
	private String aggregatorOrigin = "*";

}
