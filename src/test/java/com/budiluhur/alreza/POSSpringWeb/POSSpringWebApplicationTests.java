package com.budiluhur.alreza.POSSpringWeb;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.budiluhur.alreza.POSSpringWeb.Entity.UserInfo;
import com.budiluhur.alreza.POSSpringWeb.repository.MasterTransactionRepository;
import com.budiluhur.alreza.POSSpringWeb.repository.ShoppingCartRepository;
import com.budiluhur.alreza.POSSpringWeb.repository.TransactionDetailRepository;
import com.budiluhur.alreza.POSSpringWeb.repository.UserInfoRepository;

@SpringBootTest
class POSSpringWebApplicationTests {

	@Autowired
	UserInfoRepository userInfoRepo;

	@Autowired
	ShoppingCartRepository cartRepo;

	@Autowired
    MasterTransactionRepository masterTransactionRepo;

	@Autowired
    TransactionDetailRepository transactionDetailRepository;

	@Test
	void contextLoads() {
	}

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


	@Test
	public void testCartRepo(){
		try{
			List<Integer> listInt = cartRepo.getListItemsByUsername("user");
			Assert.isTrue(true, "size of cart in userid 1: "+listInt.size());
		}catch(Exception e){
			Assert.isTrue(false, e.getMessage());
		}
	}

	@Test
	public void testReductionOperation(){
		System.out.println("testReductionOperation");
		try{
			float qty =5.0f;
			float reduction = 4.0f;
			qty -= reduction;
			Assert.isTrue(1.0f == qty,"qty is not 1.0f!");
		}catch(Exception e){
			Assert.isTrue(false, e.getMessage());
		}
	}
}
