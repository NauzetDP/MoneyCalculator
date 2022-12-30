package moneycalculator.persistance;

import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;

public interface ExchangeRateLoader {
    
    public ExchangeRate ExchangeRateLoader(Currency from, Currency to);
}