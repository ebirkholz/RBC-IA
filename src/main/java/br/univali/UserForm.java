package br.univali;

import br.univali.csv.CsvHelper;
import br.univali.rbc.Rbc;
import br.univali.rbc.RbcDados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserForm {
    private JPanel userForm;
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
        analisarBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

                List<Map<String, String>> similarCases = rbc.findSimilarCases(rbcDados, 0.8);

                for (Map<String, String> similarCase : similarCases) {
                    carrosSelecionados.setText( "Marca: " + similarCase.get("Marca") +
                                                "\nModelo: " + similarCase.get("Modelo") +
                                                "\nAno: " + similarCase.get("Ano") +
                                                "\nCor: " + similarCase.get("Cor") +
                                                "\nKm rodados: " + similarCase.get("Km rodados") +
                                                "\nPreço: " + similarCase.get("Preço") +
                                                "\nPotencia (cv): " + similarCase.get("Potencia (cv)") +
                                                "\nCombustível: " + similarCase.get("Combustível") +
                                                "\nAutomatico: " + similarCase.get("Automatico" ) +
                                                "\n##################################################"
                            );
                }

            }
        });
        enviarBotao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryCase.put("Veiculo Vendido", carroSelecionado.getText());
                rbc.addCaseToDatabase(queryCase);
                CsvHelper.escreveLinhaArquivo("/RBC_Venda_Carros_Matriz_Base_Dados.csv", getLinhaDados());
                // TODO: escrever matriz de pesos
            }
        });
    }

    private List<String[]> getLinhaDados() {
        List<String[]> response = new ArrayList<>();
        response.add(queryCase.values().toArray(new String[0]));

        return response;
    }

    public static void main(String[] args){
        JFrame AutomatoGUI = new JFrame("Reconhecedor de Linguagens Regulares");
        AutomatoGUI.setContentPane(new UserForm().userForm);
        AutomatoGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AutomatoGUI.pack();
        AutomatoGUI.setVisible(true);
    }
}


