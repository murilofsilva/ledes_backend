package com.ledes.project.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class Processos {
    private Long id;
    private List<Movimentacao> movimentacoes;
    private String titulo;
    private String descricao;
    private LocalDate dtCriacao;
    //Criar uma enum para isso futuramente
    private String tipo;
    private BigDecimal vlDivida;
    private String nomeJuiz;
    private Long nroProcesso;
    //Criar uma enum para isso futuramente
    private String distribuicao;
    private String vara;
    private String nomeExecutado;
}