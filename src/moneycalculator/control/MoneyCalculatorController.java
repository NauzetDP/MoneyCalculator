package moneycalculator.control;

import java.util.ArrayList;
import java.util.List;
import moneycalculator.model.Currency;
import moneycalculator.persistance.files.CurrencyLoaderFile;
import moneycalculator.view.swing.DisplaySwing;
import moneycalculator.view.swing.GUISwing;

public class MoneyCalculatorController {
    
    private List<Currency> listCurrency = new ArrayList<>();
    
    public void initGUI() {
        initCurrencies();
        new GUISwing(new DisplaySwing(listCurrency), "Display money converser");
    }
    
    public void initCurrencies() {
        CurrencyLoaderFile clf = new CurrencyLoaderFile("currencies.txt");
        listCurrency = clf.currencyLoader();
    }
}