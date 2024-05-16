package despesas.gerenciador.controller;

import despesas.gerenciador.model.TipoDespesa;
import despesas.gerenciador.service.TipoDespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tipo")
@Validated
public class TipoDespesaController {

    @Autowired
    private TipoDespesaService tipoDespesaService;

    @GetMapping("/{id}")
    public ResponseEntity<TipoDespesa> buscarTipoDespesaPorId(@PathVariable Long id) {
        TipoDespesa tipoDespesa = tipoDespesaService.buscarTipoDespesaPorId(id);
        return ResponseEntity.ok().body(tipoDespesa);
    }

    @PostMapping
    public ResponseEntity<Void> criarTipoDespesa(@RequestBody TipoDespesa tipoDespesa) {
        tipoDespesaService.criarTipoDespesa(tipoDespesa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(tipoDespesa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarTipoDespesa(@PathVariable Long id, @RequestBody TipoDespesa tipoDespesa) {
        tipoDespesa.setId(id);
        tipoDespesaService.atualizarTipoDespesa(tipoDespesa);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTipoDespesa(@PathVariable Long id) {
        tipoDespesaService.deletarTipoDespesa(id);
        return ResponseEntity.noContent().build();
    }
}
