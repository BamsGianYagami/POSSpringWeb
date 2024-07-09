package com.github.BamsGianYagami.POSSpringWeb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.NoArgsConstructor; 
  
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_info")
@SequenceGenerator(name = "userInfoIdGenerator", sequenceName = "user_info_sequence", allocationSize = 1)
public class UserInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userInfoIdGenerator")
    private Integer id;
    private String username;
    private String name; 
    private String email; 
    private String password; 
    private String roles;
}
