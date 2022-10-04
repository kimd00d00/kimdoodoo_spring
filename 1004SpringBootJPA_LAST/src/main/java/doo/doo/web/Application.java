package doo.doo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"doo.doo.web.controller","doo.doo.web.dao"})//메모리 할당 요청
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
