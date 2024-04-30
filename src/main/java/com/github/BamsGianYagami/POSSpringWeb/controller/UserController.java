package com.github.BamsGianYagami.POSSpringWeb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.BamsGianYagami.POSSpringWeb.Entity.UserInfo;
import com.github.BamsGianYagami.POSSpringWeb.services.UserInfoService;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;


@RestController
@RequestMapping(path="user")
@PermitAll
public class UserController {

    public static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserInfoService userInfoService;
    
    @GetMapping
    @PermitAll
    public List<UserInfo> getUser(@RequestBody UserInfo userInfo){
        String username = userInfo.getName();
        return username.isEmpty() ? userInfoService.getAllUser() : userInfoService.getUser(username);
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

    @DeleteMapping
    @RolesAllowed("Admin")
    public ResponseEntity<?> deleteUser(@RequestBody UserInfo userInfo){
        String info = userInfoService.deleteUser(userInfo.getId());
        return ResponseEntity.ok(info);
    }
}
