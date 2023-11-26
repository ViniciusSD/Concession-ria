/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Carro;
/**
 *
 * @author Vinicius Santiago
 */
public class CarroDAO extends DAO {
    private static CarroDAO carroDao;

    private static final String sqlconsultacarros = "SELECT * FROM carro order by nome";
    private static final String sqlinserir = "INSERT INTO carro (id, nome, chassi, marca) VALUES (?, ?, ?, ?)";
    private static final String sqlalterar = "UPDATE carro SET nome = ?, chassi = ?, marca = ? WHERE id = ?";
    private static final String sqlaexcluir = "DELETE FROM carro WHERE id = ?";

    public static CarroDAO getCarroDao() {
        if (carroDao == null) {
            carroDao = new CarroDAO();
        }
        return carroDao;
    }

    private CarroDAO(){}
        
    public boolean Inserir(Carro car) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            setPstdados(connection.prepareStatement(sqlinserir, tipo, concorrencia));
            getPstdados().setInt(1, car.getId());
            getPstdados().setString(2, car.getNome());
            getPstdados().setInt(3, car.getChassi());
            getPstdados().setString(4, car.getMarca());
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
    
        public boolean Alterar(Carro car) {
       try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            setPstdados(connection.prepareStatement(sqlalterar, tipo, concorrencia));
            getPstdados().setString(1, car.getNome());
            getPstdados().setInt(2, car.getChassi());
            getPstdados().setString(3, car.getMarca());
            getPstdados().setInt(4, car.getId());
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
        
     public boolean Excluir(Carro car) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            setPstdados(connection.prepareStatement(sqlaexcluir, tipo, concorrencia));
            getPstdados().setInt(1, car.getId());
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
            setPstdados(connection.prepareStatement(sqlconsultacarros, tipo, concorrencia));
            setRsdados(getPstdados().executeQuery());
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }
     
      public Carro getCarro() {
        Carro car = null;
        if (getRsdados() != null) {
            try {
                int id = getRsdados().getInt("id");
                String nome = getRsdados().getString("nome");
                int chassi = getRsdados().getInt("chassi");
                String marca = getRsdados().getString("marca");
                car = new Carro(id, nome, chassi, marca);
            } catch (SQLException erro) {
                System.out.println(erro);
            }
        }
        return car;
    }
}
