/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supertrunfodareciclagem;

import java.io.BufferedReader;
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
public abstract class Cartas {
   
    private String codigo;
    private String nome;
    private String descricao;
    private String tipo;
    private double decomposicao;
    private Cor cor;
    private int ataque;
   
    public Cartas(String codigo, String nome, String descricao, String tipo, double decomposicao, Cor cor, int ataque ) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.decomposicao = decomposicao;
        this.cor = cor;
        this.ataque = ataque;
    }
    
    public String getCodigo() {
        return codigo;
    };
    
    public String getDescricao() {
        return descricao;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public double getDecomposicao() {
        return decomposicao;
    }

    public int getAtaque() {
        return ataque;
    }
    
    public abstract boolean ehReciclavel();
    
    public int comparar(int response, Cartas carta){
        int resultado = 0;
        
        if(this.codigo.equals("H3")){ //carta megawinner
            if(carta.codigo.contains("1")){
                resultado = -1;
                return resultado;
            }
            resultado = 1;
            return resultado;
        }
        
        if(carta.codigo.equals("H3")){ //carta megawinner
            if(this.codigo.contains("1")){
                resultado = 1;
                return resultado;
            }
            
            resultado = -1;
            return resultado;
        }
        
        if(response == 1) {
            resultado = comparaDecomposicao(carta);
        }else if( response == 3 ){
            resultado = comparaAtaque(carta);
        }else if( response == 2 ){
            resultado = comparaReciclavel(carta);
        }else{
            resultado = comparaCor(carta);
        }
        
        return resultado;
    }
    
    public int comparaDecomposicao(Cartas carta){
        if(this.decomposicao > carta.decomposicao ){
            return -1;
        } else if(this.decomposicao == carta.decomposicao ){
            return 0;
        }else{
            return 1;
        }
    }
    
    public int comparaAtaque(Cartas carta){
        if(this.ataque < carta.ataque ){
            return -1; //perde
        } else if(this.ataque == carta.ataque ){
            return 0; 
        }else{
            return 1; //ganha
        }
    }
    
    public int comparaReciclavel(Cartas carta){
        if(carta.ehReciclavel() == this.ehReciclavel()){
            return 0;
        } else if( carta.ehReciclavel() && !this.ehReciclavel() ){
            return -1;
        }else{
            return 1;
        }
    }
    
    public int comparaCor(Cartas carta) {
        if (this.cor.equals(carta.cor))
            return 0;
        if (this.cor == Cor.VERDE && (carta.cor == Cor.MARROM ||
                                      carta.cor == Cor.CINZA ||                          
                                      carta.cor == Cor.PRETO ||
                                      carta.cor == Cor.BRANCO ||
                                      carta.cor == Cor.LARANJA))
            return 1;
        if (this.cor == Cor.CINZA && (carta.cor == Cor.PRETO ||
                                      carta.cor == Cor.BRANCO ||                          
                                      carta.cor == Cor.LARANJA ||
                                      carta.cor == Cor.ROXO ||
                                      carta.cor == Cor.AZUL )) 
            return 1;
        if (this.cor == Cor.LARANJA && (carta.cor == Cor.ROXO ||
                                        carta.cor == Cor.AZUL ||                          
                                        carta.cor == Cor.VERMELHO ||
                                        carta.cor == Cor.AMARELO ||
                                        carta.cor == Cor.VERDE)) 
            return 1;
        if (this.cor == Cor.VERMELHO && (carta.cor == Cor.AMARELO ||
                                         carta.cor == Cor.VERDE ||                          
                                         carta.cor == Cor.MARROM ||
                                         carta.cor == Cor.CINZA ||
                                         carta.cor == Cor.PRETO)) 
            return 1;
        if (this.cor == Cor.AZUL && (carta.cor == Cor.VERMELHO ||
                                     carta.cor == Cor.AMARELO ||                          
                                     carta.cor == Cor.VERDE ||
                                     carta.cor == Cor.MARROM ||
                                     carta.cor == Cor.CINZA)) 
            return 1;
        if (this.cor == Cor.AMARELO && (carta.cor == Cor.VERDE ||
                                        carta.cor == Cor.MARROM ||                          
                                        carta.cor == Cor.CINZA ||
                                        carta.cor == Cor.PRETO ||
                                        carta.cor == Cor.BRANCO)) 
            return 1;
               
        if (this.cor == Cor.MARROM && (carta.cor == Cor.CINZA ||
                                       carta.cor == Cor.PRETO ||                          
                                       carta.cor == Cor.BRANCO ||
                                       carta.cor == Cor.LARANJA ||
                                       carta.cor == Cor.ROXO)) 
            return 1;
        
        if (this.cor == Cor.ROXO && (carta.cor == Cor.AZUL ||
                                     carta.cor == Cor.VERMELHO ||                          
                                     carta.cor == Cor.AMARELO ||
                                     carta.cor == Cor.VERDE ||
                                     carta.cor == Cor.MARROM)) 
            return 1;
        
        if (this.cor == Cor.BRANCO && (carta.cor == Cor.LARANJA ||
                                       carta.cor == Cor.ROXO ||                          
                                       carta.cor == Cor.AZUL ||
                                       carta.cor == Cor.VERMELHO ||
                                       carta.cor == Cor.AMARELO)) 
            return 1;
        
        if (this.cor == Cor.PRETO && (carta.cor == Cor.BRANCO ||
                                      carta.cor == Cor.LARANJA ||                          
                                      carta.cor == Cor.ROXO ||
                                      carta.cor == Cor.AZUL ||
                                      carta.cor == Cor.VERMELHO)) 
            return 1;
        
        if( this.cor == Cor.MAIOR )
            return 1;
        else 
            return -1;
    }
    
    @Override
    public String toString() {
        String str = "\nCarta " + codigo + ": " + nome + ". Descrição: " + descricao + " Tipo: " + tipo + ". Decomposição: " + decomposicao + ". Cor: " + cor +". Ataque: "+ ataque + ". " ;

        return str;
    }
}

