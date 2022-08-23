package com.sist.spring2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//5¹öÀü
@Configuration
public class ModelConfig {
	@Bean("a")
	public AModel aModel() {
		return new AModel();
	}
	@Bean("b")
	public BModel bModel() {
		return new BModel();
	}
	@Bean("c")
	public CModel cModel() {
		return new CModel();
	}
}
