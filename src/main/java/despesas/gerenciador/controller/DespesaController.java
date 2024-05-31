package despesas.gerenciador.controller;

import despesas.gerenciador.model.Despesa;
import despesas.gerenciador.service.DespesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/despesa")
@Validated
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping("/{id}")
    public ResponseEntity<Despesa> buscarDespesaPorId(@PathVariable Long id) {
        Despesa despesa = despesaService.buscarDespesaPorId(id);
        return ResponseEntity.ok().body(despesa);
    }

    @PostMapping
    public ResponseEntity<Void> criarDespesa(@RequestBody Despesa despesa) {
        despesaService.criarDespesa(despesa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(despesa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarDespesa(@RequestBody Despesa despesa, @PathVariable Long id) {
        despesa.setId(id);
        despesaService.atualizarDespesa(despesa);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDespesa(@PathVariable Long id) {
        despesaService.deletarDespesa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total/usuario/{usuarioId}")
    public ResponseEntity<Double> obterTotalDespesasPorUsuarioId(@PathVariable Long usuarioId) {
        double totalDespesas = despesaService.calcularTotalDespesasPorUsuarioId(usuarioId);
        return ResponseEntity.ok().body(totalDespesas);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Despesa>> getDespesasByUsuarioId(@PathVariable Long id) {
        List<Despesa> despesas = despesaService.buscarDespesasAssociadasAoUsuarioPorId(id);
        return ResponseEntity.ok(despesas);
    }
}
