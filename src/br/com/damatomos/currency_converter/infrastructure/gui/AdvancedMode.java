package br.com.damatomos.currency_converter.infrastructure.gui;

import br.com.damatomos.currency_converter.application.CurrencyConverter;
import br.com.damatomos.currency_converter.core.entity.Currency;
import br.com.damatomos.currency_converter.core.exceptions.NullCurrencyFieldException;
import br.com.damatomos.currency_converter.infrastructure.dto.ResponseConversionRateDTO;
import br.com.damatomos.currency_converter.infrastructure.dto.ResponseSupportedCodesDTO;
import br.com.damatomos.currency_converter.infrastructure.gui.components.Item;
import br.com.damatomos.currency_converter.infrastructure.gui.components.ItemCellRenderer;
import br.com.damatomos.currency_converter.infrastructure.mapper.CurrencyMapper;
import br.com.damatomos.currency_converter.infrastructure.services.CurrencyService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class AdvancedMode extends UserInterface {
    private Boolean runnable;
    private List<Currency> currencies;

    private JFrame frame;

    public AdvancedMode(CurrencyService currencyService, CurrencyConverter converter)
    {
        super(currencyService, converter);
    }

    @Override
    public void run()
    {
       try {
           ResponseSupportedCodesDTO supportedCodesDTO = this.currencyService.getSupportedCodes();
           this.currencies = CurrencyMapper.toEntity(supportedCodesDTO);

           frame = createFrame();
           GridBagConstraints gbc = new GridBagConstraints();
           gbc.fill = GridBagConstraints.HORIZONTAL;
           gbc.gridx = 0;
           gbc.gridy = 0;

           JPanel panel = new JPanel();
           panel.setSize(500, 300);

           panel.setLayout(new GridBagLayout());

           JLabel label = new JLabel();
           label.setText("Currency Converter");
           label.setSize(600, 100);
           label.setFont(new Font(
                   "Arial",
                   Font.BOLD,
                   32
           ));
           label.setHorizontalAlignment(SwingConstants.CENTER);
           label.setHorizontalTextPosition(SwingConstants.CENTER);
//           panel.add(label);

           frame.add(label, gbc);

           JPanel codeLists = new JPanel();
           codeLists.setSize(600, 200);
           codeLists.setLayout(new FlowLayout());;

           JList leftList = createList(currencies);
           leftList.setSelectedIndex(1);
           codeLists.add(new JScrollPane(leftList));

           JLabel versus = new JLabel("X");
           versus.setSize(200, 100);
           versus.setFont(new Font(
                   "Arial",
                   Font.BOLD,
                   20
           ));
           versus.setHorizontalAlignment(SwingConstants.CENTER);
           versus.setHorizontalTextPosition(SwingConstants.CENTER);

           codeLists.add(versus);

           JList rightList = createList(currencies);
           rightList.setSelectedIndex(1);
           codeLists.add(new JScrollPane(rightList));

           // Text Field
           JPanel fieldPanel = new JPanel();
           fieldPanel.setSize(600, 200);
           fieldPanel.setLayout(new FlowLayout());
           fieldPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
           JLabel amountLabel = new JLabel("Valor: ", JLabel.LEFT);
           JTextField amountField = new JTextField(12);

           fieldPanel.add(amountLabel);
           fieldPanel.add(amountField);

           // Button
           JButton confirm = new JButton("Converter");

           JLabel converted = new JLabel("Conversão: ", JLabel.LEFT);
           converted.setFont(new Font(
                   "Arial",
                   Font.BOLD,
                   20
           ));

           confirm.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   Currency left = ((Item<Currency>) leftList.getSelectedValue()).getConcrect();
                   Currency rigth = ((Item<Currency>) rightList.getSelectedValue()).getConcrect();

                   System.out.println(left.getBaseCode() + " x " + rigth.getBaseCode());

                   try {
                       BigDecimal result = calculateOperation(
                                new BigDecimal(Integer.parseInt(amountField.getText())),
                               left,
                               rigth
                       );

                       converted.setText("Conversão: " + result);
                   } catch (IOException ex) {
                       throw new RuntimeException(ex);
                   } catch (InterruptedException ex) {
                       throw new RuntimeException(ex);
                   }

               }
           });

           fieldPanel.add(confirm);

           gbc.fill = GridBagConstraints.HORIZONTAL;
           gbc.ipady = 20;
           gbc.gridx = 0;
           gbc.gridy = 1;
           frame.add(codeLists, gbc);
           gbc.fill = GridBagConstraints.HORIZONTAL;
           gbc.gridx = 0;
           gbc.gridy = 2;
           gbc.ipady = 20;
           frame.add(fieldPanel, gbc);
           gbc.fill = GridBagConstraints.HORIZONTAL;
           gbc.gridx = 0;
           gbc.gridy = 3;
           gbc.ipady = 20;
           frame.add(converted, gbc);
           frame.setVisible(true);
       } catch (IOException | InterruptedException e)
       {
           System.out.println("Erro ao recuperar lista de codes");
       }
    }

    private JList createList(List<Currency> currencies)
    {
        List<Item<Currency>> items = currencies.stream().map(c ->
        {
            return new Item<>(c, c.getBaseCode(), c.getBaseCode() + " (" + c.getName() + ")" );
        }).toList();

        JList list = new JList(items.toArray());
        list.setCellRenderer(new ItemCellRenderer<Currency>());
        list.setVisibleRowCount(5);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setFixedCellWidth(200);
        list.setFixedCellHeight(30);
        return list;
    }

    private JFrame createFrame()
    {
        JFrame frame = new JFrame("Advanced Mode");
        frame.setSize(600, 600);
        frame.setResizable(false);
        GridBagLayout layout = new GridBagLayout();
        frame.setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }

    private BigDecimal calculateOperation(BigDecimal value, Currency base, Currency target) throws IOException, InterruptedException {

        if (base == null || target == null)
        {
            throw new NullCurrencyFieldException("Currencies não podem ser null");
        };

        ResponseConversionRateDTO conversionRateDTO = currencyService.getConversionRateBetweenPair(
                base.getBaseCode(),
                target.getBaseCode());

        return this.converter.convert(value, conversionRateDTO.conversionRate());
    }
}
