/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestao.vendas.gestaovendas.View;

/**
 *
 * @author campos
 */
public class LoginDto {
    private String usuario;
    private String senha;

    public LoginDto(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
