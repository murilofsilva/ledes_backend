package com.ledes.project.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Processo {
    private String titulo;
    private String descricao;
    private Date dtCriacao;
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
