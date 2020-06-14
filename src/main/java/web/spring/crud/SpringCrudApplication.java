package web.spring.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import web.spring.crud.controller.MainController;
import web.spring.crud.database.DataBind;

@Configuration
@EnableAutoConfiguration
@Import(value = {
		DataBind.class, MainController.class
})
public class SpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);
	}
}
