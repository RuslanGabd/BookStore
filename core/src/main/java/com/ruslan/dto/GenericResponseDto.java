package com.ruslan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class GenericResponseDto {

    private String message;

    private Integer integer;

    public GenericResponseDto(String message) {
        this.message = message;
    }

    public GenericResponseDto(Integer integer) {
        this.integer = integer;
    }
}
