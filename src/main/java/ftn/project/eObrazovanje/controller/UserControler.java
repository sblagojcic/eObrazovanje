package ftn.project.eObrazovanje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.eObrazovanje.service.UserService;

@RestController
@RequestMapping(value="api/users")
public class UserControler {

	@Autowired
	UserService userService;
}
