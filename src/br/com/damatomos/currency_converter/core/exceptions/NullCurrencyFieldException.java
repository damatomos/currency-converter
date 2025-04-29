package br.com.damatomos.currency_converter.core.exceptions;

public class NullCurrencyFieldException extends RuntimeException {
    public NullCurrencyFieldException(String message) {
        super(message);
    }
}
