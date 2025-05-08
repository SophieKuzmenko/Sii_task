package com.github.skuzmenko.Sii_task.currency;

import com.github.skuzmenko.Sii_task.exception.CustomIllegalArgException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

public class Currency {
    public static MathContext context = new MathContext(12, RoundingMode.HALF_EVEN);
    private static final Dictionary<String, Map<String, BigDecimal>> rates = new Hashtable<>();
    static{
        Map<String, BigDecimal> plnMap = new HashMap<>();
        plnMap.put("PLN",new BigDecimal("1.0"));
        plnMap.put("USD",new BigDecimal("3.79"));
        plnMap.put("EUR",new BigDecimal("4.25"));

        Map<String, BigDecimal> euroMap = new HashMap<>();
        euroMap.put("EUR",new BigDecimal("1.0"));
        euroMap.put("PLN", new BigDecimal("0.24"));
        euroMap.put("USD", new BigDecimal("0.89"));

        Map<String, BigDecimal> usdMap = new HashMap<>();
        usdMap.put("USD",new BigDecimal("1.0"));
        usdMap.put("PLN", new BigDecimal("0.26"));
        usdMap.put("EUR", new BigDecimal("1.12"));

        rates.put("PLN", plnMap);
        rates.put("EUR", euroMap);
        rates.put("USD", usdMap);
    }
    private final static List<String> currencies = List.of("PLN", "USD", "EUR");
    public static void verifyCurrency(String currency){
        if ((currency.length()!=3) || !currencies.contains(currency))
            throw new CustomIllegalArgException("Currency should be one of the following:'PLN', 'USD' or 'EUR");
    }
    public static BigDecimal getSumInCurrency(String currency, BigDecimal plnAmount, BigDecimal euroAmount, BigDecimal usdAmount)
    {
        Map<String,BigDecimal> conversions = rates.get(currency);
        BigDecimal convertedPln = plnAmount.multiply(conversions.get("PLN"));
        BigDecimal convertedEur = euroAmount.multiply(conversions.get("EUR"));
        BigDecimal convertedUsd = usdAmount.multiply(conversions.get("USD"));

        BigDecimal sum = convertedPln.add(convertedEur).add(convertedUsd);
        return sum.setScale(2,RoundingMode.HALF_EVEN);
    }
}
