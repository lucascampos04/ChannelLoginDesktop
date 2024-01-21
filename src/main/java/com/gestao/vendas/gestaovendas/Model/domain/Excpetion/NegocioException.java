package com.gestao.vendas.gestaovendas.Model.domain.Excpetion;

public class NegocioException extends RuntimeException{
    public NegocioException(String message){
        super(message);
    }
}
