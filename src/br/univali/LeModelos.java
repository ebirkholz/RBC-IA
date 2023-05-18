package br.univali;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class LeModelos {

    public static Hashtable<String, Float> clio = new Hashtable<String, Float>();
    public static  Hashtable<String, Float> gol = new Hashtable<String, Float>();
    public static  Hashtable<String, Float> palio = new Hashtable<String, Float>();
    public static  Hashtable<String, Float> p408 = new Hashtable<String, Float>();
    public static  Hashtable<String, Float> f1000 = new Hashtable<String, Float>();
    public static  Hashtable<String, Float> cerato = new Hashtable<String, Float>();
    public static  Hashtable<String, Float> veloster = new Hashtable<String, Float>();
    public static  Hashtable<String, Float> c3 = new Hashtable<String, Float>();
    public static  Hashtable<String, Float> kicks = new Hashtable<String, Float>();
    public static  Hashtable<String, Float> saveiro = new Hashtable<String, Float>();
    public static  Hashtable<String, Float> tracker = new Hashtable<String, Float>();
    public static  Hashtable<String, Float> civic = new Hashtable<String, Float>();


    public void LeModelo(String file){

        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ",";
        int cont = 0;
        String modelos[] = new String[13];
        try {

            br = new BufferedReader(new FileReader(file));
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(csvDivisor);
                for (int i = 0; i < valores.length; i++) {

                    if (cont == 0){
                        modelos[i] = valores[i];
                    }

                    if(valores[0].equals("Clio")){
                        if(i==0){
                            continue;
                        }
                        clio.put(modelos[i],Float.parseFloat(valores[i]));
                    }
                    if(valores[0].equals("Gol")){
                        if(i==0){
                            continue;
                        }
                        gol.put(modelos[i],Float.parseFloat(valores[i]));
                    }
                    if(valores[0].equals("Palio")){
                        if(i==0){
                            continue;
                        }
                        palio.put(modelos[i],Float.parseFloat(valores[i]));
                    }
                    if(valores[0].equals("P408")){
                        if(i==0){
                            continue;
                        }
                        p408.put(modelos[i],Float.parseFloat(valores[i]));
                    }
                    if(valores[0].equals("F1000")){
                        if(i==0){
                            continue;
                        }
                        f1000.put(modelos[i],Float.parseFloat(valores[i]));
                    }
                    if(valores[0].equals("Cerato")){
                        if(i==0){
                            continue;
                        }
                        cerato.put(modelos[i],Float.parseFloat(valores[i]));
                    }
                    if(valores[0].equals("Veloster")){
                        if(i==0){
                            continue;
                        }
                        veloster.put(modelos[i],Float.parseFloat(valores[i]));
                    }
                    if(valores[0].equals("C3")){
                        if(i==0){
                            continue;
                        }
                        c3.put(modelos[i],Float.parseFloat(valores[i]));
                    }
                    if(valores[0].equals("Kicks")){
                        if(i==0){
                            continue;
                        }
                        kicks.put(modelos[i],Float.parseFloat(valores[i]));
                    }
                    if(valores[0].equals("Saveiro")){
                        if(i==0){
                            continue;
                        }
                        saveiro.put(modelos[i],Float.parseFloat(valores[i]));
                    }
                    if(valores[0].equals("Tracker")){
                        if(i==0){
                            continue;
                        }
                        tracker.put(modelos[i],Float.parseFloat(valores[i]));
                    }
                    if(valores[0].equals("Civic Touring")){
                        if(i==0){
                            continue;
                        }
                        civic.put(modelos[i],Float.parseFloat(valores[i]));
                    }
                }
                cont++;
            }
            //IMPRIME OS DICIONARIOS PARA VALIDAR
            System.out.println("Dicionario Clio: " + clio);
            System.out.println("Dicionario Gol: " + gol);
            System.out.println("Dicionario Palio: " + palio);
            System.out.println("Dicionario P408: " + p408);
            System.out.println("Dicionario F1000: " + f1000);
            System.out.println("Dicionario Cerato: " + cerato);
            System.out.println("Dicionario Veloster: " + veloster);
            System.out.println("Dicionario C3: " + c3);
            System.out.println("Dicionario Kicks: " + kicks);
            System.out.println("Dicionario Saveiro: " + saveiro);
            System.out.println("Dicionario Tracker: " + tracker);
            System.out.println("Dicionario Civic Touring: " + civic);
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
