package despesas.gerenciador.repository;

import despesas.gerenciador.model.TipoDespesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDespesaRepository extends JpaRepository<TipoDespesa, Long> {
}
