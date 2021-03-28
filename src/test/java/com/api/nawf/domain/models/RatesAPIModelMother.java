package com.api.nawf.domain.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api.nawf.domain.entities.CountryEntity;
import com.api.nawf.domain.entities.RateEntity;

public class RatesAPIModelMother {
    private static long timestamp = 1616950202L;
    public static Date now = new Timestamp(timestamp * 1000L);

    public static List<RateAPIModel> getRates() {
        List<RateAPIModel> rates = new ArrayList<>();
        rates.add(getRateEUR());
        rates.add(getRateUSD());
        return rates;
    }

    public static List<RateEntity> getRatesEntity(CountryEntity country) {
        List<RateEntity> rates = new ArrayList<>();
        rates.add(setCountry(getRateEUR().toRateEntity(), country));
        rates.add(setCountry(getRateUSD().toRateEntity(), country));
        return rates;
    }

    public static RateEntity setCountry(RateEntity rate, CountryEntity country) {
        rate.setCountry(country);
        return rate;
    }

    public static RateAPIModel getRateEUR() {
        return getRate("EUR", 1d);
    }

    public static RateAPIModel getRateUSD() {
        return getRate("USD", 3700d);
    }

    public static RateAPIModel getRate(String currency, Double value) {
        return new RateAPIModel(now, value, currency);
    }

    public static Map<String, Object> getSuccessResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", String.valueOf(timestamp));
        response.put("rates", getRatesResponse());
        return response;
    }

    public static Map<String, Object> getRatesResponse() {
        Map<String, Object> rates = new HashMap<>();
        rates.put("EUR", "1");
        rates.put("USD", "3700");
        return rates;
    }

    public static Map<String, Object> getErrorResponse() {
        Map<String, Object> error = new HashMap<>();
        error.put("type", "backend_error");
        error.put("code", "101");
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", String.valueOf(timestamp));
        response.put("error", error);
        return response;
    }
}
