/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestao.vendas.gestaovendas.Model.domain;

import java.time.LocalDateTime;
import java.util.Objects;
/**
 *
 * @author campo
 */
public class Usuario implements ResetTable{
    private Long id;
    private String nome;
    private String senha;
    private String usuario;
    private Perfil peril;
    private boolean estado;
    private LocalDateTime data_hora_criado;
    private LocalDateTime ultimo_login;
    
    public Usuario(Long id, String nome, String senha, String usuario, Perfil peril, LocalDateTime data_hora_criado, LocalDateTime ultimo_login) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.usuario = usuario;
        this.peril = peril;
        this.estado = true;
        this.data_hora_criado = data_hora_criado;
        this.ultimo_login = ultimo_login;
    }
    
    public Usuario(){
        this.estado = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Perfil getPeril() {
        return peril;
    }

    public void setPeril(Perfil peril) {
        this.peril = peril;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDateTime getDataHoraCriacao() {
        return data_hora_criado;
    }

    public void setDataHoraCriacao(LocalDateTime data_hora_criado) {
        this.data_hora_criado = data_hora_criado;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimo_login;
    }

    public void setUltimoLogin(LocalDateTime ultimo_login) {
        this.ultimo_login = ultimo_login;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }


    @Override
    public void reset() {
        this.estado = true;
    }

    @Override
    public void mudarEstado() {
        this.estado = !this.estado;
    }
}
