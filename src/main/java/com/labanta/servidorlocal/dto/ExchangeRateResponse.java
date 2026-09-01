package com.labanta.servidorlocal.dto;

import java.util.Map;

public class ExchangeRateResponse {

    private String base;
    private Map<String, Double> rates; // Dicionário que guarda "Moeda" -> "Valor"

    // Gerar Getters e Setters!
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
