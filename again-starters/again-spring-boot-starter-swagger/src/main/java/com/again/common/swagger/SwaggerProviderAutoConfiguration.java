package com.again.common.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;

/**
 * @author Hccake
 * @version 1.0
 * @date 2019/11/1 20:03
 */
@Import(SwaggerConfiguration.class)
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerProviderAutoConfiguration {

	/**
	 * 允许swagger文档跨域访问 解决聚合文档导致的跨域问题
	 * @return FilterRegistrationBean
	 */
	// @Bean
	// public FilterRegistrationBean<CorsFilter> corsFilter() {
	// UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	// CorsConfiguration config = new CorsConfiguration();
	// config.setAllowCredentials(true);
	//
	// config.addAllowedHeader("*");
	// config.addAllowedMethod("*");
	// config.setAllowedOriginPatterns(Collections.singletonList("*"));
	// /* 允许访问的方法名,GET POST等 */
	// config.addAllowedOriginPattern("*");
	// // config.addAllowedOrigin(swaggerProviderProperties.getAggregatorOrigin());
	// source.registerCorsConfiguration("/**", config);
	// FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new
	// CorsFilter(source));
	// bean.setOrder(0);
	// return bean;
	// }

}
