/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestao.vendas.gestaovendas.Model.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author campo
 */
public class Venda {
    private Long id;
    private Cliente cliente;
    private Usuario usuario;
    private BigDecimal totalVenda;
    private BigDecimal valorPage;
    private BigDecimal desconto;
    private BigDecimal troco;
    private LocalDateTime dataHoraCriada;
    private LocalDateTime ultimaAtualizacao;

    public Venda() {
    }

    public Venda(Long id, Cliente cliente, Usuario usuario, BigDecimal totalVenda, BigDecimal valorPage, BigDecimal desconto, BigDecimal troco, LocalDateTime dataHoraCriada, LocalDateTime ultimaAtualizacao) {
        this.id = id;
        this.cliente = cliente;
        this.usuario = usuario;
        this.totalVenda = totalVenda;
        this.valorPage = valorPage;
        this.desconto = desconto;
        this.troco = troco;
        this.dataHoraCriada = dataHoraCriada;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(BigDecimal totalVenda) {
        this.totalVenda = totalVenda;
    }

    public BigDecimal getValorPage() {
        return valorPage;
    }

    public void setValorPage(BigDecimal valorPage) {
        this.valorPage = valorPage;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getTroco() {
        return troco;
    }

    public void setTroco(BigDecimal troco) {
        this.troco = troco;
    }

    public LocalDateTime getDataHoraCriada() {
        return dataHoraCriada;
    }

    public void setDataHoraCriada(LocalDateTime dataHoraCriada) {
        this.dataHoraCriada = dataHoraCriada;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
    
    
}
