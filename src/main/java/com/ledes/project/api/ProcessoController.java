package com.ledes.project.api;

import com.ledes.project.business.ProcessoService;
import com.ledes.project.model.Processos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @GetMapping
    public List<Processos> getProcesso(@RequestParam(required = false, defaultValue = "") String filter) {
        return processoService.getProcessos(filter);
    }
}