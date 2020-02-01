package com.mins.corona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//@formatter:off
@PropertySource(value = {
		"classpath:properties/common.properties"
}, encoding = "UTF-8")
//@formatter:on
@SpringBootApplication
public class CoronaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaApplication.class, args);
	}
}
