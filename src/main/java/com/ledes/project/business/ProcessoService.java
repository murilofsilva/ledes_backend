package com.ledes.project.business;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.stream.Collectors;

@Service
public class ProcessoService {

    @Autowired
    private ObjectMapper mapper;

    @Getter
    private List<Processos> processos;

    @Value("classpath:mock/processos.json")
    private Resource resource;

    @PostConstruct
    public void init() {
        try (InputStream is = resource.getInputStream()) {
            this.processos = mapper.readValue(is, mapper.getTypeFactory().constructCollectionType(List.class, Processos.class));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao inicializar ProcessoService: " + e.getMessage(), e);
        }
    }

    public List<Processos> getProcessos(String filter) {
        if (filter == null || filter.trim().isEmpty()) {
            return processos;
        }

        return processos.stream().filter(p -> p.getNroProcesso().toString().equals(filter)).collect(Collectors.toList());
    }
}