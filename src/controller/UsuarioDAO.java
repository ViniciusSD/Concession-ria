/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Model.Usuario;
import View.JanelaInicial;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author willi
 */
public class UsuarioDAO extends DAO {
    private static UsuarioDAO usuarioDAO;
    private static final String sqlObter = "SELECT * FROM usuario WHERE usuario = ?";
    private static final String sqlinserir = "INSERT INTO usuario VALUES (?, ?)";
    
    public static UsuarioDAO getUsuarioDAO() {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO();
        }
        return usuarioDAO;
    }
    
    private UsuarioDAO () {}
    
    public boolean validar (Usuario user) {
        try { 
            int cont = 0; 
            if (vericarEmail(user.getUsuario())) {
                do {
                    if (user.getUsuario().equals(getRsdados().getString("usuario"))) {
                        if (user.getSenha() == getRsdados().getInt("senha")) {
                            JanelaInicial.getJanelaInicio().setVisible(true); 
                            cont = 1; return true;
                        } else {
                            JOptionPane.showMessageDialog(null, "A senha está errada!!!");
                        }
                    }
                } while (getRsdados().next());
                if (cont == 0) {
                    JOptionPane.showMessageDialog(null, "Não Autenticado!!!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Email não cadastrado!!!");
            } return false;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex); return false;
        }
    }
    
    public boolean inserir(Usuario user) {
        try {
            if (!vericarEmail(user.getUsuario())) {
                int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
                int concorrencia = ResultSet.CONCUR_UPDATABLE;
                setPstdados(connection.prepareStatement(sqlinserir, tipo, concorrencia));
                getPstdados().setString(1, user.getUsuario());
                getPstdados().setInt(2, user.getSenha());
                int resposta = getPstdados().executeUpdate();
                getPstdados().close();
                if (resposta == 1) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Este email já está cadastrado");
            }
        } catch (SQLException erro) {
            System.out.println("Erro na execução da inserção = " + erro.getMessage());
        }
        return false;
    }
    
    public final boolean vericarEmail (String usuario) {
        try {
        
            setPstdados(connection.prepareStatement(sqlObter));
            getPstdados().setString(1, usuario);
            setRsdados(getPstdados().executeQuery());
            return getRsdados().next();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex); return false;
        }
    }
}