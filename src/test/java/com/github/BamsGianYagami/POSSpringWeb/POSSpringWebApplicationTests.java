package com.github.BamsGianYagami.POSSpringWeb;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.BamsGianYagami.POSSpringWeb.Entity.UserInfo;
import com.github.BamsGianYagami.POSSpringWeb.repository.UserInfoRepository;

@SpringBootTest
class POSSpringWebApplicationTests {

	@Autowired
	UserInfoRepository userInfoRepo;

	@Test
	void contextLoads() {
	}

	@SuppressWarnings("null")
	@Test
	public void testPrint(){
		List<UserInfo> usersInfo = userInfoRepo.findAll();
		ObjectMapper o = new ObjectMapper();
		try{
			String s = o.writeValueAsString(usersInfo);
			System.out.println("size: "+usersInfo.size());
			System.out.println("test Print!!! \n "+s);
			Assert.isTrue(true, "testPrint");
		}catch(Exception e){
			Assert.isTrue(false, e.getMessage());
		}
		
	}
}
