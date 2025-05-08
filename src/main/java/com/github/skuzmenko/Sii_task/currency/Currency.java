package com.github.skuzmenko.Sii_task.currency;

import com.github.skuzmenko.Sii_task.exception.CustomIllegalArgException;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class Currency {
    public static MathContext context = new MathContext(12, RoundingMode.HALF_EVEN);
    private final static List<String> currencies = List.of("PLN", "USD", "EUR");
    public static void verifyCurrency(String currency){
        if ((currency.length()!=3) || !currencies.contains(currency))
            throw new CustomIllegalArgException("Currency should be one of the following:'PLN', 'USD' or 'EUR");
    }
}
