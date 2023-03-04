package br.com.email_jax_ws.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static final Connection connection = construirConexao();

    private static Connection construirConexao() {
        try {
            // Class.forName("org.postgresql.Driver");
            // return DriverManager.getConnection(
            //         "jdbc:postgresql://localhost:5432/email_jax_ws_bd", "postgres", "marcus13");
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");

            java.sql.Statement statement = con.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS mensagem(id integer primary key,destinatario_id integer NOT NULL,remetente_id integer NOT NULL,assunto character varying(255) NOT NULL,corpo character varying(1000) NOT NULL,data date,email_destinatario character varying(200),apagado_remetente boolean NOT NULL DEFAULT 'false',apagado_destinatario boolean NOT NULL DEFAULT 'false',nome_destinatario character varying(200),nome_remetente character varying(200));CREATE TABLE IF NOT EXISTS usuario (id integer primary key,nome character varying(255) NOT NULL,email character varying(255) NOT NULL,telefone character varying(30) NOT NULL,senha character varying(200) NOT NULL);");
            System.out.println("exec");
            return DriverManager.getConnection("jdbc:sqlite:banco.db");

        } catch (Exception ex) {
            System.err.println("Conexão falhou: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return null;
    }

    public static Connection getConnection() {
        
        return connection;
    }
}