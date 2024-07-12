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
public class CentroDeDistribuicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private int capacidadeTotal; // Capacidade total de cada item

    @OneToMany(mappedBy = "centroDeDistribuicao") // Cada centro de distribuição pode receber muitas doações, mas cada doação irá para um único centro
    private List<Doacao> doacoes;
}
