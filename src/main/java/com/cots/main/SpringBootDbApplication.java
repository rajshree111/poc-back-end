package com.cots.main;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"com.cots.model"})
@ComponentScan({"com.cots.controller","com.cots.dao"})
@EnableJpaRepositories("com.cots.repository")
public class SpringBootDbApplication {


	public static void main(String[] args) {
		//Thread t=new Thread(new CotsServer());
		//t.start();
		
		SpringApplication.run(SpringBootDbApplication.class, args);
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.secondDatasource")
	public DataSource secondaryDataSource() {
	    return DataSourceBuilder.create().build();
	}

}
