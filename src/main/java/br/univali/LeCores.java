package br.univali;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class LeCores {

    public static  Hashtable<String, Float> preto = new Hashtable<String, Float>();
    public static  Hashtable<String, String> prata = new Hashtable<String, String>();
    public static  Hashtable<String, String> verde = new Hashtable<String, String>();
    public static  Hashtable<String, String> vermelha = new Hashtable<String, String>();
    public static  Hashtable<String, String> branco = new Hashtable<String, String>();





    public void LeCor(String file){

        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ",";
        int cont = 0;
        String cores[] = new String[6];
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            br = new BufferedReader(inputStreamReader);

            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(csvDivisor);
                for (int i = 0; i < valores.length; i++) {

                    if (cont == 0){
                        cores[i] = valores[i];
                    }

                    if(valores[0].equals("Preto")){
                        if(i==0){
                            continue;
                        }
                        preto.put(cores[i],Float.parseFloat(valores[i]));
                    }
                    if(valores[0].equals("Prata")){
                        if(i==0){
                            continue;
                        }
                        prata.put(cores[i],valores[i]);
                    }
                    if(valores[0].equals("Verde")){
                        if(i==0){
                            continue;
                        }
                        verde.put(cores[i],valores[i]);
                    }
                    if(valores[0].equals("Vermelha")){
                        if(i==0){
                            continue;
                        }
                        vermelha.put(cores[i],valores[i]);
                    }
                    if(valores[0].equals("Branco")){
                        if(i==0){
                            continue;
                        }
                        branco.put(cores[i],valores[i]);
                    }
                }
                cont++;
            }

            //IMPRIME OS DICIONARIOS PARA VALIDAR
            System.out.println("Dicionario Preto: " + preto);
            System.out.println("Dicionario Prata: " + prata);
            System.out.println("Dicionario Verde: " + verde);
            System.out.println("Dicionario Vermelho: " + vermelha);
            System.out.println("Dicionario Branco: " + branco);
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
