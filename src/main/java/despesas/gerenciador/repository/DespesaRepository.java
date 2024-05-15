package despesas.gerenciador.repository;

import despesas.gerenciador.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    List<Despesa> findByUsuario_Id(Long id);
}
