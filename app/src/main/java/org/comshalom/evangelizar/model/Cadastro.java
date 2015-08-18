package org.comshalom.evangelizar.model;

/**
 * Created by BN on 8/3/15.
 */
public class Cadastro {
    // Labels table name
    public static final String TABLE = "Cadastro";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_nome = "nome";
    public static final String KEY_bairro = "bairro";
    public static final String KEY_facebook = "facebook";
    public static final String KEY_idade = "idade";
    public static final String KEY_email = "email";
    public static final String KEY_tel = "tel";
    public static final String KEY_local = "local";
    public static final String KEY_sync = "sync";


    // property help us to keep data
    private int cadastro_ID;
    private String nome;
    private String bairro;
    private String facebook;
    private String idade;
    private String email;
    private String tel;
    private int local;
    private int sync;

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public int getCadastro_ID() {
        return cadastro_ID;
    }

    public void setCadastro_ID(int cadastro_ID) {
        this.cadastro_ID = cadastro_ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }
}
