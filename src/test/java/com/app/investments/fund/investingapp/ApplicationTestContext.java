package com.app.investments.fund.investingapp;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Krzysztof Lakomy
 */

@Configuration
@ComponentScan(basePackages =  {
		"com.app.investments.fund.investingapp.fund.entity.executor",
		"com.app.investments.fund.investingapp.fund.entity.service",
		"com.app.investments.fund.investingapp.fund.type.executor",
		"com.app.investments.fund.investingapp.fund.type.service",
		"com.app.investments.fund.investingapp.investition",
		"com.app.investments.fund.investingapp.investition_style",
		"com.app.investments.fund.investingapp.single_investition",
		"com.app.investments.fund.investingapp.fund.entity.model"
})

@EnableJpaRepositories(basePackages = {
		"com.app.investments.fund.investingapp.fund.entity.repository",
		"com.app.investments.fund.investingapp.fund.type.repository",
		"com.app.investments.fund.investingapp.investition.repository",
		"com.app.investments.fund.investingapp.investition_style.repository",
		"com.app.investments.fund.investingapp.single_investition.repository"
})

@EntityScan(basePackages = {
		"com.app.investments.fund.investingapp.fund.entity.model",
		"com.app.investments.fund.investingapp.fund.type.model",
		"com.app.investments.fund.investingapp.investition_test.model",
		"com.app.investments.fund.investingapp.investition_style.model",
		"com.app.investments.fund.investingapp.single_investition.model"
})
public class ApplicationTestContext {

}
