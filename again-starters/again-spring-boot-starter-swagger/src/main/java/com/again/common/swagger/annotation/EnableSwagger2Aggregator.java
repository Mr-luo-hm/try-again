package com.again.common.swagger.annotation;

import com.again.common.swagger.SwaggerAggregatorAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ SwaggerAggregatorAutoConfiguration.class })
public @interface EnableSwagger2Aggregator {

}
