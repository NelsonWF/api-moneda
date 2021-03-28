package com.api.nawf.domain.models;

import java.util.Optional;

public class CountryAPIModelMother {

    public static CountryAPIModel getCountryApi() {
        return getCountryApiOptional().get();
    }

    public static Optional<CountryAPIModel> getCountryApiOptional() {
        return Optional.of(new CountryAPIModel("CO", "COL", "Colombia", "Co"));
    }
}
