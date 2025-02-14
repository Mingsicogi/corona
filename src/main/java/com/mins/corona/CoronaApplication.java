package com.mins.corona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@formatter:off
@PropertySource(value = {
		"classpath:properties/global-common.properties",
		"classpath:common.properties"
}, encoding = "UTF-8")
//@formatter:on
@EnableJpaAuditing
@SpringBootApplication
public class CoronaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaApplication.class, args);
	}
}
