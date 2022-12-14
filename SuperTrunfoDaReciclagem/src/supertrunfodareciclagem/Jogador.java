/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supertrunfodareciclagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author julia
 */
public class Jogador {
    String nome;
    List<Cartas> cartas = new ArrayList<Cartas>();
    
    
    public Jogador(String nome) {
        this.nome = nome;
    }
    
    public int numeroDeCartas(){
       return this.cartas.size();
    }
    
    public boolean temCartas(){
        
        return !this.cartas.isEmpty();
    }
    
    public void incluir(Cartas carta){
       this.cartas.add(carta);
    }
    
    public void excluir(){
        this.cartas.remove(0);
    }
}
