package br.com.damatomos.currency_converter.infrastructure.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Configurations {

    private Dotenv dotenv;
    private String exchangeApiKey;

    public Configurations()
    {
        dotenv = Dotenv.load();

        initialize();
    }

    private void initialize()
    {
        exchangeApiKey = dotenv.get("EXCHANGE_RATE_API_KEY");
    }

    public String getExchangeApiKey() {
        return exchangeApiKey;
    }

    public Dotenv getDotenv() {
        return dotenv;
    }
}
