package moneycalculator.persistance.files;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import moneycalculator.persistance.CurrencyLoader;
import moneycalculator.model.Currency;

public class CurrencyLoaderFile implements CurrencyLoader {
    
    private final String fileName;
    
    public CurrencyLoaderFile(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public List<Currency> currencyLoader() {
        List<Currency> list  = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                list.add(currencyOf(line));
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("CurrencyLoaderFile :: currencyLoader, FileNotFound" + ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.out.println("CurrencyLoaderFile :: currencyLoader, (IO)" + ex.getMessage());
            return null;
        }
        return list;
    }

    private Currency currencyOf(String line) {
        String [] split = line.split(",");
        return new Currency(split[0], split[1], split[2]);
    }
}