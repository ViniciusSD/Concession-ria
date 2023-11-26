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
public class Acessorio implements Serializable {
    private static long serialVersionUID = 1L;
    protected int id;
    protected String produto;
    protected double preco;
    protected String carro;

    
    public Acessorio(){
    }
    
    public Acessorio(int id, String nm, double prec, String car) {
        this.id = id;
        this.produto = nm;
        this.preco = prec;
        this.carro = car;
    }

    @Override
    public String toString() {
        String resposta = this.getId() + "\n";
        resposta += this.getProduto() + "\n";
        resposta += this.getPreco() + "\n";
        resposta += this.getCarro() + "\n\n\n";
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
    public String getProduto() {
        return produto;
    }

    /**
     * @param produto the nome to set
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the idade to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * @return the email
     */
    public String getCarro() {
        return carro;
    }

    /**
     * @param carro the  to set
     */
    public void setCarro(String carro) {
        this.carro = carro;
    }
}
