package br.com.damatomos.currency_converter.infrastructure.enums;

public enum CurrencyCodeEnum {
    USD("United State Dollar"),
    ARS("Argentine Peso"),
    BRL("Brazillian Real"),
    COP("Colombian Peso");

    private String value;

    CurrencyCodeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
