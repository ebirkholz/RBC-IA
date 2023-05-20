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
import java.util.List;

public class CsvHelper {

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

    public static void escreveLinhaArquivo(String nomeArquivo, List<String[]> linhas) {
        try {
            URL fileUrl = CsvHelper.class.getResource(nomeArquivo);

            if (fileUrl == null) {
                throw new RuntimeException("Arquivo não encontrado!");
            }

            CSVWriter writer = new CSVWriter(new FileWriter(fileUrl.getFile()));

            for (String[] linha : linhas) {
                writer.writeNext(linha, false);
            }

            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Falha ao escrever arquivo .cvs!");
        }
    }

    public static void escreveColuna(String nomeArquivo, String[] nomePesos) {
        try {
            URL fileUrl = CsvHelper.class.getResource(nomeArquivo);

            if (fileUrl == null) {
                throw new RuntimeException("Arquivo não encontrado!");
            }

            List<String> dados = Files.readAllLines(Paths.get(fileUrl.toURI()));

            FileWriter fileWriter = new FileWriter(fileUrl.toURI().toString(), true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 0; i < dados.size(); i++) {
                String[] linha = dados.get(i).split(",");
                List<String> linhaDados = new ArrayList<>(Arrays.asList(linha));
                linhaDados.add(dados.size() + 1, nomePesos[i]);
                printWriter.println(String.join(",", linhaDados));
            }

            printWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Falha ao escrever arquivo .cvs!");
        }
    }
}
