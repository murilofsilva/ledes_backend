package com.ledes.project.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledes.project.model.Movimentacao;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacoesService {

    @Autowired
    private ObjectMapper mapper;

    @Getter
    private List<Movimentacao> movimentacoes;

    @Value("classpath:mock/movimentacoes.json")
    private Resource resource;

    @PostConstruct
    public void init() {
        try (InputStream is = resource.getInputStream()) {
            this.movimentacoes = mapper.readValue(is, mapper.getTypeFactory().constructCollectionType(List.class, Movimentacao.class));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao inicializar MovimentacoesService: " + e.getMessage(), e);
        }
    }

    private Optional<Movimentacao> buscarPorId(Long id) {
        return movimentacoes.stream()
                .filter(movimentacao -> movimentacao.getId().equals(id))
                .findFirst();
    }

    public Movimentacao atualizarMovimentacao(Movimentacao movimentacaoAtualizada) {
        Optional<Movimentacao> optionalMovimentacao = buscarPorId(movimentacaoAtualizada.getId());
        if (optionalMovimentacao.isPresent()) {
            Movimentacao movimentacao = optionalMovimentacao.get();
            movimentacao.setTitulo(movimentacaoAtualizada.getTitulo());
            movimentacao.setDescricao(movimentacaoAtualizada.getDescricao());
            movimentacao.setData(movimentacaoAtualizada.getData());
            return movimentacao;
        } else {
            throw new RuntimeException("Movimentação não encontrada com o ID: " + movimentacaoAtualizada.getId());
        }
    }

    public void deletarMovimentacao(Long id) {
        Optional<Movimentacao> existingMov = movimentacoes.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();

        existingMov.ifPresent(movimentacao -> movimentacoes.remove(movimentacao));
    }
}
