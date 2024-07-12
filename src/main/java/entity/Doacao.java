package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne // Cada centro de distribuição pode receber muitas doações, mas cada doação irá para um único centro
    @JoinColumn(name = "centro_id")
    private CentroDeDistribuicao centroDeDistribuicao;

    private String tipoItem; // é bom deixar para filtrar
    private Integer quantidade;
    private String roupaDescricao;
    private String roupaGenero;
    private String roupaTamanho;
    private String higieneDescricao;
    private Boolean higieneSabonete;
    private Boolean higieneEscova; // Escova de dente
    private Boolean higienePasta; // Pasta de dente
    private Boolean higieneAbsorvente;
    private String alimentoDescricao;
    private String alimentoUnidadeMedida;

    @ManyToOne
    private Abrigo abrigo;

    @Temporal(TemporalType.DATE)
    private Date alimentoValidade;
    // Isso aqui pode causar um problema de escalabilidade, já que quando eu precisar cadastrar um novo tipo de doação precisarei adicionar mais colunas, que pode provocar bugs e lentidão do sistema.
}
