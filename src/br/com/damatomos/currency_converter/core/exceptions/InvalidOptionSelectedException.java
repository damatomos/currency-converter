package br.com.damatomos.currency_converter.core.exceptions;

public class InvalidOptionSelectedException extends RuntimeException {
    public InvalidOptionSelectedException(String message) {
        super(message);
    }
}
