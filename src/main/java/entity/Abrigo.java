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
public class Abrigo {
    @Id //  Especifica o campo que é a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração automática de valores para a chave primária
    private int id; // Chave primária, gerada automaticamente

    private String nome; // Nome do abrigo
    private String endereco; // Endereço do abrigo
    private String responsavel; // Pessoa responsável pelo abrigo
    private String telefone; //  Telefone de contato do abrigo
    private String email; // Email de contato do abrigo
    private Integer capacidade; //  Capacidade total do abrigo(número de itens que pode acomodar)
    private Double ocupacao; // Percentual de ocupação (0 a 1)

    // A anotação @OneToMany estabelece uma relação um-para-muitos com a entidade Doacao
    // Isso significa que um abrigo pode ter várias doações associadas a ele
    // O atributo mappedBy indica que o lado inverso da relação é gerenciado pela propriedade abrigo na entidade Doacao
    @OneToMany(mappedBy = "abrigo")
    private List<Doacao> doacoes;

    public double getOcupacao() {
        if (doacoes == null || capacidade == null || capacidade == 0) {
            return 0;
        }
        int quantidadeTotal = doacoes.stream().mapToInt(Doacao::getQuantidade).sum();
        return (double) quantidadeTotal / capacidade;
    }
}