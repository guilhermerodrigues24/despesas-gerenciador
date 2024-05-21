package despesas.gerenciador.controller;

import despesas.gerenciador.model.TipoDespesa;
import despesas.gerenciador.model.TipoReceita;
import despesas.gerenciador.service.TipoDespesaService;
import despesas.gerenciador.service.TipoReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tiporeceita")
@Validated
public class TipoReceitaController {

    @Autowired
    private TipoReceitaService tipoReceitaService;

    @GetMapping("/{id}")
    public ResponseEntity<TipoReceita> buscarTipoReceitaPorId(@PathVariable Long id) {
        TipoReceita tipoReceita = tipoReceitaService.buscarTipoReceitaPorId(id);
        return ResponseEntity.ok().body(tipoReceita);
    }

    @PostMapping
    public ResponseEntity<Void> criarTipoReceita(@RequestBody TipoReceita tipoReceita) {
        tipoReceitaService.criarTipoReceita(tipoReceita);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(tipoReceita.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarTipoReceita(@PathVariable Long id, @RequestBody TipoReceita tipoReceita) {
        tipoReceita.setId(id);
        tipoReceitaService.atualizarTipoReceita(tipoReceita);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTipoReceita(@PathVariable Long id) {
        tipoReceitaService.deletarTipoReceita(id);
        return ResponseEntity.noContent().build();
    }
}
