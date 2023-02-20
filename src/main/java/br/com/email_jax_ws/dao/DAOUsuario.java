package br.com.email_jax_ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.email_jax_ws.conexao.*;
import br.com.email_jax_ws.model.Usuario;

public class DAOUsuario {
    // Função de criação de usuario no banco de dados
    public boolean criarUsuario(String nome, String email, String telefone, String senha) throws SQLException {

        String sql = "insert into usuario (id, nome, email, telefone, senha) values "
                + "(nextval('serial'), ?, ?, ?, ?);";

        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        try {

            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, telefone);
            ps.setString(4, senha);
            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return false;
    }

    // Pegar dados de usuario
    public Integer pegarUsuario(String email, String senha) throws SQLException {

        String sql = "select id from usuario where "
                + "(email = '" + email + "' and senha = '" + senha + "');";

        Connection con = Conexao.getConnection();
        Statement ps = con.createStatement();

        try {

            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return null;
    }

    public Integer pegarUsuarioEmail(String email) throws SQLException {

        String sql = "select id from usuario where "
                + "(email = '" + email + "');";

        Connection con = Conexao.getConnection();
        Statement ps = con.createStatement();

        try {

            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {

                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return null;
    }

    public Usuario pegarUsuarioId(Integer id) throws SQLException {

        String sql = "select * from usuario where "
                + "(id = " + id + ");";

        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        try {

            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setNome(rs.getString("nome"));
                user.setTelefone(rs.getString("telefone"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return null;
    }

    public boolean updateUsuarioNome(Integer id, String nome) throws SQLException {

        String sql = "update usuario set nome = ? where "
                + "(id = ?);";

        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        try {
            ps.setString(1, nome);
            ps.setInt(2, id);
            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return false;
    }

    public boolean updateUsuarioEmail(Integer id, String email) throws SQLException {

        String sql = "update usuario set email = ? where "
                + "(id = ?);";

        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        try {
            ps.setString(1, email);
            ps.setInt(2, id);
            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return false;
    }

    public boolean updateUsuarioTelefone(Integer id, String telefone) throws SQLException {

        String sql = "update usuario set telefone = ? where "
                + "(id = ?);";

        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        try {
            ps.setString(1, telefone);
            ps.setInt(2, id);
            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return false;
    }

    public boolean updateUsuarioSenha(Integer id, String senha) throws SQLException {

        String sql = "update usuario set senha = ? where "
                + "(id = ?);";

        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        try {
            ps.setString(1, senha);
            ps.setInt(2, id);
            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return false;
    }

    public boolean deletarUsuario(Integer id) throws SQLException {

        String sql = "delete from usuario where "
                + "(id = ?);";

        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        try {

            ps.setInt(1, id);
            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return false;
    }

}
