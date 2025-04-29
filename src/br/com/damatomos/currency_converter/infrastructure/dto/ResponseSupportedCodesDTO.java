package br.com.damatomos.currency_converter.infrastructure.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record ResponseSupportedCodesDTO(
        @SerializedName("result")
        String result,

        @SerializedName("supported_codes")
        List<List<String>> supportedCodes
) {
}
