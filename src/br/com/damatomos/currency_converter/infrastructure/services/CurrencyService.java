package br.com.damatomos.currency_converter.infrastructure.services;

import br.com.damatomos.currency_converter.infrastructure.config.Configurations;
import br.com.damatomos.currency_converter.infrastructure.dto.ResponseCurrencyStatusDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class CurrencyService {

    private Gson gson;
    private final Configurations configurations;

    public CurrencyService(Configurations configurations)
    {
        this.configurations = configurations;
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public ResponseCurrencyStatusDTO getCurrency(String baseCode) throws IOException, InterruptedException {
        String response = WebService.get(
                String.format(
                        "https://v6.exchangerate-api.com/v6/%s/latest/%s",
                        this.configurations.getExchangeApiKey(),
                        baseCode
                ));

        return gson.fromJson(response, ResponseCurrencyStatusDTO.class);
    }

}
