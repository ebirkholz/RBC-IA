package br.univali.rbc;

import br.univali.csv.Csv;
import br.univali.csv.CsvHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rbc {
    private List<Map<String, String>> database;
    private List<List<Double>> corSimilarityMatrix;
    private List<List<Double>> modeloSimilarityMatrix;
    private String[] chavesCor;
    private String[] chavesModelo;
    private String[] chaves;

    public Rbc(){
        this.populateDatabase();
        this.populateCor();
        this.populateModelo();
    }

    public void addCaseToDatabase(Map<String, String> caseDetails) {
        database.add(caseDetails);
    }

    public List<Map<String, String>> findSimilarCases(RbcDados rbcDados, double threshold) {
        List<Map<String, String>> similarCases = new ArrayList<>();

        for (Map<String, String> caseInDatabase : database) {
            double similarity = calculateCaseSimilarity(rbcDados, caseInDatabase);
            if (similarity > threshold) {
                similarCases.add(caseInDatabase);
            }
        }

        return similarCases;
    }

    private double calculateCaseSimilarity(RbcDados rbcDados, Map<String, String> caseInDatabase) {
        Map<String, String> queryCase = rbcDados.getValores();
        double similaritySum = 0.0;
        int numAttributes = 0;

        for (Map.Entry<String, String> entry : queryCase.entrySet()) {
            String attribute = entry.getKey();
            String queryValue = entry.getValue();

            if (caseInDatabase.containsKey(attribute) && !attribute.equalsIgnoreCase("veiculo vendido")) {
                String databaseValue = caseInDatabase.get(attribute);
                double attributeSimilarity = calculateAttributeSimilarity(rbcDados, attribute, queryValue, databaseValue);
                similaritySum += attributeSimilarity;
                numAttributes++;
            }
        }

        if (numAttributes > 0) {
            return similaritySum / numAttributes;
        } else {
            return 0.0;
        }
    }

    private double calculateAttributeSimilarity(RbcDados rbcDados, String attribute, String queryValue, String databaseValue) {
        if (rbcDados.getValoresPesos().get(attribute) != null) {
            if (attribute.equalsIgnoreCase("cor")) {
                int queryIndex = getCorIndex(queryValue);
                int dbIndex = getCorIndex(databaseValue);

                this.validaPesoIndex(queryValue, queryIndex);
                this.validaPesoIndex(databaseValue, dbIndex);

                return corSimilarityMatrix.get(queryIndex).get(dbIndex) + rbcDados.getValoresPesos().get(attribute);
            } else if (attribute.equalsIgnoreCase("modelo")) {
                int queryIndex = getModeloIndex(queryValue);
                int dbIndex = getModeloIndex(databaseValue);

                this.validaPesoIndex(queryValue, queryIndex);
                this.validaPesoIndex(databaseValue, dbIndex);

                return modeloSimilarityMatrix.get(queryIndex).get(dbIndex) + rbcDados.getValoresPesos().get(attribute);
            } else if (attribute.equalsIgnoreCase("preço")) {
                return this.calculaPrecoSimilar(databaseValue, queryValue, rbcDados.getValoresPesos().get(attribute));
            }

            return rbcDados.getValoresPesos().get(attribute);
        }

        return 0.0;
    }

    private int getCorIndex(String cor) {
        for (int i = 0; i < chavesCor.length; i++) {
            if (cor.equalsIgnoreCase(chavesCor[i])) {
                return i;
            }
        }
        return -1;
    }

    private int getModeloIndex(String modelo) {
        for (int i = 0; i < chavesModelo.length; i++) {
            if (modelo.equalsIgnoreCase(chavesModelo[i])) {
                return i;
            }
        }
        return -1;
    }

    private double calculaPrecoSimilar(String databaseValue, String queryValue, Double peso) {
        Double caseValor = Double.parseDouble(databaseValue
                .split(",")[0]
                .replace("R$ ", "")
                .replace(".", ""));
        Double queryValor = Double.parseDouble(queryValue
                .split(",")[0]
                .replace("R$ ", "")
                .replace(".", ""));
        int diff = (int) Math.abs(caseValor - queryValor);

        return diff >= 1 && diff < 8000 ? peso : 0.0;
    }

    private void validaPesoIndex(String value, Integer index) {
        if (index < 0) {
            throw new RuntimeException(String.format("Falha para encontrar o valor de %s, na matriz de pesos!", value));
        }
    }

    private void populateDatabase(){
        database = new ArrayList<>();

        Csv csvDados = CsvHelper.leArquivo("/RBC_Venda_Carros_Matriz_Base_Dados.csv");
        chaves = csvDados.getCabecalho();

        for (int i = 0; i < csvDados.getLinhas().size() - 1; i++) {
            Map<String, String> caso = new HashMap<>();

            for (int j = 0; j < chaves.length; j++) {
                caso.put(chaves[j], csvDados.getLinhas().get(i)[j]);
            }

            this.addCaseToDatabase(caso);
        }
    }

    private void populateCor() {
        corSimilarityMatrix = new ArrayList<>();

        Csv csvCor = CsvHelper.leArquivo("/RBC_Venda_Carros_Matriz_Cor.csv");
        chavesCor = Arrays.stream(csvCor.getCabecalho())
                .filter(s -> !s.equalsIgnoreCase("Cor"))
                .toArray(String[]::new);

        for (int i = 0; i < csvCor.getLinhas().size(); i++) {
            List<Double> valores = new ArrayList<>();
            for (int j = 1; j < csvCor.getLinhas().get(i).length; j++) {
                valores.add(Double.parseDouble(csvCor.getLinhas().get(i)[j]));
            }

            corSimilarityMatrix.add(valores);
        }
    }

    private void populateModelo() {
        modeloSimilarityMatrix = new ArrayList<>();

        Csv csvModelo = CsvHelper.leArquivo("/RBC_Venda_Carros_Matriz_Modelo.csv");
        chavesModelo = Arrays.stream(csvModelo.getCabecalho())
                .filter(s -> !s.equalsIgnoreCase("Modelo"))
                .toArray(String[]::new);

        for (int i = 0; i < csvModelo.getLinhas().size(); i++) {
            List<Double> valores = new ArrayList<>();
            for (int j = 1; j < csvModelo.getLinhas().get(i).length; j++) {
                valores.add(Double.parseDouble(csvModelo.getLinhas().get(i)[j]));
            }

            modeloSimilarityMatrix.add(valores);
        }
    }
}
