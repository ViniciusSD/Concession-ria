/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.io.Serializable;

/**
 *
 * @author Vinicius Santiago
 */
public class Carro implements Serializable {
    protected int id;
    protected String nome;
    protected int chassi;
    protected String marca;

    public Carro(){
    }
    
    public Carro(int id, String nm, int chassi, String marca) {
        this.id = id;
        this.nome = nm;
        this.chassi = chassi;
        this.marca = marca;
    }

    @Override
    public String toString() {
        String resposta = this.getId() + "\n";
        resposta += this.getNome() + "\n";
        resposta += this.getChassi() + "\n";
        resposta += this.getMarca() + "\n\n\n";
        return resposta;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the chassi
     */
    public int getChassi() {
        return chassi;
    }

    /**
     * @param chassi the chassi to set
     */
    public void setChassi(int chassi) {
        this.chassi = chassi;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
}
