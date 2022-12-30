package moneycalculator.view.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;
import moneycalculator.persistance.rest.ExchangeRateLoaderWebService;
import moneycalculator.view.Display;

public class DisplaySwing extends JPanel implements Display {
    
    private final JLabel display = new JLabel("MONEY:");
    private final JTextField money = new JTextField();
    private final JButton exchange = new JButton("EXCHANGE");
    private final JComboBox<String> exchangesFrom = new JComboBox<>();
    private final JComboBox<String> exchangesTo = new JComboBox<>();
    private final List<Currency> currencies;
    private final JTextArea result = new JTextArea();
    
    public DisplaySwing(List<Currency> currencies) {
        this.currencies = currencies;
        createComponentsGUI();
    }

    @Override
    public void refreshMoney(Money money) {
        if (money == null) return;
        result.setText("Dinero en " + money.getCurrency().getCode() 
                        + ": " + money.getAmount());
    }
    
    private void createComponentsGUI() {
        setPreferredSize(new Dimension(200,100));
        setLayout(new BorderLayout());
        setOpaque(true);
        
        JPanel leftPane = createPanel();
        leftPane.add(display, BorderLayout.PAGE_START);
        leftPane.add(money, BorderLayout.PAGE_END);
        
        result.setEditable(false);
        
        exchange.addActionListener(new ActionListener(){  
        @Override
        public void actionPerformed(ActionEvent e){
            refreshMoney(getMoneyExchange());
        }  
        });  
        
        JPanel rightPane = createPanel();
        initExchanges();
        rightPane.add(exchangesFrom, BorderLayout.PAGE_START);
        rightPane.add(exchangesTo, BorderLayout.CENTER);
        rightPane.add(exchange, BorderLayout.PAGE_END);
        
        JPanel resultPane = createPanel();
        resultPane.add(result);
        
        add(leftPane, BorderLayout.LINE_START);
        add(rightPane, BorderLayout.LINE_END);
        add(resultPane, BorderLayout.PAGE_END);
    }
    
    private Money getMoneyExchange() {
        if (money.getText().equals(""))return null;
        double amount = Double.parseDouble(money.getText());
        Currency from = getCurrencyByCode((String)exchangesFrom.getSelectedItem());
        Currency to = getCurrencyByCode((String)exchangesTo.getSelectedItem());
        
        ExchangeRateLoaderWebService erlws = new ExchangeRateLoaderWebService
        ("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/");
        ExchangeRate er = erlws.ExchangeRateLoader(from, to);
        amount = amount * er.getRate();
        
        return new Money(to, amount);
    }
    
    private Currency getCurrencyByCode(String code) {
        for (Currency c : currencies) {
            if (c.getCode().equals(code)) return c;
        }
        return null;
    }
    
    private void initExchanges() {
        for (Currency c : currencies) {
            exchangesFrom.addItem(c.getCode());
            exchangesTo.addItem(c.getCode());
        }
    }
    
    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(true);
        return panel;
    }
}