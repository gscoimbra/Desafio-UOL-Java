package entity;

//  Anotações Lombok: As anotações Lombok ajudam a reduzir código, como getters, setters e construtores.
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

// O pacote javax.persistence é parte da especificação JPA (Java Persistence API), que fornece APIs para gerenciar dados relacionais em aplicações Java
// Por exemplo, @Entity, @Id, @GeneratedValue, @OneToMany, @ManyToOne...
import javax.persistence.*;

// Biblioteca para o uso de Listas
import java.util.List;

@Getter // Gera os getters
@Setter // Gera os setter
@AllArgsConstructor // Gera um construtor que aceita um argumento para cada campo na classe
@NoArgsConstructor // Gera um construtor sem argumentos
@Entity // Indica que a classe é uma entidade JPA
public class CentroDeDistribuicao {

    @Id //  Especifica o campo que é a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração automática de valores para a chave primária
    private int id; // Chave primária, gerada automaticamente
    private String nome; // Nome do Centro de Distribuição(Pode ser o endereço também)
    private int capacidadeTotal; // Capacidade total de cada item

    @OneToMany(mappedBy = "centroDeDistribuicao") // Cada centro de distribuição pode receber muitas doações, mas cada doação irá para um único centro
    private List<Doacao> doacoes;
}
