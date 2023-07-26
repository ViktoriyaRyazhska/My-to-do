package com.softserve.itacademy.component.login.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestAuthResponseDto {

    private String token;

}
