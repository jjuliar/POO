/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supertrunfodareciclagem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author julia
 */
public class Baralho {
    private ArrayList<Cartas> cartas;
   
    public Baralho() {
        cartas = new ArrayList<>();
    }
    
    public void geraBaralho() {
    Cartas carta;
    Cor cor = null;
    String filePath = new File("").getAbsolutePath();
    String arquivoCSV = filePath + "\\src\\supertrunfodareciclagem\\cartas.csv";
    BufferedReader br = null;
    String linha = "";
    String divisor = ";";
    
        try {
            br = new BufferedReader(new FileReader(arquivoCSV));

            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(divisor);

                if(dados[7].equals("sim")) {
                    carta = new Reciclavel(dados[0], dados[1], dados[2], dados[3], Double.parseDouble(dados[5]), cor.stringToCor(dados[4]), Integer.parseInt(dados[6]));
                } else {
                    carta = new NaoReciclavel(dados[0], dados[1], dados[2], dados[3], Double.parseDouble(dados[5]), cor.stringToCor(dados[4]), Integer.parseInt(dados[6]));
                }  

                cartas.add(carta);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
   
    
    public Cartas selecionaCarta(int index) {
        //System.out.println(index);
        return cartas.get(index);
    }
}
