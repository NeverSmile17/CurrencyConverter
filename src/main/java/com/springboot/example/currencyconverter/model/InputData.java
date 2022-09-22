package com.springboot.example.currencyconverter.model;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InputData {
    private String currencyFrom;
    private String currencyTo;
    @Min(value = 0)
    private Integer count;
}