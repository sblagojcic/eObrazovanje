package ftn.project.eObrazovanje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAutoConfiguration
@EnableResourceServer
public class EObrazovanjeApplication extends WebMvcConfigurerAdapter  {

	public static void main(String[] args) {
		SpringApplication.run(EObrazovanjeApplication.class, args);
	}
}
