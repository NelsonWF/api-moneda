package com.api.nawf.domain.entities;

public class CountryEntityMother {
    public static CountryEntity getCountry() {
        return new CountryEntity("CO", "Colombia", CurrencyEntityMother.getCurrency(), null);
    }
}
