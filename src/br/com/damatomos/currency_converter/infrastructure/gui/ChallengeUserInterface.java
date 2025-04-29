package br.com.damatomos.currency_converter.infrastructure.gui;

import br.com.damatomos.currency_converter.application.CurrencyConverter;
import br.com.damatomos.currency_converter.core.entity.Currency;
import br.com.damatomos.currency_converter.infrastructure.config.Configurations;
import br.com.damatomos.currency_converter.infrastructure.dto.ResponseConversionRateDTO;
import br.com.damatomos.currency_converter.infrastructure.dto.ResponseSupportedCodesDTO;
import br.com.damatomos.currency_converter.infrastructure.enums.CurrencyCodeEnum;
import br.com.damatomos.currency_converter.infrastructure.mapper.CurrencyMapper;
import br.com.damatomos.currency_converter.infrastructure.services.CurrencyService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ChallengeUserInterface {

    private final Scanner in;
    private final CurrencyService currencyService;
    private final CurrencyConverter converter;

    private Boolean runnable;
    private List<Currency> currencies;

    public ChallengeUserInterface(Scanner in, CurrencyService currencyService, CurrencyConverter converter)
    {
        this.in = in;
        this.currencyService = currencyService;
        this.converter = converter;
    }

    public void run() {
        runnable = true;

        while(runnable)
        {
            try {

                ResponseSupportedCodesDTO supportedCodesDTO = currencyService.getSupportedCodes();
                this.currencies = CurrencyMapper.toEntity(supportedCodesDTO);

                showOptions();
                callOperationBasedChoose();

            } catch (IOException e)
            {
                System.out.println("Erro durante a operação GET");
            } catch (InterruptedException e)
            {
                System.out.println("A operação GET foi interrompida");
            }
        }
    }

    private void showOptions()
    {
        String gui =
                """
                ******************************************************************
                Seja bem-vindo/a ao Conversor de Moeda =]
                
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileiro
                4) Real brasileiro =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Sair
                Escolha uma opção válida:
                ******************************************************************
                """;

        System.out.println(gui);
    }

    private Currency getCurrencyByCode(CurrencyCodeEnum code)
    {
        if (!this.currencies.isEmpty())
        {
            return this.currencies.stream().filter(c -> c.getBaseCode().equalsIgnoreCase(code.toString())).toList().getFirst();
        }
        return null;
    }

    private void callOperationBasedChoose() throws IOException, InterruptedException {
        Integer option = in.nextInt();

        if (option.equals(7))
        {
            System.out.println("Programa finalizado");
            runnable = false;
            return;
        } else if (option < 1 || option > 7)
        {
            System.out.println("Operação inválida. Tente novamente com outra opção");
            return;
        }

        System.out.println("Digite o valor que deseja converter");

        BigDecimal value = in.nextBigDecimal();

        CurrencyCodeEnum baseCurrency;
        CurrencyCodeEnum targetCurrency;

        switch (option)
        {
            case 1:
                baseCurrency = CurrencyCodeEnum.USD;
                targetCurrency = CurrencyCodeEnum.ARS;
                break;
            case 2:
                baseCurrency = CurrencyCodeEnum.ARS;
                targetCurrency = CurrencyCodeEnum.USD;
                break;
            case 3:
                baseCurrency = CurrencyCodeEnum.USD;
                targetCurrency = CurrencyCodeEnum.BRL;
                break;
            case 4:
                baseCurrency = CurrencyCodeEnum.BRL;
                targetCurrency = CurrencyCodeEnum.USD;
                break;
            case 5:
                baseCurrency = CurrencyCodeEnum.USD;
                targetCurrency = CurrencyCodeEnum.COP;
                break;
            case 6:
                baseCurrency = CurrencyCodeEnum.COP;
                targetCurrency = CurrencyCodeEnum.USD;
                break;
            default:
                baseCurrency = CurrencyCodeEnum.USD;
                targetCurrency = CurrencyCodeEnum.USD;
                break;
        }

        BigDecimal result = calculateOperation(
                value,
                getCurrencyByCode(baseCurrency),
                getCurrencyByCode(targetCurrency)
        );

        System.out.printf("Valor %f [%s] corresponde ao valor final de =>>> %f [%s]%n", value, baseCurrency, result, targetCurrency);
    }

    private BigDecimal calculateOperation(BigDecimal value, Currency base, Currency target) throws IOException, InterruptedException {

        if (base == null || target == null) return new BigDecimal(0);

        ResponseConversionRateDTO conversionRateDTO = currencyService.getConversionRateBetweenPair(
                base.getBaseCode(),
                target.getBaseCode());

        return this.converter.convert(value, conversionRateDTO.conversionRate());
    }

}
