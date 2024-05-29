package despesas.gerenciador.service;

import despesas.gerenciador.model.Despesa;
import despesas.gerenciador.model.Receita;
import despesas.gerenciador.model.Usuario;
import despesas.gerenciador.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Receita criarReceita(Receita receita) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(receita.getUsuario().getId());
        receita.setId(null);
        receita.setUsuario(usuario);
        receita = receitaRepository.save(receita);
        return receita;
    }

    public Receita buscarReceitaPorId(Long id) {
        Optional<Receita> receita = receitaRepository.findById(id);
        return receita.orElseThrow(() -> new RuntimeException("Receita não encontrada!"));
    }

    public List<Receita> buscarReceitasAssociadasAoUsuarioPorId(Long id) {
        List<Receita> receitas = receitaRepository.findByUsuario_Id(id);
        return receitas;
    }

    @Transactional
    public Receita atualizarReceita(Receita receita) {
        Receita novaReceita = buscarReceitaPorId(receita.getId());
        novaReceita.setDescricao(receita.getDescricao());
        novaReceita.setNome(receita.getNome());
        novaReceita.setValor(receita.getValor());
        novaReceita.setData(receita.getData());
        return receitaRepository.save(novaReceita);
    }

    public void deletarReceita(Long id) {
        buscarReceitaPorId(id);
        try {
            receitaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir, há entidades relacionadas!");
        }
    }

    public double calcularTotalReceitasPorUsuarioId(Long usuarioId) {
        return receitaRepository.findByUsuario_Id(usuarioId)
                .stream()
                .map(Receita::getValor) // Supondo que getValor retorna BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add) // Soma os BigDecimal
                .doubleValue(); // Converte para double
    }


}
