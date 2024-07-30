package com.ledes.project.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledes.project.model.Movimentacao;
import com.ledes.project.model.Processos;
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
import java.util.stream.Collectors;

@Service
public class ProcessoService {

    @Autowired
    private ObjectMapper mapper;

    @Getter
    private List<Processos> processos;

    @Getter
    private List<Movimentacao> movimentacoes;

    @Value("classpath:mock/processos.json")
    private Resource processosResource;

    @Value("classpath:mock/movimentacoes.json")
    private Resource movimentacoesResource;

    @PostConstruct
    public void init() {
        try (InputStream processosStream = processosResource.getInputStream();
             InputStream movimentacoesStream = movimentacoesResource.getInputStream()) {
            this.processos = mapper.readValue(processosStream, mapper.getTypeFactory().constructCollectionType(List.class, Processos.class));
            this.movimentacoes = mapper.readValue(movimentacoesStream, mapper.getTypeFactory().constructCollectionType(List.class, Movimentacao.class));
            associateMovimentacoesWithProcessos();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao inicializar ProcessoService: " + e.getMessage(), e);
        }
    }

    private void associateMovimentacoesWithProcessos() {
        for (Processos processo : processos) {
            List<Movimentacao> movimentacoesDoProcesso = movimentacoes.stream()
                    .filter(m -> m.getIdProcesso().equals(processo.getId()))
                    .collect(Collectors.toList());
            processo.setMovimentacoes(movimentacoesDoProcesso);
        }
    }

    public List<Processos> getProcessos(String filter) {
        if (filter == null || filter.trim().isEmpty()) {
            return processos;
        }

        return processos.stream()
                .filter(p -> p.getNroProcesso().toString().equals(filter))
                .collect(Collectors.toList());
    }

    public Processos getProcessoById(Long id) {
        if (id == null) {
            return null;
        }

        Optional<Processos> processoOptional = processos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return processoOptional.orElse(null);
    }
}
