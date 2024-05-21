package despesas.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = Despesa.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Despesa extends Entidade {
    public static final String TABLE_NAME = "despesa";

    @Column(name = "nome", length = 255, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String nome;

    @Column(name = "descricao", length = 255, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String descricao;

    @Column(name = "custo", nullable = false)
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "O custo deve ser maior que 0")
    private BigDecimal custo;

    @Column(name = "vencimento", nullable = false)
    @NotNull
    private LocalDate vencimento;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnoreProperties("despesas")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_despesa_id", nullable = false)
    @JsonIgnoreProperties("despesas")
    private TipoDespesa tipoDespesa;
}
