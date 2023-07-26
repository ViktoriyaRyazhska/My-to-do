package com.softserve.itacademy.component.login.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class RestAuthRequestDto {

    private String username;
    private String password;

}
