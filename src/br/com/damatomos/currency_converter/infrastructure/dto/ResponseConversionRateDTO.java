package br.com.damatomos.currency_converter.infrastructure.dto;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public record ResponseConversionRateDTO(
        @SerializedName("result")
        String result,
        @SerializedName("base_code")
        String baseCode,
        @SerializedName("target_code")
        String targetCode,
        @SerializedName("conversion_rate")
        BigDecimal conversionRate
) {
}
