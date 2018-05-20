package com.daria.university.diploma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
/*@EnableJpaRepositories("com.daria.university.diploma.*")
@ComponentScan(basePackages = { "com.daria.university.diploma.*" })
@EntityScan("com.daria.university.diploma.model.*")*/
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringSocketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSocketsApplication.class, args);
	}
}
