package org.comshalom.evangelizar.backend.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Cadastro entity.
 */
@Entity
public class CadastroVO {

    // property help us to keep data
    @Id
    private String idGoogle;
    private String nome;
    private String bairro;
    private String facebook;
    private String idade;
    private String email;
    private String telefone;
    private String local;

    private String evangelizadorNome;
    private String evangelizadorTipo;
    private String evangelizadorTelefone;
    private String evangelizadorEmail;
    private String evangelizadorEvento;

    public String getIdGoogle() {
        return idGoogle;
    }

    public void setIdGoogle(String idGoogle) {
        this.idGoogle = idGoogle;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getEvangelizadorNome() {
        return evangelizadorNome;
    }

    public void setEvangelizadorNome(String evangelizadorNome) {
        this.evangelizadorNome = evangelizadorNome;
    }

    public String getEvangelizadorTipo() {
        return evangelizadorTipo;
    }

    public void setEvangelizadorTipo(String evangelizadorTipo) {
        this.evangelizadorTipo = evangelizadorTipo;
    }

    public String getEvangelizadorTelefone() {
        return evangelizadorTelefone;
    }

    public void setEvangelizadorTelefone(String evangelizadorTelefone) {
        this.evangelizadorTelefone = evangelizadorTelefone;
    }

    public String getEvangelizadorEmail() {
        return evangelizadorEmail;
    }

    public void setEvangelizadorEmail(String evangelizadorEmail) {
        this.evangelizadorEmail = evangelizadorEmail;
    }

    public String getEvangelizadorEvento() {
        return evangelizadorEvento;
    }

    public void setEvangelizadorEvento(String evangelizadorEvento) {
        this.evangelizadorEvento = evangelizadorEvento;
    }
}
