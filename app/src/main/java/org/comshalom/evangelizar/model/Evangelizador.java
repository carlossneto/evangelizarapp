package org.comshalom.evangelizar.model;

/**
 * @author matheus
 */
public class Evangelizador {

    // Labels table name
    public static final String TABLE = "Evangelizador";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_nome = "nome";
    public static final String KEY_tipo = "tipo";
    public static final String KEY_telefone = "telefone";
    public static final String KEY_email = "email";
    public static final String KEY_evento = "evento";

    private int id = 1; //Apenas um Evangelizador por telefone, por isso o ID é 1.
    private String nome;
    private int tipo;
    private String telefone;
    private String email;
    private int evento;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }
}
