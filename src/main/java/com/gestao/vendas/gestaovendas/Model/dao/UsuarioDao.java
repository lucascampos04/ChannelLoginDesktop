package com.gestao.vendas.gestaovendas.Model.dao;

import com.gestao.vendas.gestaovendas.Model.conexao.Conexao;
import com.gestao.vendas.gestaovendas.Model.conexao.ConexaoMysql;
import com.gestao.vendas.gestaovendas.Model.domain.Perfil;
import com.gestao.vendas.gestaovendas.Model.domain.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private final Conexao conexao;
    public UsuarioDao() {
        this.conexao = new ConexaoMysql();
    }
    public String salvar(Usuario usuario){
        return usuario.getId() == 0L ? adicionar(usuario) : editar(usuario);
    }

    private String adicionar(Usuario usuario){
        String sql = "INSERT INTO usuario(nome, usuario, senha, perfil, estado) VALUES (?, ?, ?, ?, ?)";

        Usuario usuarioTemp = BuscarPeloUsername(usuario.getUsuario());

        if (usuarioTemp != null){
            return String.format("Erro : Usuario %s já existe no banco de dados ", usuario.getUsuario());
        }

        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            preencherValoresPrepereStatement(preparedStatement, usuario);
            int result = preparedStatement.executeUpdate();
            return result == 1 ? "Usuario adicionado com sucesso" : "Não foi possivel adicionar o usuario";
        } catch (SQLException e){
            return String.format("Erro : %s", e.getMessage());
        }
    }
    public String editar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, usuario = ?,senha = ?, perfil = ?, estado = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            preencherValoresPrepereStatement(preparedStatement, usuario);
            int result = preparedStatement.executeUpdate();
            return result == 1 ? "Usuário editado com sucesso" : "Não foi possível editar o usuário";
        } catch (SQLException e) {
            return String.format("Erro: %s", e.getMessage());
        }
    }


    private void preencherValoresPrepereStatement(PreparedStatement preparedStatement, Usuario usuario) throws SQLException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String senhaCrypt = bCryptPasswordEncoder.encode(usuario.getSenha());

        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getUsuario());
        preparedStatement.setString(3, senhaCrypt);
        preparedStatement.setString(4, usuario.getPeril().name());
        preparedStatement.setString(5, usuario.isEstado() ? "1" : "0");

        if (usuario.getId() != 0) {
            preparedStatement.setLong(6, usuario.getId());
        }
    }

    // Listando usuarios
    public List<Usuario> ListarTodos(){
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try {
            Statement statement = conexao.obterConexao().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                usuarios.add(getUser(resultSet));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return usuarios;
    }


    private Usuario getUser(ResultSet resultSet) throws SQLException {
        String perfilString = resultSet.getString("perfil");
        Perfil perfil = Perfil.valueOf(perfilString);
        
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getLong("id"));
        usuario.setNome(resultSet.getString("nome"));
        usuario.setUsuario(resultSet.getString("usuario"));
        usuario.setSenha(resultSet.getString("senha"));
        usuario.setPeril(perfil);
        usuario.setEstado(resultSet.getBoolean("estado"));
        usuario.setDataHoraCriacao(resultSet.getObject("data_hora_criado", LocalDateTime.class));
        usuario.setUltimoLogin(resultSet.getObject("ultimo_login", LocalDateTime.class));
        return usuario;
    }

    // Buscando pelo id
    public Usuario BuscarPeloID(Long id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    // Buscando pelo usuario
    public Usuario BuscarPeloUsername(String usuario){
        String sql = String.format("SELECT * FROM usuario WHERE usuario = '%s'", usuario);
        try {
                PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return getUser(resultSet);
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

}
