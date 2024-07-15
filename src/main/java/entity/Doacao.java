package entity;

//  Anotações Lombok: As anotações Lombok ajudam a reduzir código, como getters, setters e construtores
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

// O pacote javax.persistence é parte da especificação JPA (Java Persistence API), que fornece APIs para gerenciar dados relacionais em aplicações Java
// Por exemplo, @Entity, @Id, @GeneratedValue, @OneToMany, @ManyToOne...
import javax.persistence.*;

import java.util.Date; // Biblioteca de Data

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera um construtor que aceita um argumento para cada campo na classe
@NoArgsConstructor // Gera um construtor sem argumentos
@Entity // Indica que a classe é uma entidade JPA
public class Doacao {
    @Id //  Especifica o campo que é a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração automática de valores para a chave primária
    private int id; // Chave primária, gerada automaticamente

    @ManyToOne // Cada centro de distribuição pode receber muitas doações, mas cada doação irá para um único centro
    @JoinColumn(name = "centro_id") // Especifica a coluna de junção para a associação
    private CentroDeDistribuicao centroDeDistribuicao; // Relação muitos-para-um com CentroDeDistribuicao. Cada doação pertence a um centro de distribuição

    private String tipoItem; // Tipo do item doado, é bom deixar para filtrar
    private Integer quantidade; // Quantidade do item doado
    private String roupaDescricao; // Descrição das roupas doadas
    private String roupaGenero; // Gênero das roupas doadas (M/F)
    private String roupaTamanho; // Tamanho das roupas doadas (Infantil/PP/P/M/G/GG)
    private String higieneDescricao; // Descrição dos produtos de higiene doados
    private Boolean higieneSabonete; // Indica se é sabonete
    private Boolean higieneEscova; // Indica se é escova de dente
    private Boolean higienePasta; // Indica se é pasta de dente
    private Boolean higieneAbsorvente; // Indica se é absorvente
    private String alimentoDescricao; // Descrição dos alimentos doados
    private String alimentoUnidadeMedida; // Unidade de medida dos alimentos doadosD

    @ManyToOne
    private Abrigo abrigo; // Relação muitos-para-um com Abrigo. Cada doação pode estar associada a um abrigo

    @Temporal(TemporalType.DATE) // Especifica o tipo de dados temporais
    private Date alimentoValidade; // Data da validade do alimento
    // Isso aqui pode causar um problema de escalabilidade, já que quando eu precisar cadastrar um novo tipo de doação precisarei adicionar mais colunas, que pode provocar bugs e lentidão do sistema
}
