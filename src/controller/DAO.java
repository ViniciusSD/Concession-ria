package controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * A simple data source for getting database connections.
 */
public abstract class DAO {

    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/controller/configuracaobd.properties");
    private static String url;
    private static String username;
    private static String password;
    protected Connection connection = null;
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    /**
     * Initializes the data source.
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public void init() throws IOException, ClassNotFoundException {

        Properties props = new Properties();
        FileInputStream in = new FileInputStream(config_file);
        props.load(in);
        String driver = props.getProperty("jdbc.driver");
        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        if (username == null) {
            username = "";
        }
        password = props.getProperty("jdbc.password");
        if (password == null) {
            password = "";
        }
        if (driver != null) {
            Class.forName(driver);
        }
    }

    /**
     * Gets a connection to the database.
     *
     * @return the database connection
     *
     * @throws java.sql.SQLException
     */
    public boolean getConnection() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        if (connection != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean CriaConexao() {
        try {
            //Inicializa os parâmetros e realiza a conexão
            init(); getConnection();
            connection.setAutoCommit(false); return true;
        }catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
        }
        return false;
    }
    
    public boolean FechaConexao(){    
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException erro) {
                System.err.println("Erro ao fechar a conexão = " + erro);
                return false;
            }
        } else {
            return false;
        }
    }
    
    public ResultSet getRsdados() {
        return rsdados;
    }

    public void setRsdados(ResultSet rsdados) {
        this.rsdados = rsdados;
    }

    public PreparedStatement getPstdados() {
        return pstdados;
    }

    public void setPstdados(PreparedStatement pstdados) {
        this.pstdados = pstdados;
    }
}
