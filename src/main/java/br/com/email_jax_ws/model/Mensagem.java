package br.com.email_jax_ws.model;

public class Mensagem {
    private int id;
    private int remetente_id;
    private int destinataario_id;
    private String assunto;
    private String corpo;
    
    public int getIsRespostaof() {
        return isRespostaof;
    }

    public void setIsRespostaof(int isRespostaof) {
        this.isRespostaof = isRespostaof;
    }

    private int isRespostaof;

    public int getRemetente_id() {
        return remetente_id;
    }

    public void setRemetente_id(int remetente_id) {
        this.remetente_id = remetente_id;
    }

    public int getDestinataario_id() {
        return destinataario_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDestinataario_id(int destinataario_id) {
        this.destinataario_id = destinataario_id;
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
