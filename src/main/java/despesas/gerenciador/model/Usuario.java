package despesas.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@EqualsAndHashCode(callSuper = true)
public class Usuario extends Entidade {
    public static final String TABLE_NAME = "usuario";

    @Column(name = "usuario", length = 255, nullable = false, unique = true)
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 255)
    private String usuario;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 255)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "senha", length = 60, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 60)
    private String senha;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    private List<Despesa> despesas = new ArrayList<Despesa>();

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    private List<Receita> receitas = new ArrayList<Receita>();
}
