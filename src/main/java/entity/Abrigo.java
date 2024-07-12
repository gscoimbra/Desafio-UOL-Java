package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Abrigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String endereco;
    private String responsavel;
    private String telefone;
    private String email;
    private Integer capacidade;
    private Double ocupacao; // Percentual de ocupação (0 a 1)

    @OneToMany(mappedBy = "abrigo")
    private List<Doacao> doacoes;
}