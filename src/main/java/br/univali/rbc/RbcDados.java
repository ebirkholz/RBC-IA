package br.univali.rbc;

import java.util.Map;

public class RbcDados {

    private Map<String, String> valores;
    private Map<String, Double> valoresPesos;

    public RbcDados(Map<String, String> valores, Map<String, Double> valoresPesos) {
        this.valores = valores;
        this.valoresPesos = valoresPesos;
    }

    public Map<String, String> getValores() {
        return valores;
    }

    public Map<String, Double> getValoresPesos() {
        return valoresPesos;
    }
}
