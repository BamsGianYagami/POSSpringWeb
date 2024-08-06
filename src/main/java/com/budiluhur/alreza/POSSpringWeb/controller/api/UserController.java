package com.budiluhur.alreza.POSSpringWeb.controller.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.budiluhur.alreza.POSSpringWeb.Entity.UserInfo;
import com.budiluhur.alreza.POSSpringWeb.services.UserInfoService;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;


@RestController
@RequestMapping(path="api/user")
@PermitAll
public class UserController {

    public static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserInfoService userInfoService;

    @GetMapping
    @PermitAll
    public List<UserInfo> getAllUser(){
        return userInfoService.getAllUser();
    }
    
    @GetMapping("{username}")
    @PermitAll
    public List<UserInfo> getUser(@PathVariable String username){
        return userInfoService.getUser(username);
    }

    @PostMapping
    @RolesAllowed("Admin")
    public ResponseEntity<?> addUser(@RequestBody UserInfo userInfo){
        String info = userInfoService.addUser(userInfo);
        log.info(info);
        return ResponseEntity.ok(info);
    }

    @PutMapping
    @RolesAllowed("Admin")
    public ResponseEntity<?> updateUser(@RequestBody UserInfo userInfo){
        String info = userInfoService.updateUser(userInfo);
        return ResponseEntity.ok(info);
    }

    @DeleteMapping("{id}")
    @RolesAllowed("Admin")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        String info = userInfoService.deleteUser(id);
        return ResponseEntity.ok(info);
    }
}
