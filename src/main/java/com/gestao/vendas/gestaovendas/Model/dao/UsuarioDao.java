package com.gestao.vendas.gestaovendas.Model.dao;

import com.gestao.vendas.gestaovendas.Model.conexao.Conexao;
import com.gestao.vendas.gestaovendas.Model.conexao.ConexaoMysql;
import com.gestao.vendas.gestaovendas.Model.domain.Perfil;
import com.gestao.vendas.gestaovendas.Model.domain.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private final Conexao conexao;
    public UsuarioDao() {
        this.conexao = new ConexaoMysql();
    }
    public String salvar(Usuario usuario){
        return usuario.getId() == 0 ? adicionar(usuario) : editar(usuario);
    }

    private String adicionar(Usuario usuario){
        String sql = "INSERT INTO usuario(nome, usuario, senha, perfil, estado) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            preencherValoresPrepereStatement(preparedStatement, usuario);
            int result = preparedStatement.executeUpdate();
            return result == 1 ? "Usuario adicionado com sucesso" : "Não foi possivel adicionar o usuario";
        } catch (SQLException e){
            return String.format("Erro : %s", e.getMessage());
        }
    }
    private String editar(Usuario usuario) {
        String sql = "UPDATE categoria SET nome = ? , usuario = ?, senha = ?, perfil = ?, estado = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            preencherValoresPrepereStatement(preparedStatement, usuario);
            int result = preparedStatement.executeUpdate();
            return result == 1 ? "Usuario editar com sucesso" : "Não foi possivel editar o usuario";
        } catch (SQLException e){
            return String.format("Erro : %s", e.getMessage());
        }
    }

    private void preencherValoresPrepereStatement(PreparedStatement preparedStatement, Usuario usuario) throws SQLException {
        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getUsuario());
        preparedStatement.setString(3, usuario.getSenha());
        preparedStatement.setString(4, usuario.getPeril().name());
        preparedStatement.setString(5, String.valueOf(usuario.isEstado()));

        if (usuario.getId() != 0){
            preparedStatement.setLong(6, usuario.getId());

        }
    }
    // Listando usuarios
    public List<Usuario> ListarTodos(){
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try {
            ResultSet resultSet = (ResultSet) conexao.obterConexao().prepareStatement(sql);
            while (resultSet.next()){
                usuarios.add(getUser(resultSet));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return usuarios;
    }

    private Usuario getUser(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getLong("id"));
        usuario.setNome(resultSet.getString("nome"));
        usuario.setUsuario(resultSet.getString("usuario"));
        usuario.setSenha(resultSet.getString("senha"));
        usuario.setPeril(resultSet.getObject("perfil", Perfil.class));
        usuario.setEstado(resultSet.getBoolean("estado"));
        usuario.setDataHoraCriacao(resultSet.getObject("data hora criacao", LocalDateTime.class));
        usuario.setUltimoLogin(resultSet.getObject("ultimo login", LocalDateTime.class));
        return usuario;
    }

    // Buscando pelo id


}
