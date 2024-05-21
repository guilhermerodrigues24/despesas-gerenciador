package despesas.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = TipoDespesa.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TipoDespesa extends Entidade{
    public static final String TABLE_NAME = "tipo_despesa";

    @Column(name = "tipo", length = 255, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String tipo;

    @OneToMany(mappedBy = "tipoDespesa")
    @JsonIgnoreProperties("tipoDespesa")
    private List<Despesa> despesas = new ArrayList<Despesa>();
}
