/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;


import Model.Acessorio;

/**
 *
 * @author Vinicius Santiago
 */
public class AcessorioDAO extends DAO {
    private static AcessorioDAO acessDAO;
    
    private static final String sqlconsultaacessorios = "SELECT * FROM \"acessorio\" order by Produto";
    private static final String sqlinserir = "INSERT INTO acessorio (id, produto, preco, carro) VALUES (?, ?, ?, ?)";
    private static final String sqlalterar = "UPDATE acessorio SET produto = ?, preco = ?, carro = ? WHERE id = ?";
    private static final String sqlaexcluir = "DELETE FROM acessorio WHERE id = ?";

    public static AcessorioDAO getAcessDAO() {
        if (acessDAO == null) {
            acessDAO = new AcessorioDAO();
        }
        return acessDAO;
    }

    private AcessorioDAO(){}
    
    public boolean Inserir(Acessorio aces) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            setPstdados(connection.prepareStatement(sqlinserir, tipo, concorrencia));
            getPstdados().setInt(1, aces.getId());
            getPstdados().setString(2, aces.getProduto());
            getPstdados().setDouble(3, aces.getPreco());
            getPstdados().setString(4, aces.getCarro());
            int resposta = getPstdados().executeUpdate();
            getPstdados().close();
            if (resposta == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException erro) {
            System.out.println("Erro na execução da inserção = " + erro);
        }
        return false;
    }
    
     public boolean Alterar(Acessorio aces) {
       try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            setPstdados(connection.prepareStatement(sqlalterar, tipo, concorrencia));    
            getPstdados().setString(1, aces.getProduto());
            getPstdados().setDouble(2, aces.getPreco());
            getPstdados().setString(3, aces.getCarro());
            getPstdados().setInt(4, aces.getId());
            int resposta = getPstdados().executeUpdate();
            getPstdados().close();
            if (resposta == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException erro) {
            System.out.println("Erro na execução da atualização = " + erro);
        }
        return false;
    }
    
     public boolean Excluir(Acessorio aces) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            setPstdados(connection.prepareStatement(sqlaexcluir, tipo, concorrencia));
            getPstdados().setInt(1, aces.getId());
            int resposta = getPstdados().executeUpdate();
            getPstdados().close();
            if (resposta == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException erro) {
            System.out.println("Erro na execução da exclusão = " + erro);
        }
        return false;
    }
     
      public boolean ConsultarTodos() {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            setPstdados(connection.prepareStatement(sqlconsultaacessorios, tipo, concorrencia));
            setRsdados(getPstdados().executeQuery());
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }
      
      public Acessorio getAcessorio() {
        Acessorio aces = null;
        if (getRsdados() != null) {
            try {
                int id = getRsdados().getInt("id");
                String produto = getRsdados().getString("produto");
                double preco = getRsdados().getDouble("preco");
                String carro = getRsdados().getString("carro");
                aces = new Acessorio(id, produto, preco, carro);
            } catch (SQLException erro) {
                System.out.println(erro);
            }
        }
        return aces;
    }
}



   