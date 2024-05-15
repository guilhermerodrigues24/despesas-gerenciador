package despesas.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = Usuario.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Usuario {
    public static final String TABLE_NAME = "usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario", length = 255, nullable = false, unique = true)
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 255)
    private String usuario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "senha", length = 60, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 60)
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<Despesa> despesas = new ArrayList<Despesa>();
}
