package com.github.BamsGianYagami.POSSpringWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.github.BamsGianYagami.POSSpringWeb.Entity.UserInfo;

import java.util.Optional; 
  
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> { 
    Optional<UserInfo> findByUsername(String username); 
}