package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    // Criação do EntityManagerFactory, responsável por criar instâncias do EntityManager
    // Configurado uma vez, geralmente no início da aplicação, e armazena as configurações(persistence.xml) de conexão com o banco de dados.
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("my-persistence-unit");

    // Fornecimento do EntityManager, é usado para obter a instância do EntityManager.
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Fechamento do EntityManager, usado para finalizar a aplicação e liberar recursos.
    // Importante para evitar por exemplo que o banco de dados fique conectado quando não queremos.
    public static void close() {
        emf.close();
    }
}
