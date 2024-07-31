package com.ledes.project.api;

import com.ledes.project.business.ProcessoService;
import com.ledes.project.model.Documento;
import com.ledes.project.model.Processos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/{id}")
    public Processos getProcessoById(@PathVariable Long id) {
        return processoService.getProcessoById(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> validarDataPrescricao(@PathVariable Long id) {
        processoService.validarDataPrescricao(id);
        return ResponseEntity.ok("Data de prescrição validada com sucesso.");
    }

    @PutMapping(path = "/{id}/documento")
    public ResponseEntity<String> validarDataPrescricao(@PathVariable Long id,
                                                        @RequestBody String conteudoDocumento) {
        processoService.atualizarConteudoDocumento(id, conteudoDocumento);
        return ResponseEntity.ok("Documento atualizado com sucesso.");
    }
}