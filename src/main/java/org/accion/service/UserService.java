package org.accion.service;

import java.util.List;

import org.accion.entity.UserDetails;
import org.accion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userResp;

	
	public List<UserDetails> findAll(){
		return userResp.findAll();
	}
	
	 public UserDetails save(UserDetails user){
		 return userResp.save(user);
	 }
	public void delete(UserDetails user){
		userResp.delete(user);
	}
	
	
	public UserDetails findByFullName(String fullName){
		return userResp.findByFullName(fullName);
	}
	

}
