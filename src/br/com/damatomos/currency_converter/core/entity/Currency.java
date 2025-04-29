package br.com.damatomos.currency_converter.core.entity;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class Currency {

    private String name;
    private String country;
    private String baseCode;
    private Map<String, BigDecimal> conversionRates;

    public Currency(String name, String country, String baseCode, Map<String, BigDecimal> conversionRates) {
        this.name = name;
        this.country = country;
        this.baseCode = baseCode;
        this.conversionRates = conversionRates;
    }

    public Currency(String baseCode, Map<String, BigDecimal> conversionRates) {
        this.baseCode = baseCode;
        this.conversionRates = conversionRates;
    }

    public Currency(String name, String baseCode) {
        this.name = name;
        this.baseCode = baseCode;
    }

    public Currency() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public Map<String, BigDecimal> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, BigDecimal> conversionRates) {
        this.conversionRates = conversionRates;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(name, currency.name) && Objects.equals(country, currency.country) && Objects.equals(baseCode, currency.baseCode) && Objects.equals(conversionRates, currency.conversionRates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, baseCode, conversionRates);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", baseCode='" + baseCode + '\'' +
                ", conversionRates=" + conversionRates +
                '}';
    }
}
