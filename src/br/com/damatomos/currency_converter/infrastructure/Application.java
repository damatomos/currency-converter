package br.com.damatomos.currency_converter.infrastructure;

import br.com.damatomos.currency_converter.application.CurrencyConverter;
import br.com.damatomos.currency_converter.infrastructure.config.Configurations;
import br.com.damatomos.currency_converter.infrastructure.gui.ChallengeUserInterface;
import br.com.damatomos.currency_converter.infrastructure.services.CurrencyService;

import java.util.Scanner;

public class Application {

    public static void main(String[] args)
    {
        Configurations config = new Configurations();

        CurrencyService currencyService = new CurrencyService(config);

        ChallengeUserInterface challengeGUI = new ChallengeUserInterface(
                new Scanner(System.in),
                currencyService,
                new CurrencyConverter()
        );

        challengeGUI.run();

    }

}
