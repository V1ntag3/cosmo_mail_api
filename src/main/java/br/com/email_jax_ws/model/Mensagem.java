package br.com.email_jax_ws.model;

import java.sql.Date;

public class Mensagem {
    private Integer id;
    private Date data;
    private Integer remetente_id;
    private Integer destinatario_id;
    private String assunto;
    private String corpo;
    private String email_destinatario;
    public int[] mensagens;
    private Boolean apagado_destinatario;
    private Boolean apagado_remetente;
    private String nome_destinatario;

    public String getNome_remetente() {
        return nome_remetente;
    }

    public void setNome_remetente(String nome_remetente) {
        this.nome_remetente = nome_remetente;
    }

    private String nome_remetente;

    public String getNome_destinatario() {
        return nome_destinatario;
    }

    public void setNome_destinatario(String nome_destinatario) {
        this.nome_destinatario = nome_destinatario;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getApagado_destinatario() {
        return apagado_destinatario;
    }

    public void setApagado_destinatario(Boolean apagado_destinatario) {
        this.apagado_destinatario = apagado_destinatario;
    }

    public Boolean getApagado_remetente() {
        return apagado_remetente;
    }

    public void setApagado_remetente(Boolean apagado_remetente) {
        this.apagado_remetente = apagado_remetente;
    }

    public Date getData() {
        return data;
    }

    public String getEmail_destinatario() {
        return email_destinatario;
    }

    public void setEmail_destinatario(String email_destinatario) {
        this.email_destinatario = email_destinatario;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRemetente_id() {
        return remetente_id;
    }

    public Integer getDestinatario_id() {
        return destinatario_id;
    }

    public void setDestinatario_id(Integer destinatario_id) {
        this.destinatario_id = destinatario_id;
    }

    public void setRemetente_id(Integer remetente_id) {
        this.remetente_id = remetente_id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

}
