package br.com.damatomos.currency_converter.infrastructure.dto;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Map;

public record ResponseCurrencyStatusDTO(
    @SerializedName("result")
    String result,

    @SerializedName("base_code")
    String baseCode,

    @SerializedName("conversion_rates")
    Map<String, BigDecimal> conversionRates
) {
}
