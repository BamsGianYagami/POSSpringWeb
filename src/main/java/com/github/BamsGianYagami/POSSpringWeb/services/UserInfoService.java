package com.github.BamsGianYagami.POSSpringWeb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.BamsGianYagami.POSSpringWeb.Entity.UserInfo;
import com.github.BamsGianYagami.POSSpringWeb.repository.UserInfoRepository;

import java.util.List;
import java.util.Optional; 

@Service
public class UserInfoService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserInfoService.class);

	@Autowired
	private UserInfoRepository repository; 

	@Autowired
	private PasswordEncoder encoder; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserInfo> listUsers =  repository.findAll();
		try{
			log.info("print list users [{}]", listUsers.size());
			for(UserInfo user : listUsers){
				log.info(new ObjectMapper().writeValueAsString(user));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		

		Optional<UserInfo> userDetail = repository.findByName(username); 

		// Converting userDetail to UserDetails 
		return userDetail.map(UserInfoDetails::new) 
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username)); 
	}

	public List<UserInfo> getAllUser(){
		return repository.findAll();
	}

	public List<UserInfo> getUser(String username){
		return repository.findByName(username).stream().toList();
	}

	public String addUser(UserInfo userInfo) { 
		log.info("tambah User!");
		userInfo.setPassword(encoder.encode(userInfo.getPassword())); 
		repository.save(userInfo); 
		return "User Added Successfully"; 
	}

	public String updateUser(UserInfo newUserData) {
		log.info("update data user: +"+newUserData.getId());
		Optional<UserInfo> existingUser = repository.findById(newUserData.getId());
		if(existingUser.isPresent()){
			UserInfo usr = existingUser.get();
			usr.setName(newUserData.getName());
			usr.setEmail(newUserData.getEmail());
			if((newUserData.getPassword()!=null)){
				usr.setPassword(encoder.encode(newUserData.getPassword())); 
			}
			if((newUserData.getRoles()!=null)){
				usr.setPassword(newUserData.getRoles());
			}
			repository.saveAndFlush(newUserData);
			return "User "+newUserData.getId() +": "+newUserData.getName()+" Updated Successfully";
		}
		return "user "+newUserData.getId() +": "+newUserData.getName()+" does not exist!";
	}

	public String deleteUser(Integer id){
		log.info("delete data user: {}", id);
		repository.deleteById(id);
		return "userId "+id+" has been deleted!";
	}
}