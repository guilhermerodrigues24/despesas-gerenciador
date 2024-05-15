package despesas.gerenciador.service;

import despesas.gerenciador.model.Despesa;
import despesas.gerenciador.model.Usuario;
import despesas.gerenciador.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Despesa criarDespesa(Despesa despesa) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(despesa.getUsuario().getId());
        despesa.setId(null);
        despesa.setUsuario(usuario);
        despesa = despesaRepository.save(despesa);
        return despesa;
    }

    public Despesa buscarDespesaPorId(Long id) {
        Optional<Despesa> despesa = despesaRepository.findById(id);
        return despesa.orElseThrow(() -> new RuntimeException("Despesa não encontrada!"));
    }

    public List<Despesa> buscarDespesasAssociadasAoUsuarioPorId(Long id) {
        List<Despesa> despesas = despesaRepository.findByUsuario_Id(id);
        return despesas;
    }

    @Transactional
    public Despesa atualizarDespesa(Despesa despesa) {
        Despesa novaDespesa = buscarDespesaPorId(despesa.getId());
        novaDespesa.setDescricao(despesa.getDescricao());
        novaDespesa.setNome(despesa.getNome());
        novaDespesa.setCusto(despesa.getCusto());
        novaDespesa.setVencimento(despesa.getVencimento());
        return despesaRepository.save(novaDespesa);
    }

    public void deletarDespesa(Long id){
        buscarDespesaPorId(id);
        try {
            despesaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir, há entidades relacionadas!");
        }
    }
}
