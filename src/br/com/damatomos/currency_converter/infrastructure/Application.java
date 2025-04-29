package br.com.damatomos.currency_converter.infrastructure;

import br.com.damatomos.currency_converter.application.CurrencyConverter;
import br.com.damatomos.currency_converter.infrastructure.config.Configurations;
import br.com.damatomos.currency_converter.infrastructure.gui.ChallengeUserInterface;
import br.com.damatomos.currency_converter.infrastructure.services.CurrencyService;

import java.util.Scanner;

public class Application {

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        var gui =
                """
                Escolha entre as interfaces:
                1 - Console Challenge Mode
                2 - Advanced Mode
                (Outra opção para fechar)
                """;

        System.out.println(gui);

        Integer option = in.nextInt();

        if (option.equals(1))
        {
            new ChallengeUserInterface(
                    in,
                    new CurrencyService(new Configurations()),
                    new CurrencyConverter()
            ).run();
        } else if (option.equals(2))
        {
            // AdvanceMode
        } else {
            System.out.println("Aplicação finalizada");
        }


    }

}
