package despesas.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = TipoReceita.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TipoReceita extends Entidade{

    public static final String TABLE_NAME = "tipo_receita";

    @Column(name = "tipo", length = 255, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String tipo;

    @OneToMany(mappedBy = "tipoReceita")
    @JsonIgnoreProperties("tipoReceita")
    private List<Receita> receitas = new ArrayList<Receita>();
}
