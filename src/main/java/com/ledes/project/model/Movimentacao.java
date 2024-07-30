package com.ledes.project.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Movimentacao {

    private Long id;
    private Long idProcesso;
    private String titulo;
    private LocalDate data;
    private String descricao;
}
