package br.com.damatomos.currency_converter.infrastructure;

import br.com.damatomos.currency_converter.application.CurrencyConverter;
import br.com.damatomos.currency_converter.infrastructure.config.Configurations;
import br.com.damatomos.currency_converter.infrastructure.gui.ChallengeUserInterface;
import br.com.damatomos.currency_converter.infrastructure.services.CurrencyService;

import java.util.Scanner;

public class Application {

    public static void main(String[] args)
    {
        new ChallengeUserInterface(
                new Scanner(System.in),
                new CurrencyService(new Configurations()),
                new CurrencyConverter()
        ).run();

    }

}
