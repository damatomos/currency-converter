package br.com.damatomos.currency_converter.infrastructure.mapper;

import br.com.damatomos.currency_converter.core.entity.Currency;
import br.com.damatomos.currency_converter.infrastructure.dto.ResponseCurrencyStatusDTO;
import br.com.damatomos.currency_converter.infrastructure.dto.ResponseSupportedCodesDTO;

import java.util.List;

public class CurrencyMapper {

    public static Currency toEntity(ResponseCurrencyStatusDTO dto)
    {
        return new Currency(dto.baseCode(), dto.conversionRates());
    }

    public static List<Currency> toEntity(ResponseSupportedCodesDTO dto)
    {
        return dto.supportedCodes().stream().map(
                values -> {
                    return new Currency(values.get(1), values.get(0));
                }
        ).toList();
    }

}
