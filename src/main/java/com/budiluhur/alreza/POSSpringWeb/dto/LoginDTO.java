package com.budiluhur.alreza.POSSpringWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    
    String username;
    String password;
    boolean loginFailed;
}
