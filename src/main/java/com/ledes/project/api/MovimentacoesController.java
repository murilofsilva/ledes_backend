package com.ledes.project.api;

import com.ledes.project.business.MovimentacoesService;
import com.ledes.project.model.Movimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacoesController {

    @Autowired
    private MovimentacoesService movimentacoesService;

    @PutMapping
    public Movimentacao atualizarMovimentacao(@RequestBody Movimentacao movimentacao) {
        return movimentacoesService.atualizarMovimentacao(movimentacao);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletarMovimentao(@PathVariable Long id) {
        movimentacoesService.deletarMovimentacao(id);
        return ResponseEntity.ok("Movimentação deletada com sucesso!");
    }
}
