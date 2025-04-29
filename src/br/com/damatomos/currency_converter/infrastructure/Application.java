package br.com.damatomos.currency_converter.infrastructure;

import br.com.damatomos.currency_converter.infrastructure.config.Configurations;
import br.com.damatomos.currency_converter.infrastructure.dto.ResponseCurrencyStatusDTO;
import br.com.damatomos.currency_converter.infrastructure.mapper.CurrencyMapper;
import br.com.damatomos.currency_converter.infrastructure.services.CurrencyService;

import java.io.IOException;

public class Application {

    public static void main(String[] args)
    {
        Configurations config = new Configurations();

        CurrencyService currencyService = new CurrencyService(config);

        try {
            ResponseCurrencyStatusDTO dto = currencyService.getCurrency("USD");
            System.out.println(CurrencyMapper.toEntity(dto));
        } catch (IOException e)
        {
            System.out.println("Erro durante a operação GET");
        } catch (InterruptedException e)
        {
            System.out.println("A operação GET foi interrompida");
        }
    }

}
