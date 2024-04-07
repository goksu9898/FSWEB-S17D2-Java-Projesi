package com.example.S17D2.tax;

import org.springframework.stereotype.Component;

@Component
public class DeveloperTax implements Taxable{
    @Override
    public double getSimpleTaxRate() {
        return 10;
    }

    @Override
    public double getMiddleTaxRate() {
        return 20;
    }

    @Override
    public double getUpperTaxRate() {
        return 30;
    }
}
