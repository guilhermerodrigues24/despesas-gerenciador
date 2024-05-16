package despesas.gerenciador.service;

import despesas.gerenciador.model.TipoDespesa;
import despesas.gerenciador.repository.TipoDespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class TipoDespesaService {

    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;

    @Transactional
    public TipoDespesa criarTipoDespesa(TipoDespesa tipoDespesa) {
        tipoDespesa.setId(null);
        tipoDespesa = tipoDespesaRepository.save(tipoDespesa);
        return tipoDespesa;
    }

    public TipoDespesa buscarTipoDespesaPorId(Long id) {
        Optional<TipoDespesa> tipoDespesa = tipoDespesaRepository.findById(id);
        return tipoDespesa.orElseThrow(() -> new RuntimeException("Tipo de despesa não encontrado!"));
    }

    @Transactional
    public TipoDespesa atualizarTipoDespesa(TipoDespesa tipoDespesa) {
        TipoDespesa novoTipoDespesa = buscarTipoDespesaPorId(tipoDespesa.getId());
        novoTipoDespesa.setTipo(tipoDespesa.getTipo());
        return tipoDespesaRepository.save(novoTipoDespesa);
    }

    public Optional<TipoDespesa> deletarTipoDespesa(Long id) {
        buscarTipoDespesaPorId(id);
        try {
            tipoDespesaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir, há entidades relacionadas!");
        }
        return tipoDespesaRepository.findById(id);
    }
}
