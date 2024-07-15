package dao;

import config.JPAUtil;
import entity.Abrigo;
import entity.CentroDeDistribuicao;
import entity.Doacao;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DoacaoDAO {

    // save: Uso o EntityManager para iniciar uma transação, persistir a entidade e dar um commit na transação.
    public void save(Doacao doacao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(doacao);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // find: Uso o EntityManager para encontrar a entidade pelo ID.
    public Doacao find(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Doacao.class, id);
        } finally {
            em.close();
        }
    }

    // findAll: Faço uma consulta JPQL para recuperar em lista todos os objetos da entidade.
    public List<Doacao> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("FROM Doacao", Doacao.class).getResultList();
        } finally {
            em.close();
        }
    }

    // update: Uso o EntityManager para iniciar a transação, mesclar as mudanças e dou um commit.
    public void update(Doacao doacao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(doacao);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Uso o EntityManager para encontrar o objeto da entidade pelo ID e a removo.
    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Doacao doacao = em.find(Doacao.class, id);
            if (doacao != null) {
                em.remove(doacao);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Esse método retorna uma lista de Doacao com base no tipo de item e quantidade mais próxima solicitada.
    // Uso o EntityManager para criar uma consulta que ordena os resultados pela diferença absoluta entre a quantidade disponível e a quantidade solicitada.
    public List<Doacao> findByTipoItemAndQuantidade(String tipoItem, int quantidade) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Doacao> query = em.createQuery(
                    "SELECT d FROM Doacao d WHERE d.tipoItem = :tipoItem ORDER BY ABS(d.quantidade - :quantidade) ASC",
                    Doacao.class);
            query.setParameter("tipoItem", tipoItem);
            query.setParameter("quantidade", quantidade);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Esse método retorna uma lista de tipos distintos de itens doados.
    // Uso o EntityManager para criar uma consulta que seleciona tipos de itens únicos.
    public List<String> findDistinctTipoItem() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT DISTINCT d.tipoItem FROM Doacao d", String.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Esse método retorna uma lista de Doacao com base no tipo de item.
    // Uso o EntityManager parar criar uma consulta que filtra os resultados pelo tipo de item.
    public List<Doacao> findByTipoItem(String tipoItem) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("FROM Doacao d WHERE d.tipoItem = :tipoItem", Doacao.class)
                    .setParameter("tipoItem", tipoItem)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Esse método atualiza o centro de distribuição associado a uma Doacao específica.
    // Uso o EntityManager para iniciar uma transação, encontrar a Doacao e o CentroDeDistribuição, atualizar a relação e fazer commit da transação.
    public void updateCentroDeDistribuicao(int doacaoId, int novoCentroId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Doacao doacao = em.find(Doacao.class, doacaoId);
            CentroDeDistribuicao centroDeDistribuicao = em.find(CentroDeDistribuicao.class, novoCentroId);
            if (doacao != null) {
                doacao.setCentroDeDistribuicao(centroDeDistribuicao);
                em.merge(doacao);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para transferir uma doação para o abrigo
    public void transferirParaAbrigo(int doacaoId, int abrigoId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Doacao doacao = em.find(Doacao.class, doacaoId);
            Abrigo abrigo = em.find(Abrigo.class, abrigoId);
            if (doacao != null && abrigo != null) {
                doacao.setCentroDeDistribuicao(null);
                doacao.setAbrigo(abrigo);
                em.merge(doacao);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}