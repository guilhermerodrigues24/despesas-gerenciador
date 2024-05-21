package despesas.gerenciador.service;

import despesas.gerenciador.model.TipoDespesa;
import despesas.gerenciador.model.TipoReceita;
import despesas.gerenciador.repository.TipoDespesaRepository;
import despesas.gerenciador.repository.TipoReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TipoReceitaService {

    @Autowired
    private TipoReceitaRepository tipoReceitaRepository;

    @Transactional
    public TipoReceita criarTipoReceita(TipoReceita tipoReceita) {
        tipoReceita.setId(null);
        tipoReceita = tipoReceitaRepository.save(tipoReceita);
        return tipoReceita;
    }

    public TipoReceita buscarTipoReceitaPorId(Long id) {
        Optional<TipoReceita> tipoReceita = tipoReceitaRepository.findById(id);
        return tipoReceita.orElseThrow(() -> new RuntimeException("Tipo de receita não encontrado!"));
    }

    @Transactional
    public TipoReceita atualizarTipoReceita(TipoReceita tipoReceita) {
        TipoReceita novoTipoReceita = buscarTipoReceitaPorId(tipoReceita.getId());
        novoTipoReceita.setTipo(tipoReceita.getTipo());
        return tipoReceitaRepository.save(novoTipoReceita);
    }

    public Optional<TipoReceita> deletarTipoReceita(Long id) {
        buscarTipoReceitaPorId(id);
        try {
            tipoReceitaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir, há entidades relacionadas!");
        }
        return tipoReceitaRepository.findById(id);
    }
}
