package despesas.gerenciador.model;

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
@EqualsAndHashCode
public class Despesa {
    public static final String TABLE_NAME = "despesa";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

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
    @NotEmpty
    @DecimalMin(value = "0.0", inclusive = false, message = "O custo deve ser maior que 0")
    private BigDecimal custo;

    @Column(name = "vencimento", nullable = false)
    @NotNull
    private LocalDate vencimento;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
