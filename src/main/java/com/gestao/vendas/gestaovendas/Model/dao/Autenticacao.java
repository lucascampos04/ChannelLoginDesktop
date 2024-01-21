/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestao.vendas.gestaovendas.Model.dao;

import com.gestao.vendas.gestaovendas.Model.domain.Excpetion.NegocioException;
import com.gestao.vendas.gestaovendas.Model.domain.Perfil;
import com.gestao.vendas.gestaovendas.Model.domain.Usuario;
import com.gestao.vendas.gestaovendas.View.LoginDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.swing.*;

/**
 *
 * @author campos
 */
public class Autenticacao {
    private final UsuarioDao usuarioDao;
    
    public Autenticacao(){
        this.usuarioDao = new UsuarioDao();
    }

    public boolean temPermissao(Usuario usuario){
        try {
            permissao(usuario);
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Usuario sem permissão", 0);
        }
        return false;
    }

    private void permissao(Usuario usuario){
        if (!Perfil.ADMIN.equals(usuario.getPeril())){
            throw new NegocioException("Sem permissão para realizar essa ação");
        }
    }
    public Usuario login(LoginDto loginDto){
        Usuario usuario = usuarioDao.BuscarPeloUsername(loginDto.getUsuario());

        if (usuario == null || !usuario.isEstado()){
            System.out.println("usuario não encontrado");
            return null;
        }

        if (usuario.isEstado() && validarSenha(usuario.getSenha(), loginDto.getSenha())){
            return usuario;
        }
        return null;
    }

    public boolean validarSenha(String senhausuario, String senhalogin){
        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
        return cryptPasswordEncoder.matches(senhalogin, senhausuario);
    }
    
}
