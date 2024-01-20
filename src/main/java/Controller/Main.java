package Controller;

import com.gestao.vendas.gestaovendas.Model.conexao.Conexao;
import com.gestao.vendas.gestaovendas.Model.conexao.ConexaoMysql;
import com.gestao.vendas.gestaovendas.Model.domain.Categoria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            // Instanciando um objeto de conexão usando a implementação para MySQL
            Conexao conexao = new ConexaoMysql();

            // Criando um objeto de Categoria para ser inserido no banco de dados
            Categoria categoria = new Categoria(null, "Refrigerante", "Primeira inserção com Java");

            // Definindo a instrução SQL para a inserção
            String insertSql = "INSERT INTO categoria(nome, descricao) VALUES (?, ?)";

            // Preparando a instrução SQL para a execução
            try (PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(insertSql)) {
                // Substituindo os parâmetros na instrução SQL pelos valores do objeto Categoria
                preparedStatement.setString(1, categoria.getNome());
                preparedStatement.setString(2, categoria.getDescricao());

                // Executando a instrução SQL de inserção e obtendo o resultado
                int result = preparedStatement.executeUpdate();

                // Imprimindo o resultado da operação de inserção
                System.out.println("Linhas afetadas pela inserção: " + result);
            }

            // Selecionando todos os dados da tabela categoria
            String selectSql = "SELECT * FROM categoria";
            try (ResultSet resultSet = conexao.obterConexao().prepareStatement(selectSql).executeQuery()) {
                // Exibindo os dados da tabela categoria
                System.out.println("Dados da tabela categoria:");
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id") +
                            ", Nome: " + resultSet.getString("nome") +
                            ", Descrição: " + resultSet.getString("descricao"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
