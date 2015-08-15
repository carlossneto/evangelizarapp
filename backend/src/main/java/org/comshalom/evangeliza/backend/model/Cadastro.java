package org.comshalom.evangeliza.backend.model;

/**
 * Created by BN on 8/3/15.
 */
public class Cadastro {
    // Labels table name
    public static final String TABLE = "Cadastro";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_nome = "nome";
    public static final String KEY_endereco = "endereco";
    public static final String KEY_bairro = "bairro";
    public static final String KEY_facebook = "facebook";
    public static final String KEY_idade = "idade";
    public static final String KEY_email = "email";
    public static final String KEY_tel = "tel";
    public static final String KEY_local = "local";

    // property help us to keep data
    public int cadastro_ID;
    public String nome;
    public String endereco;
    public String bairro;
    public String facebook;
    public int idade;
    public String email;
    public int tel;
    public String local;

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
