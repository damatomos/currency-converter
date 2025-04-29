package br.com.damatomos.currency_converter.core.entity;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class Currency {

    private String baseCode;
    private Map<String, BigDecimal> conversionRates;

    public Currency(String baseCode, Map<String, BigDecimal> conversionRates) {
        this.baseCode = baseCode;
        this.conversionRates = conversionRates;
    }

    public Currency() {
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
        return Objects.equals(baseCode, currency.baseCode) && Objects.equals(conversionRates, currency.conversionRates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseCode, conversionRates);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "baseCode='" + baseCode + '\'' +
                ", conversionRates=" + conversionRates +
                '}';
    }
}
