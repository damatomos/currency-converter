package br.com.damatomos.currency_converter.application;

import br.com.damatomos.currency_converter.core.entity.Currency;

import java.math.BigDecimal;

public class CurrencyConverter {

    public CurrencyConverter() {}

    public BigDecimal convert(BigDecimal baseValue, BigDecimal conversionRate)
    {
        return baseValue.multiply(conversionRate);
    }

}
