package br.com.damatomos.currency_converter.infrastructure.mapper;

import br.com.damatomos.currency_converter.core.entity.Currency;
import br.com.damatomos.currency_converter.infrastructure.dto.ResponseCurrencyStatusDTO;

public class CurrencyMapper {

    public static Currency toEntity(ResponseCurrencyStatusDTO dto)
    {
        return new Currency(dto.baseCode(), dto.conversionRates());
    }

}
