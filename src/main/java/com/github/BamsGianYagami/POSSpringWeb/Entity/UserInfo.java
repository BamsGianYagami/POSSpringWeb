package com.github.BamsGianYagami.POSSpringWeb.Entity;

import jakarta.persistence.Entity; 
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.NoArgsConstructor; 
  
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_info")
public class UserInfo {
    
    @Id
    private String username;
    private String name; 
    private String email; 
    private String password; 
    private String roles;
}
