package com.softserve.itacademy.component.state;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateDto {

    private Long id;
    private String name;
}
