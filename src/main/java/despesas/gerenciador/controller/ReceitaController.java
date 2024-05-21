package despesas.gerenciador.controller;

import despesas.gerenciador.model.Despesa;
import despesas.gerenciador.model.Receita;
import despesas.gerenciador.service.DespesaService;
import despesas.gerenciador.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/receita")
@Validated
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/{id}")
    public ResponseEntity<Receita> buscarReceitaPorId(@PathVariable Long id) {
        Receita receita = receitaService.buscarReceitaPorId(id);
        return ResponseEntity.ok().body(receita);
    }

    @PostMapping
    public ResponseEntity<Void> criarReceita(@RequestBody Receita receita) {
        receitaService.criarReceita(receita);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarReceita(@RequestBody Receita receita, @PathVariable Long id) {
        receita.setId(id);
        receitaService.atualizarReceita(receita);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReceita(@PathVariable Long id) {
        receitaService.deletarReceita(id);
        return ResponseEntity.noContent().build();
    }

}
