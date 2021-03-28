package com.api.nawf.domain.models;

import com.api.nawf.domain.entities.CurrencyEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyAPIModel {
    private String code;
    private String name;
    private String symbol;
    
    public CurrencyEntity toCurrency() {
    	return CurrencyEntity.builder().code(code).name(name).symbol(symbol).build();
    }
}
