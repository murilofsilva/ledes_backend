package com.ledes.project.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class Processos {
    private Long id;
    private List<Movimentacao> movimentacoes;
    private Documento documento;
    private String titulo;
    private String descricao;
    private LocalDate dtCriacao;
    private String tipo;
    private BigDecimal vlDivida;
    private String nomeJuiz;
    private Long nroProcesso;
    private String distribuicao;
    private String vara;
    private String nomeExecutado;
    private Boolean dataPrescricaoValidada;
}