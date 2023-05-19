package br.univali;

public class Main {
    public static void main(String[] args) {
        LeCores cores = new LeCores();
        cores.LeCor("/RBC_Venda_Carros_Matriz_Cor.csv");
        LeModelos carros = new LeModelos();
        carros.LeModelo("/RBC_Venda_Carros_Matriz_Modelo.csv");
    }
}
