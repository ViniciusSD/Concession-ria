/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Pneu;

/**
 *
 * @author Vinicius Santiago
 */
public class PneuDAO extends DAO {
    private static PneuDAO pneuDao;
    
    private static final String sqlconsultapneus = "SELECT * FROM \"pneu\" order by preco";
    private static final String sqlinserir = "INSERT INTO pneu (id, preco, nome, descricao) VALUES (?, ?, ?, ?)";
    private static final String sqlalterar = "UPDATE pneu SET preco = ?, nome = ?, descricao = ? WHERE id = ?";
    private static final String sqlaexcluir = "DELETE FROM pneu WHERE id = ?";

    public static PneuDAO getPneuDao() {
        if (pneuDao == null) {
            pneuDao = new PneuDAO();
        }
        return pneuDao;
    }
    
    private PneuDAO() {}

    public boolean Inserir(Pneu pn) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            setPstdados(connection.prepareStatement(sqlinserir, tipo, concorrencia));
            getPstdados().setInt(1, pn.getId());
            getPstdados().setDouble(2, pn.getPreco());
            getPstdados().setString(3, pn.getNome());
            getPstdados().setString(4, pn.getDescricao());
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

    public boolean Alterar(Pneu pn) {
       try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            setPstdados(connection.prepareStatement(sqlalterar, tipo, concorrencia));
            getPstdados().setDouble(1, pn.getPreco());
            getPstdados().setString(2, pn.getNome());
            getPstdados().setString(3, pn.getDescricao());
            getPstdados().setInt(4, pn.getId());
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

    public boolean Excluir(Pneu pn) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            setPstdados(connection.prepareStatement(sqlaexcluir, tipo, concorrencia));
            getPstdados().setInt(1, pn.getId());
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
            setPstdados(connection.prepareStatement(sqlconsultapneus, tipo, concorrencia));
            setRsdados(getPstdados().executeQuery());
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }

    public Pneu getPneu() {
        Pneu pn = null;
        if (getRsdados() != null) {
            try {
                int id = getRsdados().getInt("id");
                double preco = getRsdados().getDouble("preco");
                String nome = getRsdados().getString("nome");
                String descricao = getRsdados().getString("descricao");
                pn = new Pneu(id, nome, preco, descricao);
            } catch (SQLException erro) {
                System.out.println(erro);
            }
        }
        return pn;
    }
}
