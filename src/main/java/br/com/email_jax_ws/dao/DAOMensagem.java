package br.com.email_jax_ws.dao;

import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.email_jax_ws.conexao.Conexao;
import br.com.email_jax_ws.model.*;

public class DAOMensagem {
    public boolean mandarMensagem(Mensagem msg) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String ts = sdf.format(timestamp);

        String sql = "insert into mensagem (resposta, apagado_remetente,apagado_destinatario, remetente_id, destinatario_id, nome_destinatario, nome_remetente , assunto, corpo, data, email_destinatario) values "
                + "("+ msg.getResposta() + ","+ false +","+ false+", "+msg.getRemetente_id()+", "+msg.getDestinatario_id()+", '"+msg.getNome_destinatario()+"', '"+msg.getNome_remetente()+"', '"+msg.getAssunto()+"','"+msg.getCorpo()+"','"+ts+"','"+msg.getEmail_destinatario()+"');";

        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
     

        try {

            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return false;
    }

    public Mensagem mensagem(Integer id) throws SQLException {
        String sql = "select * from mensagem where "
                + "(id = " + id + ");";

        Connection con = Conexao.getConnection();
        Statement ps = con.createStatement();

        try {
            ResultSet rs = ps.executeQuery(sql);
            Mensagem msg = new Mensagem();
            while (rs.next()) {
                msg.setId(rs.getInt("id"));
                msg.setDestinatario_id(rs.getInt("destinatario_id"));
                msg.setRemetente_id(rs.getInt("remetente_id"));
                msg.setAssunto(rs.getString("assunto"));
                msg.setCorpo(rs.getString("corpo"));
                msg.setData(rs.getDate("data"));
                msg.setResposta(rs.getInt("resposta"));
                msg.setEmail_destinatario(rs.getString("email_destinatario"));
                msg.setNome_destinatario(rs.getString("nome_destinatario"));
                msg.setNome_remetente(rs.getString("nome_remetente"));
            }
            return msg;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return null;
    }

    public ArrayList<Mensagem> mensagensEnviadas(Integer remetente_id) throws SQLException {
        String sql = "select * from mensagem where "
                + "(remetente_id = " + remetente_id + " and apagado_remetente = false) ORDER BY data DESC;";

        Connection con = Conexao.getConnection();
        Statement ps = con.createStatement();
        ArrayList<Mensagem> msgArray = new ArrayList<Mensagem>();
        try {

            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                Mensagem msg = new Mensagem();
                msg.setId(rs.getInt("id"));
                msg.setDestinatario_id(rs.getInt("destinatario_id"));
                msg.setRemetente_id(rs.getInt("remetente_id"));
                msg.setAssunto(rs.getString("assunto"));
                msg.setCorpo(rs.getString("corpo"));
                msg.setData(rs.getDate("data"));
                msg.setData_string(rs.getTime("data").toString());
                msg.setResposta(rs.getInt("resposta"));
                msg.setEmail_destinatario(rs.getString("email_destinatario"));
                msg.setNome_destinatario(rs.getString("nome_destinatario"));
                msg.setNome_remetente(rs.getString("nome_remetente"));
                msgArray.add(msg);
            }
            return msgArray;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return null;
    }

    public ArrayList<Mensagem> mensagensRecebidas(Integer destinatario_id) throws SQLException {
        String sql = "select * from mensagem where "
                + "(destinatario_id = " + destinatario_id + " and apagado_destinatario = false) ORDER BY data DESC;";

        Connection con = Conexao.getConnection();
        Statement ps = con.createStatement();
        ArrayList<Mensagem> msgArray = new ArrayList<Mensagem>();
        try {

            ResultSet rs = ps.executeQuery(sql);
            System.out.println(rs);
            while (rs.next()) {
                Mensagem msg = new Mensagem();
                msg.setId(rs.getInt("id"));
                System.out.println(rs.getInt("id"));
                msg.setDestinatario_id(rs.getInt("destinatario_id"));
                msg.setRemetente_id(rs.getInt("remetente_id"));
                msg.setAssunto(rs.getString("assunto"));
                msg.setCorpo(rs.getString("corpo"));
                msg.setData(rs.getDate("data"));
                msg.setData_string(rs.getTime("data").toString());
                msg.setResposta(rs.getInt("resposta"));
                msg.setEmail_destinatario(rs.getString("email_destinatario"));
                msg.setNome_destinatario(rs.getString("nome_destinatario"));
                msg.setNome_remetente(rs.getString("nome_remetente"));
                msgArray.add(msg);
            }
            return msgArray;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return null;
    }

    public boolean deletarMensagem(Integer id, Integer tipo) throws SQLException {
        String sql = "";
        if (tipo == 1) {
            sql = "update mensagem set apagado_destinatario = true where "
                    + "(id = "+id+");";
        }
        if (tipo == 2) {
            sql = "update mensagem set apagado_remetente = true where "
                    + "(id = "+id+");";
        }

        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        try {

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
