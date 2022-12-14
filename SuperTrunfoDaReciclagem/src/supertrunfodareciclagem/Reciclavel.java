/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supertrunfodareciclagem;

/**
 *
 * @author julia
 */
public class Reciclavel extends Cartas {
    public Reciclavel(String codigo, String nome, String descricao, String tipo, double decomposicao, Cor cor, int ataque) {
        super(codigo, nome, descricao, tipo, decomposicao, cor, ataque );
    }

    @Override
    public String toString() {
        return super.toString() + "Reciclável{" + "}";
    }
        
    @Override
    public boolean ehReciclavel() {
        return true;
    }

}
