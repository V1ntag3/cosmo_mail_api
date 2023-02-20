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

    public Integer getId() {
        return id;
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
