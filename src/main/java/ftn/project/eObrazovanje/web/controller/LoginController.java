package ftn.project.eObrazovanje.web.controller;

import java.security.Principal;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RestController
@RequestMapping("api/user")
@EnableResourceServer
public class LoginController extends WebMvcConfigurerAdapter{
	
	
	@RequestMapping(method = RequestMethod.GET)
	public Principal user(Principal user){
		return user;
	}
	
}

