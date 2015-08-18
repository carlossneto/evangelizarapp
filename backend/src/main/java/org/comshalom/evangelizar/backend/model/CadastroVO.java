package org.comshalom.evangelizar.backend.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.UUID;

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
    private String tel;
    private int local;

    private String nomeEvangelizador;
    private int tipoEvangelizador;
    private String telefoneEvangelizador;
    private String emailEvangelizador;
    private int eventoEvangelizador;

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

    public String getNomeEvangelizador() {
        return nomeEvangelizador;
    }

    public void setNomeEvangelizador(String nomeEvangelizador) {
        this.nomeEvangelizador = nomeEvangelizador;
    }

    public int getTipoEvangelizador() {
        return tipoEvangelizador;
    }

    public void setTipoEvangelizador(int tipoEvangelizador) {
        this.tipoEvangelizador = tipoEvangelizador;
    }

    public String getTelefoneEvangelizador() {
        return telefoneEvangelizador;
    }

    public void setTelefoneEvangelizador(String telefoneEvangelizador) {
        this.telefoneEvangelizador = telefoneEvangelizador;
    }

    public String getEmailEvangelizador() {
        return emailEvangelizador;
    }

    public void setEmailEvangelizador(String emailEvangelizador) {
        this.emailEvangelizador = emailEvangelizador;
    }

    public int getEventoEvangelizador() {
        return eventoEvangelizador;
    }

    public void setEventoEvangelizador(int eventoEvangelizador) {
        this.eventoEvangelizador = eventoEvangelizador;
    }
}
