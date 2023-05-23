package br.univali;

import br.univali.rbc.Rbc;
import br.univali.rbc.RbcDados;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        executaCliente();
//        executaTesteLogica();
    }

    private static void executaTesteLogica() {
        Rbc rbc = new Rbc();
        Map<String, String> queryCase = new HashMap<>();
        queryCase.put("Marca", "Volkswagen");
        queryCase.put("Modelo", "Gol");
        queryCase.put("Ano", "2012");
        queryCase.put("Cor", "Prata");
        queryCase.put("Km rodados", "125000");
        queryCase.put("Preço", "R$ 27.490,00");
        queryCase.put("Potencia (cv)", "76");
        queryCase.put("Combustível", "Flex");
        queryCase.put("Automatico", "Não");
        queryCase.put("Qtd de Portas", "4");
        queryCase.put("Veiculo Vendido", "Kwid");

        Map<String, Double> valoresPesos = new HashMap<>();
        valoresPesos.put("Marca", 0.4);
        valoresPesos.put("Modelo", 0.5);
        valoresPesos.put("Ano", 0.7);
        valoresPesos.put("Cor", 0.8);
        valoresPesos.put("Km rodados", 1.0);
        valoresPesos.put("Preço", 0.8);
        valoresPesos.put("Potencia (cv)", 0.1);
        valoresPesos.put("Combustível", 0.9);
        valoresPesos.put("Automatico", 0.8);
        valoresPesos.put("Qtd de Portas", 1.0);

        RbcDados rbcDados = new RbcDados(queryCase, valoresPesos);

        List<Map<String, String>> similarCases = rbc.findSimilarCases(rbcDados, 0.8);

        for (Map<String, String> similarCase : similarCases) {
            System.out.println(similarCase);
        }
    }

    private static void executaCliente() {
        JFrame jFrame = new JFrame("Reconhecedor de Linguagens Regulares");
        jFrame.setContentPane(new UserForm().userForm);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
