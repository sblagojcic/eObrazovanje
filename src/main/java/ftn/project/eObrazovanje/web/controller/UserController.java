package ftn.project.eObrazovanje.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.eObrazovanje.model.User;
import ftn.project.eObrazovanje.service.UserService;
import ftn.project.eObrazovanje.web.dto.UserDTO;


@RestController
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    UserService userService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userService.findAll();
        List<UserDTO> usersDTO = new ArrayList<UserDTO>();
        for (User user : users) {
            usersDTO.add(new UserDTO(user));
        }
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);

    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setLastName(userDTO.getLastName());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setRole("user");
        user.setUserName(userDTO.getUserName());
        user = userService.save(user);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);

    }
    
    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        User user = userService.findOne(userDTO.getId());
        if (user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        user.setLastName(userDTO.getLastName());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setUserName(userDTO.getUserName());
        user = userService.save(user);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);

    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        User user = userService.findOne(id);
        if (user != null) {
            userService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

