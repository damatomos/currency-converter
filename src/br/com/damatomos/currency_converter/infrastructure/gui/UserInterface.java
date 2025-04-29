package br.com.damatomos.currency_converter.infrastructure.gui;

import br.com.damatomos.currency_converter.application.CurrencyConverter;
import br.com.damatomos.currency_converter.core.entity.Currency;
import br.com.damatomos.currency_converter.infrastructure.services.CurrencyService;

import java.util.List;

public abstract class UserInterface {
    protected final CurrencyService currencyService;
    protected final CurrencyConverter converter;

    public UserInterface(CurrencyService currencyService, CurrencyConverter converter)
    {
        this.currencyService = currencyService;
        this.converter = converter;
    }

    public abstract void run();
}
