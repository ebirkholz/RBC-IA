package br.univali;

import br.univali.csv.CsvHelper;
import br.univali.rbc.Rbc;
import br.univali.rbc.RbcDados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class UserForm {
    JPanel userForm;
    private JLabel Entrada;
    private JTextField entradaMarca;
    private JLabel valor;
    private JLabel Modelo;
    private JTextField entradaModelo;
    private JLabel Ano;
    private JTextField entradaAno;
    private JTextField entradaKM;
    private JTextField entradaCor;
    private JLabel Cor;
    private JLabel kmRodado;
    private JLabel marca;
    private JTextField entradaPreco;
    private JLabel Preço;
    private JTextField entradaPotencia;
    private JLabel Potencia;
    private JTextField entradaCombustivel;
    private JTextField entradaAutomatico;
    private JLabel Automatico;
    private JTextField entradaQtdPortas;
    private JLabel Combustivel;
    private JLabel QtdPortas;
    private JTextArea carrosSelecionados;
    private JLabel CarrosSelecionados;
    private JButton analisarBotao;
    private JLabel CarroSelecionado;
    private JTextField carroSelecionado;
    private JButton enviarBotao;
    private JTextField marcaValor;
    private JTextField modeloValor;
    private JTextField anoValor;
    private JTextField corValor;
    private JTextField kmValor;
    private JTextField precoValor;
    private JTextField potenciaValor;
    private JTextField combustivelValor;
    private JTextField automaticoValor;
    private JTextField qtdPortasValor;
    ArrayList<String> conteudoOutbox = new ArrayList<String>();
    RbcDados rbcDados;
    Map<String, String> queryCase = new HashMap<>();
    Rbc rbc = new Rbc();

    public UserForm(){
        analisarBotao.addActionListener(e -> {
            conteudoOutbox.clear();

            queryCase.put("Marca", entradaMarca.getText());
            queryCase.put("Modelo", entradaModelo.getText());
            queryCase.put("Ano", entradaAno.getText());
            queryCase.put("Cor", entradaCor.getText());
            queryCase.put("Km rodados", entradaKM.getText());
            queryCase.put("Preço", entradaPreco.getText());
            queryCase.put("Potencia (cv)", entradaPotencia.getText());
            queryCase.put("Combustível", entradaCombustivel.getText());
            queryCase.put("Automatico", entradaAutomatico.getText());
            queryCase.put("Qtd de Portas", entradaQtdPortas.getText());
            queryCase.put("Veiculo Vendido", " ");

            Map<String, Double> valoresPesos = new HashMap<>();
            valoresPesos.put("Marca", Double.parseDouble(marcaValor.getText()));
            valoresPesos.put("Modelo",Double.parseDouble(modeloValor.getText()));
            valoresPesos.put("Ano", Double.parseDouble(anoValor.getText()));
            valoresPesos.put("Cor", Double.parseDouble(corValor.getText()));
            valoresPesos.put("Km rodados", Double.parseDouble(kmValor.getText()));
            valoresPesos.put("Preço", Double.parseDouble(precoValor.getText()));
            valoresPesos.put("Potencia (cv)", Double.parseDouble(potenciaValor.getText()));
            valoresPesos.put("Combustível", Double.parseDouble(combustivelValor.getText()));
            valoresPesos.put("Automatico", Double.parseDouble(automaticoValor.getText()));
            valoresPesos.put("Qtd de Portas", Double.parseDouble(qtdPortasValor.getText()));

            rbcDados = new RbcDados(queryCase, valoresPesos);

            List<Map<String, String>> similarCases = rbc.findSimilarCases(rbcDados, 0.1);

            String novoTexto = "";

            for (Map<String, String> similarCase : similarCases) {
                novoTexto = carrosSelecionados.getText()
                        + "Marca: " + similarCase.get("Marca")
                        + "\nModelo: " + similarCase.get("Modelo")
                        + "\nAno: " + similarCase.get("Ano")
                        + "\nCor: " + similarCase.get("Cor")
                        + "\nKm rodados: " + similarCase.get("Km rodados")
                        + "\nPreço: " + similarCase.get("Preço")
                        + "\nPotencia (cv): " + similarCase.get("Potencia (cv)")
                        + "\nCombustível: " + similarCase.get("Combustível")
                        + "\nAutomatico: " + similarCase.get("Automatico" )
                        + "\n##################################################";

            }

            carrosSelecionados.setText(novoTexto);
        });

        enviarBotao.addActionListener(e -> {
            queryCase.put("Veiculo Vendido", carroSelecionado.getText());
            rbc.addCaseToDatabase(queryCase);

            String[] novoCaso = getLinhaDados();
            CsvHelper.escreveLinhaArquivo("/RBC_Venda_Carros_Matriz_Base_Dados.csv", novoCaso);

            populateMatrizPesos();

            JOptionPane.showMessageDialog(userForm, "Veiculo adicionado");
            clean();
        });
    }

    private String[] getLinhaDados() {
        String[] linhaDados = new String[rbc.getChaves().length];

        for (int i = 0; i < linhaDados.length; i++) {
            linhaDados[i] = queryCase.get(rbc.getChaves()[i]);
        }

        return linhaDados;
    }

    private void populateMatrizPesos() {
        if (!Arrays.asList(rbc.getChavesModelo()).contains(carroSelecionado.getText())) {
            List<String> valores = getLinhaPesos("Modelo");

            CsvHelper.escreveLinhaColuna("/RBC_Venda_Carros_Matriz_Modelo.csv", valores);
        }

        if (!Arrays.asList(rbc.getChavesCor()).contains(queryCase.get("Cor"))) {
            List<String> valores = getLinhaPesos("Cor");

            CsvHelper.escreveLinhaColuna("/RBC_Venda_Carros_Matriz_Cor.csv", valores);
        }
    }

    private List<String> getLinhaPesos(String chave) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String[] chaveValores = chave.equalsIgnoreCase("Modelo")
                ? rbc.getChavesModelo()
                : rbc.getChavesCor();
        List<String> linhaDados = new ArrayList<>();

        linhaDados.add(chave.equalsIgnoreCase("Modelo")
                ? carroSelecionado.getText()
                : queryCase.get("Cor"));

        for (int i = 0; i < chaveValores.length; i++) {
            Double novoValor = ThreadLocalRandom.current().nextDouble(0.0, 1.0);
            linhaDados.add(decimalFormat.format(novoValor));
        }

        return linhaDados;
    }

    private void clean() {
        conteudoOutbox.clear();
        carrosSelecionados.setText("");
        carroSelecionado.setText("");
        queryCase.clear();

        entradaMarca.setText("");
        entradaModelo.setText("");
        entradaAno.setText("");
        entradaCor.setText("");
        entradaKM.setText("");
        entradaPreco.setText("");
        entradaPotencia.setText("");
        entradaCombustivel.setText("");
        entradaAutomatico.setText("");
        entradaQtdPortas.setText("");

        marcaValor.setText("");
        modeloValor.setText("");
        anoValor.setText("");
        corValor.setText("");
        kmValor.setText("");
        precoValor.setText("");
        potenciaValor.setText("");
        combustivelValor.setText("");
        automaticoValor.setText("");
        qtdPortasValor.setText("");

        rbc = new Rbc();
    }

    private void insertTesteValue() {
        entradaMarca.setText("Volkswagen");
        entradaModelo.setText("Pollo");
        entradaAno.setText("2015");
        entradaCor.setText("Azul");
        entradaKM.setText("12500");
        entradaPreco.setText("R$ 33.490,00");
        entradaPotencia.setText("85");
        entradaCombustivel.setText("Flex");
        entradaAutomatico.setText("Não");
        entradaQtdPortas.setText("4");

        marcaValor.setText("0.4");
        modeloValor.setText("0.5");
        anoValor.setText("0.7");
        corValor.setText("0.8");
        kmValor.setText("1.0");
        precoValor.setText("0.8");
        potenciaValor.setText("0.1");
        combustivelValor.setText("0.9");
        automaticoValor.setText("0.8");
        qtdPortasValor.setText("1.0");
    }
}
