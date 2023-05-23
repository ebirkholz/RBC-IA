package br.univali.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CsvHelper {

    private CsvHelper() {}

    public static Csv leArquivo(String nomeArquivo) {
        try {
            URL fileUrl = CsvHelper.class.getResource(nomeArquivo);

            if (fileUrl == null) {
                throw new RuntimeException("Arquivo não encontrado!");
            }

            CSVReader reader = new CSVReader(new FileReader(fileUrl.getFile()));
            String[] cabecalho = reader.readNext();
            List<String[]> linhas = reader.readAll();

            reader.close();

            return new Csv(cabecalho, linhas);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Falha ao ler arquivo .cvs!");
        }
    }

    public static void escreveLinhaArquivo(String nomeArquivo, String[] ...linhas) {
        try {
            URL fileUrl = CsvHelper.class.getResource(nomeArquivo);

            if (fileUrl == null) {
                throw new RuntimeException("Arquivo não encontrado!");
            }

            CSVReader reader = new CSVReader(new FileReader(fileUrl.getFile()));
            List<String[]> linhasFinal = new ArrayList<>(reader.readAll());
            Collections.addAll(linhasFinal, linhas);
            reader.close();

            CSVWriter writer = new CSVWriter(new FileWriter(fileUrl.getFile()));

            for (String[] linha : linhasFinal) {
                writer.writeNext(linha, false);
            }

            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Falha ao escrever arquivo .cvs!");
        }
    }

    public static void escreveLinhaColuna(String nomeArquivo, List<String> valores) {
        try {
            URL fileUrl = CsvHelper.class.getResource(nomeArquivo);

            if (fileUrl == null) {
                throw new RuntimeException("Arquivo não encontrado!");
            }

            List<String> dados = Files.readAllLines(Paths.get(fileUrl.toURI()));

            FileWriter fileWriter = new FileWriter(Paths.get(fileUrl.toURI()).toFile().getPath(), false);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < dados.size(); i++) {
                String[] linha = dados.get(i).split(",");
                List<String> linhaDados = new ArrayList<>(Arrays.asList(linha));
                linhaDados.add(valores.get(i));
                printWriter.println(String.join(",", linhaDados));
            }

            valores.add("1.0");

            printWriter.println(String.join(",", valores));

            printWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Falha ao escrever arquivo .cvs!");
        }
    }
}
