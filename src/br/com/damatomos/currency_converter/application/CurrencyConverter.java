package br.com.damatomos.currency_converter.application;

import java.math.BigDecimal;

public class CurrencyConverter {

    public CurrencyConverter() {}

    public BigDecimal convert(BigDecimal baseValue, BigDecimal conversionRate)
    {
        return baseValue.multiply(conversionRate);
    }

}
