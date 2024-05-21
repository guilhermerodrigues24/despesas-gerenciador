package despesas.gerenciador.repository;

import despesas.gerenciador.model.Despesa;
import despesas.gerenciador.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    List<Receita> findByUsuario_Id(Long id);
}
