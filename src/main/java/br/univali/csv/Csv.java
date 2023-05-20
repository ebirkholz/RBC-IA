package br.univali.csv;

import java.util.List;

public class Csv {

    private String[] cabecalho;
    private List<String[]> linhas;

    public Csv(String[] cabecalho, List<String[]> linhas) {
        this.cabecalho = cabecalho;
        this.linhas = linhas;
    }

    public String[] getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(String[] cabecalho) {
        this.cabecalho = cabecalho;
    }

    public List<String[]> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<String[]> linhas) {
        this.linhas = linhas;
    }
}
