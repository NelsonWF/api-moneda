package com.api.nawf.domain.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RateEntityMother {
    private static Date now = new Date();

    public static List<RateEntity> getRates() {
        List<RateEntity> rates = new ArrayList<>();
        rates.add(getRateEUR());
        rates.add(getRateUSD());
        return rates;
    }

    public static RateEntity getRateEUR() {
        return getRate("EUR", 1d);
    }

    public static RateEntity getRateUSD() {
        return getRate("USD", 3700d);
    }

    public static RateEntity getRate(String currency, Double value) {
        return new RateEntity("1", now, currency, value, CountryEntityMother.getCountry());
    }
}
