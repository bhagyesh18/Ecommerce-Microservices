package com.ecommerce.users.usersmicroservice.rest;

import java.util.List;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.users.usersmicroservice.model.User;
import com.ecommerce.users.usersmicroservice.service.UserServices;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	UserServices userService;
	
	@RequestMapping(method = RequestMethod.GET,value="/users")
	public ResponseEntity<List<User>> getAllUser() {
		try {
			List<User> users=userService.getAllUser();
			if (users != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(users);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		try {
		User user=userService.getUser(id);
		if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/users")
	public String addUser(@RequestBody User user) {
		try {
					userService.addUser(user);
				return "Success";
			}catch (Exception e) {
				return "Error";
	    }
	}
	
	@RequestMapping(method = RequestMethod.PUT,value="/users")
	public String updateUser(@RequestBody User user) {
		try {
			userService.updateUser(user);
		    return "Success";
		}catch (Exception e) {
			return "Error";
    }
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value="/users/{id}")
	public String deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);;
		    return "Success";
		}catch (Exception e) {
			return "Error";
    }
	}

	
}
