package moneycalculator.persistance.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.persistance.ExchangeRateLoader;

public class ExchangeRateLoaderWebService implements ExchangeRateLoader {
    
    private final String dom;
    
    public ExchangeRateLoaderWebService (String dom) {
        this.dom = dom;
    }

    public String read (URL url) throws IOException {
        InputStream inputStream = url.openStream();
        byte[] buffer = new byte[1024];
        int length = inputStream.read(buffer);
        return new String(buffer, 0, length);
    }

    public String getStringRateFromJSON(String line) {
        String[] split = line.split(",");
        return split[1].substring(split[1].indexOf(":") + 1, split[1].length()-1);
    }

    @Override
    public ExchangeRate ExchangeRateLoader(Currency from, Currency to) {
        String rate = "";
        try {
            String line = read(new URL(this.dom + from.getCode().toLowerCase() 
                                + "/" + to.getCode().toLowerCase() + ".json"));
            System.out.println(line);
            rate = getStringRateFromJSON(line);
        } catch (IOException e) {
            
        }
        return new ExchangeRate(from, to, Double.parseDouble(rate));
    }
}