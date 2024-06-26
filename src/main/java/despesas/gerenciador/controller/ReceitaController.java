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
import java.util.List;

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
    public ResponseEntity<?> criarReceita(@RequestBody Receita receita) {
        Receita novaReceita = receitaService.criarReceita(receita);
        return ResponseEntity.ok().body(novaReceita.getId());
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

    @GetMapping("/total/usuario/{usuarioId}")
    public ResponseEntity<Double> obterTotalReceitasPorUsuarioId(@PathVariable Long usuarioId) {
        double totalReceitas = receitaService.calcularTotalReceitasPorUsuarioId(usuarioId);
        return ResponseEntity.ok().body(totalReceitas);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Receita>> getReceitasByUsuarioId(@PathVariable Long id) {
        List<Receita> receitas = receitaService.buscarReceitasAssociadasAoUsuarioPorId(id);
        return ResponseEntity.ok(receitas);
    }

}
