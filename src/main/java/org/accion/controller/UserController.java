package org.accion.controller;

import java.util.Arrays;
import java.util.List;

import org.accion.entity.UserDetails;
import org.accion.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/user")
public class UserController {
	
	final static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService us;
	

	
	@RequestMapping(value="/addUser",method=RequestMethod.POST,headers = {"content-type=application/json"})
	public ResponseEntity<UserDetails> add(@RequestBody UserDetails user){
			us.save(user);
			logger.debug("Added: " + user);
			return new ResponseEntity<UserDetails>(user,HttpStatus.CREATED);
			
		}
	
	
	
	@PutMapping("/updateUser/{fullName}")
	//@RequestMapping(value="/updateByName/{name}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Void> updateUserByName(@RequestBody UserDetails user,@PathVariable("fullName")String fullName){
	UserDetails user1=us.findByFullName(fullName);
	if(user1== null){
		logger.debug("User with name " + user.getFullName() + " does not exists");
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	else{
		us.save(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	}
	
	@GetMapping("/getByName/{fullName}")
	//@RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<UserDetails> getUserByName(@PathVariable("fullName") String fullName) {
		UserDetails usr = us.findByFullName(fullName);
		if (usr == null) {
			logger.debug("User with name " + fullName + " does not exists");
			return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found User:: " + usr);
		return new ResponseEntity<UserDetails>(usr, HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllUsers")
	//@RequestMapping(value = "/getAllVendors",method = RequestMethod.GET)
	public ResponseEntity<List<UserDetails>> getAllUsers() {
		List<UserDetails> users = us.findAll();
		if (users.isEmpty()) {
			logger.debug("Users does not exists");
			return new ResponseEntity<List<UserDetails>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + users.size() + " users");
		logger.debug(users);
		logger.debug(Arrays.toString(users.toArray()));
		return new ResponseEntity<List<UserDetails>>(users, HttpStatus.OK);
	}

	@DeleteMapping("/deleteByName/{fullName}")
	//@RequestMapping(value = "/deleteByName/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("fullName") String fullName) {
		UserDetails user = us.findByFullName(fullName);
		if (user == null) {
			logger.debug("User with name " + fullName + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			us.delete(user);
			logger.debug("User with name" + fullName + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

}
