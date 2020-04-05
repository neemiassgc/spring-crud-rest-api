package web.spring.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import web.spring.crud.controller.MainPageController;
import web.spring.crud.database.UserBind;

@Configuration
@EnableAutoConfiguration
@Import(value = {
		UserBind.class, MainPageController.class
})
public class SpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);
	}
}
