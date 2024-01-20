/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestao.vendas.gestaovendas.Model.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author campo
 */
public class ConexaoMysql implements Conexao{
       
    private final String USUARIO = "root";
    private final String PASSWORD = "senha12345";
    private final String URL = "jdbc:mysql://localhost:3306/gestaovendas";
    private Connection conectar;
    
    @Override
    public Connection obterConexao() throws SQLException {     
        if(conectar == null){
            conectar = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        }
        return conectar;
    }
    
}
