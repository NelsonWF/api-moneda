package com.api.nawf.domain.models;

import java.util.Optional;

public class CurrenciesAPIModelMother {
    public static CurrenciesAPIModel getCurrenciesApi() {
        CurrencyAPIModel[] currencies = { getCurrencyApi() };
        return new CurrenciesAPIModel(currencies);
    }

    public static Optional<CurrencyAPIModel> getCurrencyApiOptional() {
        return Optional.of(getCurrencyApi());
    }

    public static CurrencyAPIModel getCurrencyApi() {
        return new CurrencyAPIModel("COP", "Colombia", "$");
    }
}
