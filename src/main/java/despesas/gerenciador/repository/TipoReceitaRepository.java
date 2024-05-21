package despesas.gerenciador.repository;

import despesas.gerenciador.model.TipoReceita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoReceitaRepository extends JpaRepository<TipoReceita, Long> {
}
